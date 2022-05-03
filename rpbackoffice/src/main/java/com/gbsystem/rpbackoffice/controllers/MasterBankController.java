package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.MasterBank;
import com.gbsystem.rpbackoffice.services.MasterBankService;

@RestController
@RequestMapping("/master/bank")
@CrossOrigin
public class MasterBankController {
	@Autowired
	private MasterBankService masterBankService;

    @GetMapping("/all")
	public ResponseEntity<List<MasterBank>> getAll() {
        return new ResponseEntity<>(masterBankService.getAllMasterBank(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MasterBank>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(masterBankService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public String saveMaster_bank(@RequestParam("bank_name") String bank_name, @RequestParam("owner_name") String owner_name, @RequestParam("acc_number") String acc_number) 
	{
    	masterBankService.saveMasterBank(bank_name, owner_name, acc_number);
		return "redirect:/bank";
	}
    
    @PostMapping("/update")
    public String update(@RequestParam("id") Long id, @RequestParam("bank_name") String bank_name, @RequestParam("owner_name") String owner_name, @RequestParam("acc_number") String acc_number) throws Exception {
    	
    	if (bank_name != "") {
    		masterBankService.update(id, bank_name, owner_name, acc_number);
    	}
    	return "Update Data Successs!";
		
    }
    @GetMapping("/delete")
    public String deleteBank(@RequestParam("id") Long id)
    {
    	
    	masterBankService.deleteMasterBankById(id);
    	return "redirect:/bank";
    }
}

