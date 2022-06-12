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

import com.gbsystem.rpbackoffice.entities.PenerimaanOfficeFromStore;
import com.gbsystem.rpbackoffice.services.PenerimaanOfficeFromStoreService;

@RestController
@RequestMapping("/penerimaanStore")
@CrossOrigin
public class PenerimaanOfficeFromStoreController {
	
	@Autowired
	private PenerimaanOfficeFromStoreService penerimaanStoreService;
	
    @GetMapping("/all")
	public ResponseEntity<List<PenerimaanOfficeFromStore>> getAll() {
        return new ResponseEntity<>(penerimaanStoreService.getAllPenerimaanStore(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PenerimaanOfficeFromStore>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penerimaanStoreService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add")
    @ResponseBody
    public PenerimaanOfficeFromStore saveProduct(@RequestBody PenerimaanOfficeFromStore penerimaanOfficeFromStore) throws Exception {
    	
    	PenerimaanOfficeFromStore response = penerimaanStoreService.savePenerimaanStore(penerimaanOfficeFromStore);
    	return response;
		
    }
    
    @PostMapping(value = "/update")
    @ResponseBody
    public String update(@RequestBody PenerimaanOfficeFromStore penerimaanOfficeFromStore) throws Exception {
    	
    	String response = penerimaanStoreService.update(penerimaanOfficeFromStore);
    	return response;
		
    }
    
    @GetMapping("/delete")
    public String deletePenerimaanStore(@RequestParam("id") Long id)
    {
    	String response = penerimaanStoreService.deletePenerimaanStoreById(id);
    	return response;
    }

}
