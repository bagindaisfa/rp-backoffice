package com.gbsystem.rpbackoffice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.gbsystem.rpbackoffice.entities.Pelanggan;
import com.gbsystem.rpbackoffice.repository.PelangganRepository;
import com.gbsystem.rpbackoffice.services.PelangganService;

@RestController
@RequestMapping("/pelanggan")
@CrossOrigin
public class PelangganController {
	
	@Autowired
	private PelangganService pelangganService;
	
	@Autowired
	private PelangganRepository eRepo;

    @GetMapping("/all")
	public ResponseEntity<List<Pelanggan>> getAll() {
        return new ResponseEntity<>(pelangganService.getAllPelanggan(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Pelanggan>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(pelangganService.search(keyword), HttpStatus.OK);
    }
    
    @GetMapping("/download")
    public ResponseEntity<String> download() {
    	pelangganService.download();
    	return new ResponseEntity<>("OK GAN", HttpStatus.OK);
    }
    
    @PostMapping(value = "/add")
    public @ResponseBody String saveProduct(@RequestParam("nama_pelanggan") String nama_pelanggan,
    		@RequestParam("no_hp") String no_hp,@RequestParam("email") String email,@RequestParam("alamat") String alamat,
    		@RequestParam("total_kunjungan") double total_kunjungan,@RequestParam("kuantitas") double kuantitas,
    		@RequestParam("poin") double poin,@RequestParam("pembelian") double total_pembelian) throws Exception {
    	
    	if (nama_pelanggan != "") {
    		pelangganService.savePelanggan(nama_pelanggan, no_hp, email, alamat, total_kunjungan,kuantitas, poin, total_pembelian);
    	}
    	return "Insert Data Successs!";
    	}
    
    @PostMapping(value = "/addMobile")
    public Map savePelangganMobile(@RequestBody Pelanggan pelanggan) {
    	
    	pelangganService.savePelangganMobile(pelanggan);
    	Map<String,String> response = new HashMap<>();
    	response.put("message", "Insert Success");
    	return response;
		
    }
    
    @PostMapping(value = "/update")
    public @ResponseBody String update(@RequestParam("id") Long id,@RequestParam("nama_pelanggan") String nama_pelanggan,
    		@RequestParam("no_hp") String no_hp,@RequestParam("email") String email,@RequestParam("alamat") String alamat,
    		@RequestParam("total_kunjungan") double total_kunjungan,@RequestParam("kuantitas") double kuantitas,
    		@RequestParam("poin") double poin,@RequestParam("pembelian") double total_pembelian) throws Exception {
    	
    	if (nama_pelanggan != "") {
    		pelangganService.update(id, nama_pelanggan, no_hp, email, alamat, total_kunjungan,
    				kuantitas, poin, total_pembelian);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/updateMobile")
    public Map updatePelangganMobile(@RequestBody Pelanggan pelanggan) {
    	
    	pelangganService.updateMobile(pelanggan);
    	Map<String,String> response = new HashMap<>();
    	response.put("message", "Update Success");
    	return response;
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Pelanggan p = new Pelanggan();
        	XSSFRow row = worksheet.getRow(i);
    		p.setNama_pelanggan(row.getCell(1).getStringCellValue());
    		p.setNo_hp(row.getCell(2).getStringCellValue());
    		p.setEmail(row.getCell(3).getStringCellValue());
    		p.setAlamat(row.getCell(4).getStringCellValue());
    		p.setTotal_kunjungan(row.getCell(5).getNumericCellValue());
    		p.setKuantitas(row.getCell(6).getNumericCellValue());
    		p.setPoin(row.getCell(7).getNumericCellValue());
    		p.setTotal_pembelian(row.getCell(8).getNumericCellValue());
    		p.setRowstatus(1);
    		p.setTanggal_join(row.getCell(0).getDateCellValue());
        	eRepo.save(p);
        }
        
    }
    
    @GetMapping("/delete")
    public String deletePelanggan(@RequestParam("id") Long id)
    {
    	
    	pelangganService.deletePelangganById(id);
    	return "redirect:/pelanggan/all";
    }

    @GetMapping("/totalPoin")
	public Double totalPoin(@Param("nama_pelanggan") String nama_pelanggan,@Param("no_hp_pelanggan") String no_hp_pelanggan) {
        return pelangganService.totalPoin(nama_pelanggan,no_hp_pelanggan);
    }
}