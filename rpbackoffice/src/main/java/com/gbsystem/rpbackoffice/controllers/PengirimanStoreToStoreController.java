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

import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStore;
import com.gbsystem.rpbackoffice.repository.PengirimanStoreToStoreRepository;
import com.gbsystem.rpbackoffice.services.PengirimanStoreToStoreService;

@RestController
@RequestMapping("/pengirimanStore")
@CrossOrigin
public class PengirimanStoreToStoreController {
	
	@Autowired
	private PengirimanStoreToStoreService pengirimanStoreService;
	
	@Autowired
	private PengirimanStoreToStoreRepository eRepo;

    @GetMapping("/all")
	public ResponseEntity<List<PengirimanStoreToStore>> getAll() {
        return new ResponseEntity<>(pengirimanStoreService.getAllPengirimanStore(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PengirimanStoreToStore>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(pengirimanStoreService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String saveProduct(@RequestParam("id_store_asal") int id_store_asal,
    		@RequestParam("lokasi_store_asal") String lokasi_store_asal, @RequestParam("id_store_tujuan") int id_store_tujuan,
    		@RequestParam("lokasi_store_tujuan") String lokasi_store_tujuan, @RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori, @RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang, @RequestParam("kuantitas") double kuantitas, 
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang, 
    		@RequestParam("hpp") double hpp, @RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		pengirimanStoreService.savePengirimanStore(id_store_asal,lokasi_store_asal, id_store_tujuan, lokasi_store_tujuan, artikel, kategori, tipe, nama_barang, 
    				kuantitas, ukuran, foto_barang, hpp, harga_jual);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(@RequestParam("id") Long id, @RequestParam("tanggal_pengiriman") Date tanggal_pengiriman, @RequestParam("pengiriman_code") String pengiriman_code,
    		@RequestParam("id_store_asal") int id_store_asal,@RequestParam("lokasi_store_asal") String lokasi_store_asal, @RequestParam("id_store_tujuan") int id_store_tujuan,
    		@RequestParam("lokasi_store_tujuan") String lokasi_store_tujuan, @RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori, @RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang, @RequestParam("kuantitas") double kuantitas, 
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang, 
    		@RequestParam("hpp") double hpp, @RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		pengirimanStoreService.update(id,pengiriman_code, tanggal_pengiriman,id_store_asal, lokasi_store_asal, id_store_tujuan, lokasi_store_tujuan, artikel, kategori, tipe, nama_barang, 
    				kuantitas, ukuran, foto_barang, hpp, harga_jual);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
        	PengirimanStoreToStore p = new PengirimanStoreToStore();
        	XSSFRow row = worksheet.getRow(i);
        	p.setId_store_asal((int)row.getCell(1).getNumericCellValue());
        	p.setLokasi_store_asal(row.getCell(2).getStringCellValue());
        	p.setId_store_tujuan((int)row.getCell(3).getNumericCellValue());
        	p.setLokasi_store_tujuan(row.getCell(4).getStringCellValue());
    		p.setArtikel(row.getCell(5).getStringCellValue());
    		p.setKategori(row.getCell(6).getStringCellValue());
			p.setTipe(row.getCell(7).getStringCellValue());
			p.setNama_barang(row.getCell(8).getStringCellValue());
			p.setKuantitas(row.getCell(9).getNumericCellValue());
			p.setUkuran(row.getCell(10).getStringCellValue());
			p.setHpp(row.getCell(11).getNumericCellValue());
			p.setHarga_jual(row.getCell(12).getNumericCellValue());
    		p.setRowstatus(1);
    		p.setTanggal_pengiriman(row.getCell(0).getDateCellValue());
    		eRepo.save(p);
        }
        
    }
    
    @GetMapping("/delete")
    public String deletePengirimanStore(
    		@RequestParam("id") Long id,@RequestParam("id_store_asal") int id_store_asal,
    		@RequestParam("id_store_tujuan") int id_store_tujuan, @RequestParam("artikel") String artikel, @RequestParam("pengiriman_code") String pengiriman_code)
    {
    	pengirimanStoreService.deletePengirimanStoreById(id,id_store_asal,id_store_tujuan,artikel,pengiriman_code);
    	return "redirect:/all";
    }

}
