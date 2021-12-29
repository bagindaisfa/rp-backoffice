package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.gbsystem.rpbackoffice.entities.MasterStore;
import com.gbsystem.rpbackoffice.services.MasterStoreService;

@RestController
@RequestMapping("/master/store")
@CrossOrigin
public class MasterStoreController {
	@Autowired
	private MasterStoreService masterStoreService;

    @GetMapping("/all")
	public ResponseEntity<List<MasterStore>> getAll() {
        return new ResponseEntity<>(masterStoreService.getAllMasterStore(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MasterStore>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(masterStoreService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public String saveMaster_tipe(
    		@RequestParam("store_name") String store_name,@RequestParam("no_tlpn") String no_tlpn,
    		@RequestParam("alamat") String alamat, @RequestParam("kepala_store") String kepala_store ) 
	{
    	masterStoreService.saveMasterStore(store_name, no_tlpn, alamat, kepala_store);
		return "redirect:/store";
	}
    
    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,@RequestParam("store_name") String store_name,@RequestParam("no_tlpn") String no_tlpn,
    		@RequestParam("alamat") String alamat, @RequestParam("kepala_store") String kepala_store) throws Exception {
    	
    	if (store_name != "") {
    		masterStoreService.update(id, store_name, no_tlpn, alamat, kepala_store);
    	}
    	return "Update Data Successs!";
		
    }
    @GetMapping("/delete")
    public String deleteTipe(@RequestParam("id") Long id)
    {
    	
    	masterStoreService.deleteMasterStoreById(id);
    	return "redirect:/store";
    }
}
