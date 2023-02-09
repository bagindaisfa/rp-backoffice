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

import com.gbsystem.rpbackoffice.entities.ProformaInvoice;
import com.gbsystem.rpbackoffice.services.ProformaInvoiceService;

@RestController
@RequestMapping("/proformaInvoice")
@CrossOrigin
public class ProformaInvoiceController {

	@Autowired
	private ProformaInvoiceService proformaInvoiceService;
	
	@GetMapping("/pi")
	public ResponseEntity<List<ProformaInvoice>> getAll(){
		return new ResponseEntity<>(proformaInvoiceService.getAllProformaInvoice(), HttpStatus.OK);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<ProformaInvoice>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(proformaInvoiceService.search(keyword), HttpStatus.OK);
    }
	
	@GetMapping("/getPi")
    public ResponseEntity<ProformaInvoice> getPi(@Param("pi_no") String pi_no) {
    	return new ResponseEntity<>(proformaInvoiceService.getPi(pi_no), HttpStatus.OK);
    }
	
	@GetMapping("/getPiUpdate")
    public ResponseEntity<ProformaInvoice> getPiUpdate(@Param("pi_no") String pi_no) {
    	return new ResponseEntity<>(proformaInvoiceService.getPiUpdate(pi_no), HttpStatus.OK);
    }
	
	@PostMapping(value = "/add")
	@ResponseBody
	public ProformaInvoice savePenjualan_office(@RequestBody ProformaInvoice proformaInvoice) 
	{
		ProformaInvoice proformaInvoiceResponse = proformaInvoiceService.saveProformaInvoice(proformaInvoice);
		return proformaInvoiceResponse;
	}
	
	@PostMapping(value = "/update")
	@ResponseBody
	public ProformaInvoice update(@RequestBody ProformaInvoice proformaInvoice) throws Exception {
    	
		ProformaInvoice proformaInvoiceResponse = proformaInvoiceService.update(proformaInvoice);
    	return proformaInvoiceResponse;
		
    }
	
	@GetMapping("/delete")
    public String deletePenjualan(@RequestParam("id") Long id)
    {
    	
		String proformaInvoiceResponse = proformaInvoiceService.deleteProformaInvoiceById(id);
    	return proformaInvoiceResponse;
    }
	    
}
