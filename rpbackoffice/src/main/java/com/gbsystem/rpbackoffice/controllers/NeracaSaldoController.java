package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.gbsystem.rpbackoffice.entities.NeracaSaldo;
import com.gbsystem.rpbackoffice.services.NeracaSaldoService;

@RestController
@RequestMapping("/akutansi")
@CrossOrigin
public class NeracaSaldoController {
	
	@Autowired
	private NeracaSaldoService neracaSaldoService;
	
	@GetMapping("/neracaSaldo")
    public ResponseEntity<List<NeracaSaldo>> search(
    		@Param("year") String year) {
    	return new ResponseEntity<>(neracaSaldoService.neracaSaldo(year), HttpStatus.OK);
    }
}
