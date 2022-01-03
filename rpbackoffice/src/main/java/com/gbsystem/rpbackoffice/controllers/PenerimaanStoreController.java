package com.gbsystem.rpbackoffice.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.PenerimaanStore;
import com.gbsystem.rpbackoffice.repository.PenerimaanStoreRepository;
import com.gbsystem.rpbackoffice.services.PenerimaanStoreService;

@RestController
@RequestMapping("/penerimaanStore")
@CrossOrigin
public class PenerimaanStoreController {
	
	@Autowired
	private PenerimaanStoreService penerimaanStoreService;
	
	@Autowired
	private PenerimaanStoreRepository eRepo;

    @GetMapping("/all")
	public ResponseEntity<List<PenerimaanStore>> getAll() {
        return new ResponseEntity<>(penerimaanStoreService.getAllPenerimaanStore(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PenerimaanStore>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penerimaanStoreService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String saveProduct(
    		@RequestParam("penerimaan_code") String penerimaan_code) throws Exception {
    	
    	if (penerimaan_code != "") {
    		penerimaanStoreService.savePenerimaanStore(penerimaan_code);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(@RequestParam("id") Long id,@RequestParam("penerimaan_code") String penerimaan_code) throws Exception {
    	
    	if (penerimaan_code != "") {
    		penerimaanStoreService.update(id, penerimaan_code);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        String code_penerimaan = "RT-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
        	PenerimaanStore p = new PenerimaanStore();
        	XSSFRow row = worksheet.getRow(i);
        	p.setPenerimaan_code(code_penerimaan);
    		p.setId_office((int)row.getCell(1).getNumericCellValue());
    		p.setLokasi_office(row.getCell(2).getStringCellValue());
    		p.setId_store((int)row.getCell(3).getNumericCellValue());
    		p.setLokasi_store(row.getCell(4).getStringCellValue());
    		p.setArtikel(row.getCell(5).getStringCellValue());
    		p.setKategori(row.getCell(6).getStringCellValue());
			p.setTipe(row.getCell(7).getStringCellValue());
			p.setNama_barang(row.getCell(8).getStringCellValue());
			p.setKuantitas(row.getCell(9).getNumericCellValue());
			p.setUkuran(row.getCell(10).getStringCellValue());
			p.setHpp(row.getCell(11).getNumericCellValue());
			p.setHarga_jual(row.getCell(12).getNumericCellValue());
    		p.setRowstatus(1);
    		p.setTanggal_penerimaan(row.getCell(0).getDateCellValue());
    		eRepo.save(p);
        }
        
    }
    
    @GetMapping("/delete")
    public String deletePenerimaanStore(@RequestParam("id") Long id,@RequestParam("id_office") int id_office,@RequestParam("artikel") String artikel)
    {
    	penerimaanStoreService.deletePenerimaanStoreById(id,id_office, artikel);
    	return "redirect:/all";
    }

}
