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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.Penjualan;
import com.gbsystem.rpbackoffice.repository.PenjualanRepository;
import com.gbsystem.rpbackoffice.services.PenjualanService;

@RestController
@RequestMapping("/store")
@CrossOrigin
public class PenjualanController {

	@Autowired
	private PenjualanService penjualanService;
	
	@Autowired
	private PenjualanRepository eRepo;
	
	@GetMapping("/penjualan")
	public ResponseEntity<List<Penjualan>> getAll(){
		return new ResponseEntity<>(penjualanService.getAllPenjualan(), HttpStatus.OK);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<Penjualan>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penjualanService.search(keyword), HttpStatus.OK);
    }
	
	@PostMapping("/add")
	@ResponseBody
	public Penjualan savePenjualan(@RequestBody Penjualan penjualan) 
	{
		Penjualan penjualanResponse = penjualanService.savePenjualan(penjualan);
		return penjualanResponse;
	}
	
	@PostMapping(value = "/update")
	@ResponseBody
    public Penjualan update(@RequestBody Penjualan penjualan) throws Exception {
    	
		Penjualan penjualanResponse = penjualanService.update(penjualan);
    	return penjualanResponse;
		
    }
	
	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
        	Penjualan p = new Penjualan();
        	XSSFRow row = worksheet.getRow(i);
        	p.setId_transaksi(row.getCell(1).getStringCellValue());
        	p.setId_store(row.getCell(2).getStringCellValue());
        	p.setLokasi_store(row.getCell(3).getStringCellValue());
        	p.setNama_pelanggan(row.getCell(4).getStringCellValue());
        	p.setNama_karyawan(row.getCell(5).getStringCellValue());
        	p.setDiskon(row.getCell(6).getNumericCellValue());
        	p.setMetode_bayar(row.getCell(7).getStringCellValue());
        	p.setEkspedisi(row.getCell(8).getStringCellValue());
			p.setOngkir(row.getCell(9).getNumericCellValue());
			p.setTotal(row.getCell(10).getNumericCellValue());
			p.setKembalian(row.getCell(11).getNumericCellValue());
    		p.setRowstatus(1);
    		p.setTanggal_transaksi(row.getCell(0).getDateCellValue());
    		eRepo.save(p);
        }
    }
	
	@PostMapping("/delete")
    public Penjualan deletePenjualan(@RequestParam("id") Long id)
    {
		Penjualan penjualanResponse = penjualanService.deletePenjualanById(id);
    	return penjualanResponse;
    }
	    
}
