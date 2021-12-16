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

import com.gbsystem.rpbackoffice.entities.PenjualanOffice;
import com.gbsystem.rpbackoffice.repository.PenjualanOfficeRepository;
import com.gbsystem.rpbackoffice.services.PenjualanOfficeService;

@RestController
@RequestMapping("/office")
@CrossOrigin
public class PenjualanOfficeController {

	@Autowired
	private PenjualanOfficeService penjualanOfficeService;
	
	@Autowired
	private PenjualanOfficeRepository eRepo;
	
	@GetMapping("/penjualan")
	public ResponseEntity<List<PenjualanOffice>> getAll(){
		return new ResponseEntity<>(penjualanOfficeService.getAllPenjualan(), HttpStatus.OK);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<PenjualanOffice>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penjualanOfficeService.search(keyword), HttpStatus.OK);
    }
	
	@PostMapping("/add")
	public String savePenjualan_office(@RequestParam("tanggal_transaksi") Date tanggal_transaksi,
			@RequestParam("id_transaksi") String id_transaksi,
			@RequestParam("id_office") String id_office,
			@RequestParam("lokasi_office") String lokasi_office,
			@RequestParam("kuantitas") double kuantitas,
			@RequestParam("total") double total) 
	{
		penjualanOfficeService.savePenjualanOffice(tanggal_transaksi, id_transaksi, id_office, lokasi_office, kuantitas, total);
		return "redirect:/penjualan";
	}
	
	@PostMapping(value = "/update")
    public @ResponseBody String update(@RequestParam("id") Long id, @RequestParam("tanggal_transaksi") Date tanggal_transaksi, 
    		@RequestParam("id_transaksi") String id_transaksi,
    		@RequestParam("id_office") String id_office,@RequestParam("lokasi_office") String lokasi_office,
    		@RequestParam("kuantitas") double kuantitas,@RequestParam("total") double total) throws Exception {
    	
    	if (id_transaksi != "") {
    		penjualanOfficeService.update(id, tanggal_transaksi, id_transaksi, id_office, lokasi_office, kuantitas, total);
    	}
    	return "Update Data Successs!";
		
    }
	
	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
        	PenjualanOffice p = new PenjualanOffice();
        	XSSFRow row = worksheet.getRow(i);
        	p.setId_transaksi(row.getCell(1).getStringCellValue());
        	p.setId_office(row.getCell(2).getStringCellValue());
        	p.setLokasi_office(row.getCell(3).getStringCellValue());
			p.setKuantitas(row.getCell(4).getNumericCellValue());
			p.setTotal(row.getCell(5).getNumericCellValue());
    		p.setRowstatus(1);
    		p.setTanggal_transaksi(row.getCell(0).getDateCellValue());
    		eRepo.save(p);
        }
    }
	
	@GetMapping("/delete")
    public String deletePenjualan(@RequestParam("id") Long id)
    {
    	
    	penjualanOfficeService.deletePenjualanById(id);
    	return "redirect:/penjualan";
    }
	
//	@PostMapping("/changeTanggal_transaksi")
//    public String changeTanggal_transaksi(@RequestParam("id") Long id ,@RequestParam("newTanggal_transaksi") Date tanggal_transaksi)
//    {
//    	penjualanOfficeService.changePenjualanTanggal_transaksi(id, tanggal_transaksi);
//    	return "redirect:/penjualan"; 
//    }
//	@PostMapping("/changeId_transaksi")
//    public String changeId_transaksi(@RequestParam("id") Long id ,@RequestParam("newId_transaksi") String id_transaksi)
//    {
//    	penjualanOfficeService.changePenjualanId_transaksi(id, id_transaksi);
//    	return "redirect:/penjualan"; 
//    }
//	@PostMapping("/changeId_office")
//    public String changeId_office(@RequestParam("id") Long id ,@RequestParam("newId_office") String id_office)
//    {
//    	penjualanOfficeService.changePenjualanId_office(id, id_office);
//    	return "redirect:/penjualan"; 
//    }
//	@PostMapping("/changeLokasi_office")
//    public String changeLokasi_office(@RequestParam("id") Long id ,@RequestParam("newLokasi_office") String lokasi_office)
//    {
//    	penjualanOfficeService.changePenjualanLokasi_office(id, lokasi_office);
//    	return "redirect:/penjualan"; 
//    }
//	@PostMapping("/changeKuantitas")
//    public String changeKuantitas(@RequestParam("id") Long id ,@RequestParam("newKuantitas") int kuantitas)
//    {
//    	penjualanOfficeService.changePenjualanKuantitas(id, kuantitas);
//    	return "redirect:/penjualan"; 
//    }
//	@PostMapping("/changeTotal")
//    public String changeTotal(@RequestParam("id") Long id ,@RequestParam("newTotal") double total)
//    {
//    	penjualanOfficeService.changePenjualanTotal(id, total);
//    	return "redirect:/penjualan"; 
//    }
    
}
