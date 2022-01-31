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

import com.gbsystem.rpbackoffice.entities.Karyawan;
import com.gbsystem.rpbackoffice.repository.KaryawanRepository;
import com.gbsystem.rpbackoffice.services.KaryawanService;

@RestController
@RequestMapping("/karyawan")
@CrossOrigin
public class KaryawanController {
	
	@Autowired
	private KaryawanService karyawanService;
	
	@Autowired
	private KaryawanRepository eRepo;

    @GetMapping("/all")
	public ResponseEntity<List<Karyawan>> getAll() {
        return new ResponseEntity<>(karyawanService.getAllKaryawan(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Karyawan>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(karyawanService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String saveProduct(@RequestParam("nama_karyawan") String nama_karyawan,
    		@RequestParam("id_office") int id_office,@RequestParam("lokasi_office") String lokasi_office,
    		@RequestParam("id_store") int id_store, @RequestParam("lokasi_store") String lokasi_store,@RequestParam("jabatan") String jabatan,
    		@RequestParam("no_hp") String no_hp,@RequestParam("email") String email,@RequestParam("alamat") String alamat,
    		@RequestParam("total_transaksi") double total_transaksi, @RequestParam("image") MultipartFile image) throws Exception {
    	
    	if (nama_karyawan != "") {
    		karyawanService.saveKaryawan(nama_karyawan, id_office, lokasi_office, id_store, lokasi_store, jabatan,
    				no_hp, email, alamat, total_transaksi, image);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(@RequestParam("id") Long id,@RequestParam("nama_karyawan") String nama_karyawan,
    		@RequestParam("id_office") int id_office,@RequestParam("lokasi_office") String lokasi_office,
    		@RequestParam("id_store") int id_store, @RequestParam("lokasi_store") String lokasi_store,@RequestParam("jabatan") String jabatan,
    		@RequestParam("no_hp") String no_hp,@RequestParam("email") String email,@RequestParam("alamat") String alamat,
    		@RequestParam("total_transaksi") double total_transaksi, @RequestParam("image") MultipartFile image) throws Exception {
    	
    	if (nama_karyawan != "") {
    		karyawanService.update(id, nama_karyawan, id_office, lokasi_office, id_store, lokasi_store, jabatan,
    				no_hp, email, alamat, total_transaksi, image);
    	}
    	return "Update Data Successs!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Karyawan k = new Karyawan();
        	XSSFRow row = worksheet.getRow(i);
    		k.setNama_karyawan(row.getCell(1).getStringCellValue());
    		k.setLokasi_office(row.getCell(2).getStringCellValue());
    		k.setJabatan(row.getCell(3).getStringCellValue());
    		k.setNo_hp(row.getCell(4).getStringCellValue());
    		k.setEmail(row.getCell(5).getStringCellValue());
    		k.setAlamat(row.getCell(6).getStringCellValue());
    		k.setTotal_transaksi(row.getCell(7).getNumericCellValue());
    		k.setRowstatus(1);
    		k.setTanggal_join(row.getCell(0).getDateCellValue());
        	eRepo.save(k);
        }
        
    }
    
    @GetMapping("/delete")
    public String deleteKaryawan(@RequestParam("id") Long id)
    {
    	
    	karyawanService.deleteKaryawanById(id);
    	return "redirect:/karyawan/all";
    }

    @PostMapping("/pindahStore")
    public Map pindahStore(@RequestBody Karyawan karyawan) {
    	karyawanService.pindahStore(karyawan);
    	Map<String,String> response = new HashMap<>();
    	response.put("message", "Insert Success");
    	return response;
    }
}
