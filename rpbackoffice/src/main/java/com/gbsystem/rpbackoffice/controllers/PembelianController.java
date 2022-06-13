package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.gbsystem.rpbackoffice.entities.Pembelian;
import com.gbsystem.rpbackoffice.services.PembelianService;


@RestController
@RequestMapping("/pembelian")
@CrossOrigin
public class PembelianController {
	
	@Autowired
	private PembelianService pembelianService;
	
    @GetMapping("/all")
	public ResponseEntity<List<Pembelian>> getAll() {
        return new ResponseEntity<>(pembelianService.getAllPembelian(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Pembelian>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(pembelianService.search(keyword), HttpStatus.OK);
    }
    
    @GetMapping("/getPembelian")
    public ResponseEntity<Pembelian> getPembelian(@Param("pembelian_code") String pembelian_code) {
    	return new ResponseEntity<>(pembelianService.getPembelian(pembelian_code), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    @ResponseBody
    public Pembelian saveProduct(@RequestBody Pembelian pembelian) throws Exception {
    	
    	Pembelian pembelianResponse = pembelianService.savePembelian(pembelian);
    	return pembelianResponse;
		
    }
    
    @PostMapping("/update")
    @ResponseBody
    public Pembelian update(@RequestBody Pembelian pembelian) throws Exception {
    	
    	Pembelian pembelianResponse = pembelianService.update(pembelian);
    	return pembelianResponse;
		
    }
    
    /*@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
        
    }*/
    
    @GetMapping("/deletePembelian")
    public Pembelian deletePembelian(@RequestParam("id") Long id)
    {
    	
    	Pembelian pembelianResponse = pembelianService.deletePembelianById(id);
    	return pembelianResponse;
    }
    
    
}
