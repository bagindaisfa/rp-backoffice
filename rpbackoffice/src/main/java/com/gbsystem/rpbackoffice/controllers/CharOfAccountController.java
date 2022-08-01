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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.gbsystem.rpbackoffice.entities.ChartOfAccount;
import com.gbsystem.rpbackoffice.repository.ChartOfAccountRepository;
import com.gbsystem.rpbackoffice.services.ChartOfAccountService;

@RestController
@RequestMapping("/akutansi/coa")
@CrossOrigin
public class CharOfAccountController {
	@Autowired
	private ChartOfAccountService chartOfAccountService;
	
	@Autowired
	private ChartOfAccountRepository eRepo;
	
    @GetMapping("/all")
	public ResponseEntity<List<ChartOfAccount>> getAll() {
        return new ResponseEntity<>(chartOfAccountService.getAllChartOfAccount(), HttpStatus.OK);
    }
    
    @GetMapping("/dropdown")
	public ResponseEntity<List<ChartOfAccount>> getAllByNo_akun(@RequestParam List<String> noAkun) {
    	
        return new ResponseEntity<>(chartOfAccountService.getChartOfAccount(noAkun), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ChartOfAccount>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(chartOfAccountService.search(keyword), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public @ResponseBody String saveChartOfAccount(
    		@RequestParam("noAkun") String noAkun,
    		@RequestParam("nama_akun") String namaAkun,
    		@RequestParam("kelompok") String kelompok,
    		@RequestParam("tipe") String tipe,
    		@RequestParam("saldo_normal") String saldo_normal,
    		@RequestParam("saldo_awal") double saldo_awal
    		) 
	{
    	chartOfAccountService.saveChartOfAccount(noAkun,namaAkun,kelompok,tipe,saldo_normal, saldo_awal);
		return "Success!";
	}
    
    @PostMapping(value = "/update")
    public @ResponseBody String update(
    		@RequestParam("id") Long id,
    		@RequestParam("noAkun") String noAkun,
    		@RequestParam("nama_akun") String namaAkun,
    		@RequestParam("kelompok") String kelompok,
    		@RequestParam("tipe") String tipe,
    		@RequestParam("saldo_normal") String saldo_normal,
    		@RequestParam("saldo_awal") double saldo_awal
    		) throws Exception {
    	
    	if (namaAkun != "") {
    		chartOfAccountService.update(id, noAkun,namaAkun,kelompok,tipe,saldo_normal, saldo_awal);
    	}
    	return "Update Data Success!";
		
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        
        for(int i=1; i<worksheet.getPhysicalNumberOfRows(); i++) {
        	ChartOfAccount chartOfAccount = new ChartOfAccount();
        	XSSFRow row = worksheet.getRow(i);
        	chartOfAccount.setNoAkun(row.getCell(0).getStringCellValue());
        	chartOfAccount.setNamaAkun(row.getCell(1).getStringCellValue());
        	chartOfAccount.setKelompok(row.getCell(2).getStringCellValue());
        	chartOfAccount.setTipe(row.getCell(3).getStringCellValue());
        	chartOfAccount.setSaldo_normal(row.getCell(4).getStringCellValue());
        	chartOfAccount.setSaldo_awal(row.getCell(6).getNumericCellValue());
        	chartOfAccount.setRowstatus(1);
    		eRepo.save(chartOfAccount);
        }
        
    }
    
    @GetMapping("/delete")
    public String deleteChartOfAccount(@RequestParam("id") Long id)
    {
    	
    	chartOfAccountService.deleteChartOfAccountById(id);
    	return "redirect:/akutansi/coa/all";
    }
}
