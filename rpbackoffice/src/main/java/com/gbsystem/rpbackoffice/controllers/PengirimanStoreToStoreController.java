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

import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStore;
import com.gbsystem.rpbackoffice.services.PengirimanStoreToStoreService;

@RestController
@RequestMapping("/pengirimanStore")
@CrossOrigin
public class PengirimanStoreToStoreController {
	
	@Autowired
	private PengirimanStoreToStoreService pengirimanStoreService;

    @GetMapping("/all")
	public ResponseEntity<List<PengirimanStoreToStore>> getAll() {
        return new ResponseEntity<>(pengirimanStoreService.getAllPengirimanStore(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PengirimanStoreToStore>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(pengirimanStoreService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    @ResponseBody
    public PengirimanStoreToStore saveProduct(@RequestBody PengirimanStoreToStore pengirimanStoreToStore) throws Exception {
    	
    	PengirimanStoreToStore pengirimanStoreToStoreResponse = pengirimanStoreService.savePengirimanStore(pengirimanStoreToStore);
    	return pengirimanStoreToStoreResponse;
		
    }
    
    @PostMapping("/update")
    @ResponseBody
    public PengirimanStoreToStore update(@RequestBody PengirimanStoreToStore pengirimanStoreToStore) throws Exception {
    	
    	PengirimanStoreToStore pengirimanStoreToStoreResponse = pengirimanStoreService.update(pengirimanStoreToStore);
    	return pengirimanStoreToStoreResponse;
		
    }
    
    @GetMapping("/delete")
    public PengirimanStoreToStore deletePengirimanStore(@RequestParam("id") Long id)
    {
    	PengirimanStoreToStore pengirimanStoreToStoreResponse = pengirimanStoreService.deletePengirimanStoreById(id);
    	return pengirimanStoreToStoreResponse;
    }

}
