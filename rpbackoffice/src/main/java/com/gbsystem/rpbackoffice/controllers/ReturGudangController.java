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

import com.gbsystem.rpbackoffice.entities.ReturGudang;
import com.gbsystem.rpbackoffice.repository.ReturGudangRepository;
import com.gbsystem.rpbackoffice.services.ReturGudangService;

@RestController
@RequestMapping("/returGudang")
@CrossOrigin
public class ReturGudangController {
	
	@Autowired
	private ReturGudangService returGudangService;
	
	@Autowired
	private ReturGudangRepository eRepo;

    @GetMapping("/all")
	public ResponseEntity<List<ReturGudang>> getAll() {
        return new ResponseEntity<>(returGudangService.getAllReturGudang(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ReturGudang>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(returGudangService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String saveProduct(@RequestParam("id_store_asal") int id_store_asal,
    		@RequestParam("lokasi_store_asal") String lokasi_store_asal,@RequestParam("id_office_tujuan") int id_office_tujuan,
    		@RequestParam("lokasi_office_tujuan") String lokasi_office_tujuan,@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori, @RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang, @RequestParam("kuantitas") double kuantitas, 
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang, 
    		@RequestParam("hpp") double hpp, @RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		returGudangService.saveReturGudang(id_store_asal, lokasi_store_asal, 
    				id_office_tujuan,lokasi_office_tujuan,artikel, kategori, tipe, nama_barang, kuantitas, ukuran,foto_barang, hpp, harga_jual);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(
    		@RequestParam("id") Long id,@RequestParam("pengiriman_code") String pengiriman_code,
    		@RequestParam("tanggal_retur") Date tanggal_retur, @RequestParam("id_store_asal") int id_store_asal,
    		@RequestParam("lokasi_store_asal") String lokasi_store_asal,@RequestParam("id_office_tujuan") int id_office_tujuan,
    		@RequestParam("lokasi_office_tujuan") String lokasi_office_tujuan,@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori, @RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang, @RequestParam("kuantitas") double kuantitas, 
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang, 
    		@RequestParam("hpp") double hpp, @RequestParam("harga_jual") double harga_jual) throws Exception {
    	
    	if (artikel != "") {
    		returGudangService.update(id, pengiriman_code, tanggal_retur, id_store_asal, lokasi_store_asal, 
    				id_office_tujuan,lokasi_office_tujuan,artikel, kategori, tipe, nama_barang, kuantitas, ukuran,foto_barang, hpp, harga_jual);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        String code_retur = "RT-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
        	ReturGudang p = new ReturGudang();
        	XSSFRow row = worksheet.getRow(i);
        	p.setPengiriman_code(code_retur);
    		p.setId_store_asal((int)row.getCell(1).getNumericCellValue());
    		p.setLokasi_store_asal(row.getCell(2).getStringCellValue());
    		p.setId_office_tujuan((int)row.getCell(3).getNumericCellValue());
    		p.setLokasi_office_tujuan(row.getCell(4).getStringCellValue());
    		p.setArtikel(row.getCell(5).getStringCellValue());
    		p.setKategori(row.getCell(6).getStringCellValue());
			p.setTipe(row.getCell(7).getStringCellValue());
			p.setNama_barang(row.getCell(8).getStringCellValue());
			p.setKuantitas(row.getCell(9).getNumericCellValue());
			p.setUkuran(row.getCell(10).getStringCellValue());
			p.setHpp(row.getCell(11).getNumericCellValue());
			p.setHarga_jual(row.getCell(12).getNumericCellValue());
    		p.setRowstatus(1);
    		p.setTanggal_retur(row.getCell(0).getDateCellValue());
    		eRepo.save(p);
        }
        
    }
    
    @GetMapping("/delete")
    public String deleteReturGudang(
    		@RequestParam("id") Long id,@RequestParam("id_office_tujuan") int id_office,
    		@RequestParam("id_store_asal") int id_store,@RequestParam("artikel") String artikel,@RequestParam("pengiriman_code") String pengiriman_code)
    {
    	returGudangService.deleteReturGudangById(id, id_office, id_store, artikel, pengiriman_code);
    	return "redirect:/all";
    }

}
