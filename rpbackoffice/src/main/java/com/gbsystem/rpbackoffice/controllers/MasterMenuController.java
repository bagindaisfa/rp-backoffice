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
import com.gbsystem.rpbackoffice.entities.MasterMenu;
import com.gbsystem.rpbackoffice.services.MasterMenuService;

@RestController
@RequestMapping("/master/menu")
@CrossOrigin
public class MasterMenuController {
	@Autowired
	private MasterMenuService masterMenuService;

    @GetMapping("/all")
	public ResponseEntity<List<MasterMenu>> getAll() {
        return new ResponseEntity<>(masterMenuService.getAllMasterMenu(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MasterMenu>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(masterMenuService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public String saveMaster_aksesoris(@RequestParam("nama_menu") String nama_menu) 
	{
    	masterMenuService.saveMasterMenu(nama_menu);
		return "Sukses!";
	}
    
    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,@RequestParam("kode_menu") String kode_menu, @RequestParam("nama_menu") String nama_menu ) throws Exception {
    	
    	if (nama_menu != "") {
    		masterMenuService.update(id,kode_menu, nama_menu);
    	}
    	return "Update Data Successs!";
		
    }
    @GetMapping("/delete")
    public String deleteTipe(@RequestParam("id") Long id)
    {
    	
    	masterMenuService.deleteMasterMenuById(id);
    	return "redirect:/menu";
    }
}
