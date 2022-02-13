package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbsystem.rpbackoffice.entities.PenerimaanStoreFromStore;
import com.gbsystem.rpbackoffice.services.PenerimaanStoreFromStoreService;

public class PenerimaanStoreFromStoreController {
	
	@Autowired
	private PenerimaanStoreFromStoreService penerimaanFromStoreService;

    @GetMapping("/all")
	public ResponseEntity<List<PenerimaanStoreFromStore>> getAll() {
        return new ResponseEntity<>(penerimaanFromStoreService.getAllPenerimaanFromStore(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PenerimaanStoreFromStore>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penerimaanFromStoreService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    @ResponseBody
    public PenerimaanStoreFromStore saveProduct(@RequestBody PenerimaanStoreFromStore penerimaanStoreFromStore) throws Exception {
    	PenerimaanStoreFromStore pengirimanResponse = penerimaanFromStoreService.savePenerimaanStore(penerimaanStoreFromStore);
    	return pengirimanResponse;
    	
    }
    
    @PostMapping("/update")
    @ResponseBody
    public PenerimaanStoreFromStore update(@RequestBody PenerimaanStoreFromStore penerimaanStoreFromStore) throws Exception {
    	
    	PenerimaanStoreFromStore pengirimanResponse = penerimaanFromStoreService.update(penerimaanStoreFromStore);
    	return pengirimanResponse;
		
    }
    
    @GetMapping("/delete")
    public String deletePenerimaanFromStore(@RequestParam("id") Long id)
    {
    	penerimaanFromStoreService.deletePenerimaanFromStoreById(id);
    	return "redirect:/all";
    }

}
