package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.gbsystem.rpbackoffice.entities.MasterOffice;
import com.gbsystem.rpbackoffice.services.MasterOfficeService;

@RestController
@RequestMapping("/master/office")
@CrossOrigin
public class MasterOfficeController {
	@Autowired
	private MasterOfficeService masterOfficeService;

    @GetMapping("/all")
	public ResponseEntity<List<MasterOffice>> getAll() {
        return new ResponseEntity<>(masterOfficeService.getAllMasterOffice(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MasterOffice>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(masterOfficeService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public String saveMaster_tipe(
    		@RequestParam("office_name") String office_name,@RequestParam("no_tlpn") String no_tlpn,
    		@RequestParam("alamat") String alamat, @RequestParam("kepala_office") String kepala_office ) 
	{
    	masterOfficeService.saveMasterOffice(office_name, no_tlpn, alamat, kepala_office);
		return "redirect:/office";
	}
    
    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,@RequestParam("office_name") String office_name,@RequestParam("no_tlpn") String no_tlpn,
    		@RequestParam("alamat") String alamat, @RequestParam("kepala_office") String kepala_office) throws Exception {
    	
    	if (office_name != "") {
    		masterOfficeService.update(id, office_name, no_tlpn, alamat, kepala_office);
    	}
    	return "Update Data Successs!";
		
    }
    @GetMapping("/delete")
    public String deleteTipe(@RequestParam("id") Long id)
    {
    	
    	masterOfficeService.deleteMasterOfficeById(id);
    	return "redirect:/office";
    }
}
