package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.LabaRugi;
import com.gbsystem.rpbackoffice.services.LabaRugiService;

@RestController
@RequestMapping("/akutansi")
@CrossOrigin
public class LabaRugiController {
	@Autowired
	private LabaRugiService labaRugiService;
	
	@GetMapping("/labaRugi")
    public ResponseEntity<List<LabaRugi>> search(@Param("year") String year) {
    	return new ResponseEntity<>(labaRugiService.labaRugi(year), HttpStatus.OK);
    }
	
	@GetMapping("/neracaKeuangan")
    public ResponseEntity<List<LabaRugi>> find(@Param("year") String year) {
    	return new ResponseEntity<>(labaRugiService.neracaKeuangan(year), HttpStatus.OK);
    }
}
