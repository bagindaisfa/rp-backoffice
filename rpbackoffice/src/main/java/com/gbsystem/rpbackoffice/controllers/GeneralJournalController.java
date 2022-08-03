package com.gbsystem.rpbackoffice.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.gbsystem.rpbackoffice.entities.GeneralJournal;
import com.gbsystem.rpbackoffice.services.GeneralJournalService;

@RestController
@RequestMapping("/akutansi/journal")
@CrossOrigin
public class GeneralJournalController {
	@Autowired
	private GeneralJournalService generalJournalService;
	
    @PostMapping(value = "/add")
    public String saveGeneralJournal(@RequestBody List<GeneralJournal> request){
    	generalJournalService.saveGeneralJournal(request);
		return "Success!";
	}
    
    @PostMapping(value = "/update")
    public String updateGeneralJournal(@RequestBody List<GeneralJournal> request){
    	generalJournalService.update(request);
		return "Success!";
	}
    
    @GetMapping("/bukuBesar")
    public ResponseEntity<List<GeneralJournal>> search(
    		@Param("tanggal_awal") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggal_awal,
    		@Param("tanggal_akhir") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggal_akhir,
    		@Param("project") String project) {
    	return new ResponseEntity<>(generalJournalService.bukuBesar(tanggal_awal, tanggal_akhir, project), HttpStatus.OK);
    }
    
    @GetMapping("/journalUmum")
    public ResponseEntity<List<GeneralJournal>> search(
    		@Param("tanggal_awal") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggal_awal,
    		@Param("tanggal_akhir") @DateTimeFormat(pattern="yyyy-MM-dd") Date tanggal_akhir
    		) {
    	return new ResponseEntity<>(generalJournalService.getAll(tanggal_awal, tanggal_akhir),HttpStatus.OK);
    }
}
