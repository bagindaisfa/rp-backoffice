package com.gbsystem.rpbackoffice.controllers;

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

import com.gbsystem.rpbackoffice.entities.Pemasok;
import com.gbsystem.rpbackoffice.repository.PemasokRepository;
import com.gbsystem.rpbackoffice.services.PemasokService;

@RestController
@RequestMapping("/pemasok")
@CrossOrigin
public class PemasokController {
	
	@Autowired
	private PemasokService pemasokService;
	
	@Autowired
	private PemasokRepository eRepo;

    @GetMapping("/all")
	public ResponseEntity<List<Pemasok>> getAll() {
        return new ResponseEntity<>(pemasokService.getAllPemasok(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Pemasok>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(pemasokService.search(keyword), HttpStatus.OK);
    }
    
    @GetMapping("/download")
    public ResponseEntity<List<Pemasok>> download(@Param("oe") String oe) {
    	return new ResponseEntity<>(pemasokService.download(oe), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add")
    public @ResponseBody String saveProduct(@RequestParam("kode_pemasok") String kode_pemasok,
    		@RequestParam("nama_pemasok") String nama_pemasok,
    		@RequestParam("no_hp") String no_hp,@RequestParam("email") String email,@RequestParam("alamat") String alamat,
    		@RequestParam("hpp") double hpp,@RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (nama_pemasok != "") {
    		pemasokService.savePemasok(kode_pemasok, nama_pemasok, no_hp, email, alamat, hpp, harga_jual);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update")
    public @ResponseBody String update(@RequestParam("id") Long id,@RequestParam("kode_pemasok") String kode_pemasok,
    		@RequestParam("nama_pemasok") String nama_pemasok,
    		@RequestParam("no_hp") String no_hp,@RequestParam("email") String email,@RequestParam("alamat") String alamat,
    		@RequestParam("hpp") double hpp,@RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (nama_pemasok != "") {
    		pemasokService.update(id, kode_pemasok, nama_pemasok, no_hp, email, alamat, hpp, harga_jual);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Pemasok p = new Pemasok();
        	XSSFRow row = worksheet.getRow(i);
    		p.setNama_pemasok(row.getCell(1).getStringCellValue());
    		p.setNama_pemasok(row.getCell(2).getStringCellValue());
    		p.setNo_hp(row.getCell(3).getStringCellValue());
    		p.setEmail(row.getCell(4).getStringCellValue());
    		p.setAlamat(row.getCell(5).getStringCellValue());
    		p.setHpp(row.getCell(6).getNumericCellValue());
    		p.setHarga_jual(row.getCell(7).getNumericCellValue());
    		p.setRowstatus(1);
    		p.setTanggal_join(row.getCell(0).getDateCellValue());
        	eRepo.save(p);
        }
        
    }
    
    @GetMapping("/delete")
    public String deletePemasok(@RequestParam("id") Long id)
    {
    	
    	pemasokService.deletePemasokById(id);
    	return "redirect:/pemasok/all";
    }
}
