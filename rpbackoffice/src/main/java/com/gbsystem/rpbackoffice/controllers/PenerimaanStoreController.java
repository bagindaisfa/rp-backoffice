package com.gbsystem.rpbackoffice.controllers;

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
    		@RequestParam("lokasi_penerimaan") String lokasi_penerimaan, @RequestParam("id_pelanggan") String id_pelanggan,
    		@RequestParam("nama_pelanggan") String nama_pelanggan, @RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori, @RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang, @RequestParam("kuantitas") double kuantitas, 
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang, 
    		@RequestParam("hpp") double hpp, @RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		penerimaanStoreService.savePenerimaanStore(lokasi_penerimaan, id_pelanggan, nama_pelanggan, artikel, kategori, tipe, nama_barang, 
    				kuantitas, ukuran, foto_barang, hpp, harga_jual);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(@RequestParam("id") Long id, @RequestParam("tanggal_penerimaan") Date tanggal_penerimaan,
    		@RequestParam("lokasi_penerimaan") String lokasi_penerimaan, @RequestParam("id_pelanggan") String id_pelanggan,
    		@RequestParam("nama_pelanggan") String nama_pelanggan, @RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("kuantitas") double kuantitas,
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang,
    		@RequestParam("hpp") double hpp, @RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		penerimaanStoreService.update(id, tanggal_penerimaan, lokasi_penerimaan, id_pelanggan, nama_pelanggan, artikel, kategori, tipe, nama_barang, 
    				kuantitas, ukuran, foto_barang, hpp, harga_jual);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
        	PenerimaanStore p = new PenerimaanStore();
        	XSSFRow row = worksheet.getRow(i);
        	p.setLokasi_penerimaan(row.getCell(1).getStringCellValue());
        	p.setId_pelanggan(row.getCell(2).getStringCellValue());
        	p.setNama_pelanggan(row.getCell(3).getStringCellValue());
    		p.setArtikel(row.getCell(4).getStringCellValue());
    		p.setKategori(row.getCell(5).getStringCellValue());
			p.setTipe(row.getCell(6).getStringCellValue());
			p.setNama_barang(row.getCell(7).getStringCellValue());
			p.setKuantitas(row.getCell(8).getNumericCellValue());
			p.setUkuran(row.getCell(9).getStringCellValue());
			p.setHpp(row.getCell(10).getNumericCellValue());
			p.setHarga_jual(row.getCell(11).getNumericCellValue());
    		p.setRowstatus(1);
    		p.setTanggal_penerimaan(row.getCell(0).getDateCellValue());
    		eRepo.save(p);
        }
        
    }
    
    @GetMapping("/delete")
    public String deletePenerimaanStore(@RequestParam("id") Long id)
    {
    	penerimaanStoreService.deletePenerimaanStoreById(id);
    	return "redirect:/all";
    }

}