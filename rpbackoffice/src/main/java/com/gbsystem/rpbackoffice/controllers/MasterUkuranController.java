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
import com.gbsystem.rpbackoffice.entities.MasterUkuran;
import com.gbsystem.rpbackoffice.services.MasterUkuranService;

@RestController
@RequestMapping("/master/ukuran")
@CrossOrigin
public class MasterUkuranController {
	@Autowired
	private MasterUkuranService masterUkuranService;
	

    @GetMapping("/all")
	public ResponseEntity<List<MasterUkuran>> getAll() {
        return new ResponseEntity<>(masterUkuranService.getAllMasterUkuran(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MasterUkuran>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(masterUkuranService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public String saveUkuran(@RequestParam("ukuran") String ukuran) 
	{
    	masterUkuranService.saveMasterUkuran(ukuran);
		return "redirect:/office";
	}
    
    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,@RequestParam("ukuran") String ukuran) throws Exception {
    	
    	if (ukuran != "") {
    		masterUkuranService.update(id, ukuran);
    	}
    	return "Update Data Successs!";
		
    }
    @GetMapping("/delete")
    public String deleteUkuran(@RequestParam("id") Long id)
    {
    	
    	masterUkuranService.deleteMasterUkuranById(id);
    	return "redirect:/office";
    }

}
