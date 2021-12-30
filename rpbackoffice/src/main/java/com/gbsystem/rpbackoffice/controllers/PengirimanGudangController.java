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

import com.gbsystem.rpbackoffice.entities.PengirimanGudang;
import com.gbsystem.rpbackoffice.repository.PengirimanGudangRepository;
import com.gbsystem.rpbackoffice.services.PengirimanGudangService;

@RestController
@RequestMapping("/pengirimanGudang")
@CrossOrigin
public class PengirimanGudangController {
	
	@Autowired
	private PengirimanGudangService pengirimanGudangService;
	
	@Autowired
	private PengirimanGudangRepository eRepo;

    @GetMapping("/all")
	public ResponseEntity<List<PengirimanGudang>> getAll() {
        return new ResponseEntity<>(pengirimanGudangService.getAllPengirimanGudang(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PengirimanGudang>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(pengirimanGudangService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String saveProduct(
    		@RequestParam("nama_gudang") String nama_gudang, @RequestParam("lokasi_store") String lokasi_store,
    		@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori, @RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang, @RequestParam("kuantitas") double kuantitas, 
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang, 
    		@RequestParam("hpp") double hpp, @RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		pengirimanGudangService.savePengirimanGudang(nama_gudang, lokasi_store, artikel, kategori, tipe, nama_barang, 
    				kuantitas, ukuran, foto_barang, hpp, harga_jual);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(@RequestParam("id") Long id, @RequestParam("tanggal_pengiriman") Date tanggal_pengiriman,
    		@RequestParam("nama_gudang") String nama_gudang, @RequestParam("lokasi_store") String lokasi_store,
    		@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori, @RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang, @RequestParam("kuantitas") double kuantitas, 
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang, 
    		@RequestParam("hpp") double hpp, @RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		pengirimanGudangService.update(id, tanggal_pengiriman, nama_gudang, lokasi_store, artikel, kategori, tipe, nama_barang, 
    				kuantitas, ukuran, foto_barang, hpp, harga_jual);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
        	PengirimanGudang p = new PengirimanGudang();
        	XSSFRow row = worksheet.getRow(i);
        	p.setNama_gudang(row.getCell(1).getStringCellValue());
        	p.setLokasi_store(row.getCell(2).getStringCellValue());
    		p.setArtikel(row.getCell(3).getStringCellValue());
    		p.setKategori(row.getCell(4).getStringCellValue());
			p.setTipe(row.getCell(5).getStringCellValue());
			p.setNama_barang(row.getCell(6).getStringCellValue());
			p.setKuantitas(row.getCell(7).getNumericCellValue());
			p.setUkuran(row.getCell(8).getStringCellValue());
			p.setHpp(row.getCell(9).getNumericCellValue());
			p.setHarga_jual(row.getCell(10).getNumericCellValue());
    		p.setRowstatus(1);
    		p.setTanggal_pengiriman(row.getCell(0).getDateCellValue());
    		eRepo.save(p);
        }
        
    }
    
    @GetMapping("/delete")
    public String deletePengirimanGudang(@RequestParam("id") Long id)
    {
    	pengirimanGudangService.deletePengirimanGudangById(id);
    	return "redirect:/all";
    }

}
