package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.PengirimanOfficeToStore;
import com.gbsystem.rpbackoffice.services.PengirimanOfficeToStoreService;

@RestController
@RequestMapping("/pengirimanOffice")
@CrossOrigin
public class PengirimanOfficeToStoreController {
	
	@Autowired
	private PengirimanOfficeToStoreService pengirimanGudangService;

    @GetMapping("/all")
	public ResponseEntity<List<PengirimanOfficeToStore>> getAll() {
        return new ResponseEntity<>(pengirimanGudangService.getAllPengirimanGudang(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PengirimanOfficeToStore>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(pengirimanGudangService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    @ResponseBody
    public PengirimanOfficeToStore saveProduct(@RequestBody PengirimanOfficeToStore pengirimanOfficeToStore) throws Exception {
    	PengirimanOfficeToStore pengirimanResponse = pengirimanGudangService.savePengirimanOffice(pengirimanOfficeToStore);
    	return pengirimanResponse;
    	
    }
    
    @PostMapping("/update")
    @ResponseBody
    public PengirimanOfficeToStore update(@RequestBody PengirimanOfficeToStore pengirimanOfficeToStore) throws Exception {
    	
    	PengirimanOfficeToStore pengirimanResponse = pengirimanGudangService.update(pengirimanOfficeToStore);
    	return pengirimanResponse;
		
    }
    
    @GetMapping("/delete")
    public String deletePengirimanGudang(@RequestParam("id") Long id)
    {
    	pengirimanGudangService.deletePengirimanGudangById(id);
    	return "redirect:/all";
    }

}
