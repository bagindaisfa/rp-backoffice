package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.gbsystem.rpbackoffice.entities.MasterTipe;
import com.gbsystem.rpbackoffice.repository.MasterTipeRepository;
import com.gbsystem.rpbackoffice.services.MasterTipeService;

@RestController
@RequestMapping("/master/tipe")
@CrossOrigin
public class MasterTipeController {
	@Autowired
	private MasterTipeService masterTipeService;
	
	@Autowired
	private MasterTipeRepository eRepo;

    @GetMapping("/all")
	public ResponseEntity<List<MasterTipe>> getAll() {
        return new ResponseEntity<>(masterTipeService.getAllMasterTipe(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MasterTipe>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(masterTipeService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public String saveMaster_tipe(@RequestParam("type_name") String type_name) 
	{
    	masterTipeService.saveMasterTipe(type_name);
		return "redirect:/tipe";
	}
    
    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,@RequestParam("type_name") String type_name) throws Exception {
    	
    	if (type_name != "") {
    		masterTipeService.update(id, type_name);
    	}
    	return "Update Data Successs!";
		
    }
    @GetMapping("/delete")
    public String deleteTipe(@RequestParam("id") Long id)
    {
    	
    	masterTipeService.deleteMasterTipeById(id);
    	return "redirect:/kategori";
    }
    
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws Exception {
    	
        XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for(int i=1; i<worksheet.getPhysicalNumberOfRows(); i++) {
            MasterTipe p = new MasterTipe();
        	XSSFRow row = worksheet.getRow(i);
        	DataFormatter formatter = new DataFormatter();
    		p.setType_name(formatter.formatCellValue(row.getCell(0)));
    		p.setRowstatus(1);
        	
    		eRepo.save(p);
        }
        
    }
}
