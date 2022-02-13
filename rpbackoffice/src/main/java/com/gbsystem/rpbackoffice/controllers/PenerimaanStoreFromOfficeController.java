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

import com.gbsystem.rpbackoffice.entities.PenerimaanStoreFromOffice;
import com.gbsystem.rpbackoffice.services.PenerimaanStoreFromOfficeService;

@RestController
@RequestMapping("/penerimaanFromOffice")
@CrossOrigin
public class PenerimaanStoreFromOfficeController {
	
	@Autowired
	private PenerimaanStoreFromOfficeService penerimaanFromOfficeService;

    @GetMapping("/all")
	public ResponseEntity<List<PenerimaanStoreFromOffice>> getAll() {
        return new ResponseEntity<>(penerimaanFromOfficeService.getAllPenerimaanFromOffice(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PenerimaanStoreFromOffice>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penerimaanFromOfficeService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    @ResponseBody
    public PenerimaanStoreFromOffice saveProduct(@RequestBody PenerimaanStoreFromOffice penerimaanStoreFromOffice) throws Exception {
    	PenerimaanStoreFromOffice pengirimanResponse = penerimaanFromOfficeService.savePenerimaanStore(penerimaanStoreFromOffice);
    	return pengirimanResponse;
    	
    }
    
    @PostMapping("/update")
    @ResponseBody
    public PenerimaanStoreFromOffice update(@RequestBody PenerimaanStoreFromOffice penerimaanStoreFromOffice) throws Exception {
    	
    	PenerimaanStoreFromOffice pengirimanResponse = penerimaanFromOfficeService.update(penerimaanStoreFromOffice);
    	return pengirimanResponse;
		
    }
    
    @GetMapping("/delete")
    public String deletePenerimaanFromOffice(@RequestParam("id") Long id)
    {
    	penerimaanFromOfficeService.deletePenerimaanFromOfficeById(id);
    	return "redirect:/all";
    }

}
