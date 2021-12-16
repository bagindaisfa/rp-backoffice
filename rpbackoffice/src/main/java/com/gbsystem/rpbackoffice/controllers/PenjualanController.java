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
	public String savePenjualan(@RequestParam("tanggal_transaksi") Date tanggal_transaksi,
			@RequestParam("id_transaksi") String id_transaksi,
			@RequestParam("id_store") String id_store,
			@RequestParam("lokasi_store") String lokasi_store,
			@RequestParam("kuantitas") double kuantitas,
			@RequestParam("total") double total) 
	{
		penjualanService.savePenjualan(tanggal_transaksi, id_transaksi, id_store, lokasi_store, kuantitas, total);
		return "redirect:/penjualan";
	}
	
	@PostMapping(value = "/update")
    public @ResponseBody String update(@RequestParam("id") Long id, @RequestParam("tanggal_transaksi") Date tanggal_transaksi, 
    		@RequestParam("id_transaksi") String id_transaksi,
    		@RequestParam("id_store") String id_store,@RequestParam("lokasi_store") String lokasi_store,
    		@RequestParam("kuantitas") double kuantitas,@RequestParam("total") double total) throws Exception {
    	
    	if (id_transaksi != "") {
    		penjualanService.update(id, tanggal_transaksi, id_transaksi, id_store, lokasi_store, kuantitas, total);
    	}
    	return "Update Data Successs!";
		
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
    	
    	penjualanService.deletePenjualanById(id);
    	return "redirect:/penjualanStore";
    }
	
//	@PostMapping("/changeTanggal_transaksi")
//    public String changeTanggal_transaksi(@RequestParam("id") Long id ,@RequestParam("newTanggal_transaksi") Date tanggal_transaksi)
//    {
//    	penjualanService.changePenjualanTanggal_transaksi(id, tanggal_transaksi);
//    	return "redirect:/penjualanStore"; 
//    }
//	@PostMapping("/changeId_transaksi")
//    public String changeId_transaksi(@RequestParam("id") Long id ,@RequestParam("newId_transaksi") String id_transaksi)
//    {
//    	penjualanService.changePenjualanId_transaksi(id, id_transaksi);
//    	return "redirect:/penjualanStore"; 
//    }
//	@PostMapping("/changeId_store")
//    public String changeId_store(@RequestParam("id") Long id ,@RequestParam("newId_store") String id_store)
//    {
//    	penjualanService.changePenjualanId_store(id, id_store);
//    	return "redirect:/penjualanStore"; 
//    }
//	@PostMapping("/changeLokasi_store")
//    public String changeLokasi_store(@RequestParam("id") Long id ,@RequestParam("newLokasi_store") String lokasi_store)
//    {
//    	penjualanService.changePenjualanLokasi_store(id, lokasi_store);
//    	return "redirect:/penjualanStore"; 
//    }
//	@PostMapping("/changeKuantitas")
//    public String changeKuantitas(@RequestParam("id") Long id ,@RequestParam("newKuantitas") int kuantitas)
//    {
//    	penjualanService.changePenjualanKuantitas(id, kuantitas);
//    	return "redirect:/penjualanStore"; 
//    }
//	@PostMapping("/changeTotal")
//    public String changeTotal(@RequestParam("id") Long id ,@RequestParam("newTotal") double total)
//    {
//    	penjualanService.changePenjualanTotal(id, total);
//    	return "redirect:/penjualanStore"; 
//    }
    
}
