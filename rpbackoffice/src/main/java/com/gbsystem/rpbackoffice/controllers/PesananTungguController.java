package com.gbsystem.rpbackoffice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.PesananTunggu;
import com.gbsystem.rpbackoffice.services.PesananTungguService;

@RestController
@RequestMapping("/pesananTunggu")
@CrossOrigin
public class PesananTungguController {
	@Autowired
	private PesananTungguService pesananTungguService;
	
	@GetMapping("/all")
	public ResponseEntity<List<PesananTunggu>> getAll(){
		return new ResponseEntity<>(pesananTungguService.getAllPesanan(), HttpStatus.OK);
	}
	
	@GetMapping("/getItem")
	public ResponseEntity<PesananTunggu> getPesanan(@Param("no_pesanan") String no_pesanan) {
		return new ResponseEntity<>(pesananTungguService.getPesanan(no_pesanan), HttpStatus.OK);
	}

	@PostMapping("/add")
	public List<PesananTunggu> savePesananTunggu(@RequestBody PesananTunggu pesananTunggu) 
	{
		List<PesananTunggu> pesananTungguResponse = pesananTungguService.savePesananTunggu(pesananTunggu);
		return pesananTungguResponse;
	}
	
	@GetMapping("/deletePesanan")
	public Map delete(@Param("id") Long id) {
		Map<String, String> map = new HashMap<>();
		pesananTungguService.delete(id);
		map.put("result", "Deleted");
		return map;
	}
}
