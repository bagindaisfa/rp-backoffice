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

import com.gbsystem.rpbackoffice.entities.StockOpname;
import com.gbsystem.rpbackoffice.repository.StockOpnameRepository;
import com.gbsystem.rpbackoffice.services.StockOpnameService;

@RestController
@RequestMapping("/stockOpname")
@CrossOrigin
public class StockOpnameController {
	
	@Autowired
	private StockOpnameService stockOpnameService;
	
	@Autowired
	private StockOpnameRepository eRepo;

    @GetMapping("/all")
	public ResponseEntity<List<StockOpname>> getAll() {
        return new ResponseEntity<>(stockOpnameService.getAllStockOpname(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<StockOpname>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(stockOpnameService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add")
    public @ResponseBody String saveProduct(@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("kuantitas") double kuantitas,@RequestParam("ukuran") String ukuran,
    		@RequestParam("hpp") double hpp,@RequestParam("harga_jual") double harga_jual,
    		@RequestParam("keterangan") String keterangan) throws Exception {
    	
    	if (artikel != "") {
    		stockOpnameService.saveStockOpname(artikel, kategori, tipe, nama_barang, kuantitas, ukuran, hpp, harga_jual, keterangan);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update")
    public @ResponseBody String update(@RequestParam("id") Long id,@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("kuantitas") double kuantitas,@RequestParam("ukuran") String ukuran,
    		@RequestParam("hpp") double hpp,@RequestParam("harga_jual") double harga_jual,
    		@RequestParam("keterangan") String keterangan) throws Exception {
    	
    	if (artikel != "") {
    		stockOpnameService.update(id, artikel, kategori, tipe, nama_barang, kuantitas, ukuran, hpp, harga_jual, keterangan);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
        	StockOpname p = new StockOpname();
        	XSSFRow row = worksheet.getRow(i);
    		p.setArtikel(row.getCell(0).getStringCellValue());
    		p.setKategori(row.getCell(4).getStringCellValue());
			p.setTipe(row.getCell(7).getStringCellValue());
			p.setNama_barang(row.getCell(6).getStringCellValue());
			p.setKuantitas(row.getCell(5).getNumericCellValue());
			p.setUkuran(row.getCell(8).getStringCellValue());
			p.setHpp(row.getCell(1).getNumericCellValue());
    		p.setHarga_jual(row.getCell(2).getNumericCellValue());
    		p.setTotal_hpp((row.getCell(5).getNumericCellValue()) * (row.getCell(1).getNumericCellValue()));
    		p.setKeterangan(null);
    		p.setRowstatus(1);
    		p.setTanggal_transaksi(new Date());
    		eRepo.save(p);
        }
        
    }
    
    @GetMapping("/delete")
    public String deleteStockOpname(@RequestParam("id") Long id)
    {
    	stockOpnameService.deleteStockOpnameById(id);
    	return "redirect:/all";
    }
}
