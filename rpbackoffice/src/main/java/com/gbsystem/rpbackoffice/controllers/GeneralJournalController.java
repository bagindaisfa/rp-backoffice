package com.gbsystem.rpbackoffice.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gbsystem.rpbackoffice.entities.GeneralJournal;
import com.gbsystem.rpbackoffice.entities.JournalDetail;
import com.gbsystem.rpbackoffice.services.GeneralJournalService;

@RestController
@RequestMapping("/akutansi/journal")
@CrossOrigin
public class GeneralJournalController {
	@Autowired
	private GeneralJournalService generalJournalService;
	
	@GetMapping("/all/{nomorJournal}")
	@ResponseBody
    public GeneralJournal getGeneralJournalDetails(@PathVariable String nomorJournal) {
		GeneralJournal generalJournalResponse = generalJournalService.getAll(nomorJournal);
		
		return generalJournalResponse;
    }
	
    @PostMapping(value = "/add")
    public Object saveGeneralJournal(@RequestBody GeneralJournal request){
    	LocalDateTime tanggal_transaksi = request.getTanggal_transaksi();
    	String project = request.getProject();
    	String project_name = request.getProject_name();
    	String rincian_transaksi = request.getRincian_transaksi();
    	List<JournalDetail> journalDetail = request.getJournalDetail();
    	generalJournalService.saveGeneralJournal(tanggal_transaksi,project,project_name,rincian_transaksi,journalDetail);
		return "redirect:/akutanasi/journal/all";
	}
    
}
