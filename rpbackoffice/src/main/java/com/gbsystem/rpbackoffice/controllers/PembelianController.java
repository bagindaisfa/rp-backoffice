package com.gbsystem.rpbackoffice.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.gbsystem.rpbackoffice.entities.Pembelian;
import com.gbsystem.rpbackoffice.repository.PembelianRepository;
import com.gbsystem.rpbackoffice.services.PembelianService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class PembelianController {
	
	@Autowired
	private PembelianService pembelianService;
	
	@Autowired
	private PembelianRepository eRepo;

    @GetMapping("/pembelian")
	public ResponseEntity<List<Pembelian>> getAll() {
        return new ResponseEntity<>(pembelianService.getAllPembelian(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Pembelian>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(pembelianService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/addPembelian", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String saveProduct(@RequestParam("image") MultipartFile image,@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("kuantitas") double kuantitas,@RequestParam("ukuran") String ukuran,
    		@RequestParam("hpp") double hpp,@RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		pembelianService.savePembelian(image, artikel, kategori, tipe, nama_barang, kuantitas, ukuran, hpp, harga_jual);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(@RequestParam("id") Long id,@RequestParam("image") MultipartFile image,@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("kuantitas") double kuantitas,@RequestParam("ukuran") String ukuran,
    		@RequestParam("hpp") double hpp,@RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		pembelianService.update(id, image, artikel, kategori, tipe, nama_barang, kuantitas, ukuran, hpp, harga_jual);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Pembelian pembelian = new Pembelian();
        	XSSFRow row = worksheet.getRow(i);
        	pembelian.setImage(null);
    		pembelian.setArtikel(row.getCell(0).getStringCellValue());
    		pembelian.setKategori(row.getCell(4).getStringCellValue());
			pembelian.setTipe(row.getCell(7).getStringCellValue());
			pembelian.setNama_barang(row.getCell(6).getStringCellValue());
			pembelian.setKuantitas(row.getCell(5).getNumericCellValue());
			pembelian.setUkuran(row.getCell(8).getStringCellValue());
			pembelian.setHpp(row.getCell(1).getNumericCellValue());
    		pembelian.setHarga_jual(row.getCell(2).getNumericCellValue());
    		pembelian.setTotal_hpp((row.getCell(5).getNumericCellValue()) * (row.getCell(1).getNumericCellValue()));
    		pembelian.setRowstatus(1);
    		pembelian.setTanggal_transaksi(new Date());
    		eRepo.save(pembelian);
        }
        
    }
    
    @GetMapping("/deletePembelian")
    public String deletePembelian(@RequestParam("id") Long id)
    {
    	
    	pembelianService.deletePembelianById(id);
    	return "redirect:/pembelian";
    }
    
    
}
