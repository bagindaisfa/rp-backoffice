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

import com.gbsystem.rpbackoffice.entities.PenjualanOffice;
import com.gbsystem.rpbackoffice.services.PenjualanOfficeService;

@RestController
@RequestMapping("/office")
@CrossOrigin
public class PenjualanOfficeController {

	@Autowired
	private PenjualanOfficeService penjualanOfficeService;
	
	@GetMapping("/penjualan")
	public ResponseEntity<List<PenjualanOffice>> getAll(){
		return new ResponseEntity<>(penjualanOfficeService.getAllPenjualan(), HttpStatus.OK);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<PenjualanOffice>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penjualanOfficeService.search(keyword), HttpStatus.OK);
    }
	
	@PostMapping(value = "/add")
	@ResponseBody
	public PenjualanOffice savePenjualan_office(@RequestBody PenjualanOffice penjualanOffice) 
	{
		PenjualanOffice penjualanOfficeResponse = penjualanOfficeService.savePenjualanOffice(penjualanOffice);
		return penjualanOfficeResponse;
	}
	
	@PostMapping(value = "/update")
	@ResponseBody
	public PenjualanOffice update(@RequestBody PenjualanOffice penjualanOffice) throws Exception {
    	
		PenjualanOffice penjualanOfficeResponse = penjualanOfficeService.update(penjualanOffice);
    	return penjualanOfficeResponse;
		
    }
	
	@GetMapping("/delete")
    public String deletePenjualan(@RequestParam("id") Long id)
    {
    	
		String penjualanOfficeResponse = penjualanOfficeService.deletePenjualanById(id);
    	return penjualanOfficeResponse;
    }
	    
}
