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

import com.gbsystem.rpbackoffice.entities.Penjualan;
import com.gbsystem.rpbackoffice.services.PenjualanService;

@RestController
@RequestMapping("/store")
@CrossOrigin
public class PenjualanController {

	@Autowired
	private PenjualanService penjualanService;
	
	@GetMapping("/penjualan")
	public ResponseEntity<List<Penjualan>> getAll(){
		return new ResponseEntity<>(penjualanService.getAllPenjualan(), HttpStatus.OK);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<Penjualan>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penjualanService.search(keyword), HttpStatus.OK);
    }
	
	@PostMapping("/add")
	@ResponseBody
	public Penjualan savePenjualan(@RequestBody Penjualan penjualan) 
	{
		Penjualan penjualanResponse = penjualanService.savePenjualan(penjualan);
		return penjualanResponse;
	}
	
	@PostMapping(value = "/update")
	@ResponseBody
    public Penjualan update(@RequestBody Penjualan penjualan) throws Exception {
    	
		Penjualan penjualanResponse = penjualanService.update(penjualan);
    	return penjualanResponse;
		
    }
	
	@PostMapping("/delete")
    public Penjualan deletePenjualan(@RequestParam("id") Long id)
    {
		Penjualan penjualanResponse = penjualanService.deletePenjualanById(id);
    	return penjualanResponse;
    }
	    
}
