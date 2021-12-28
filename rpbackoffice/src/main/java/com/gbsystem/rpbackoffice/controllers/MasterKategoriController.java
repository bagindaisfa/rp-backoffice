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
import com.gbsystem.rpbackoffice.entities.MasterKategori;
import com.gbsystem.rpbackoffice.services.MasterKategoriService;

@RestController
@RequestMapping("/master/kategori")
@CrossOrigin
public class MasterKategoriController {
	@Autowired
	private MasterKategoriService masterKategoriService;

    @GetMapping("/all")
	public ResponseEntity<List<MasterKategori>> getAll() {
        return new ResponseEntity<>(masterKategoriService.getAllMasterKategori(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MasterKategori>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(masterKategoriService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public String saveMaster_kategori(@RequestParam("kategori_name") String kategori_name) 
	{
    	masterKategoriService.saveMasterKategori(kategori_name);
		return "redirect:/kategori";
	}
    
    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,@RequestParam("kategori_name") String kategori_name) throws Exception {
    	
    	if (kategori_name != "") {
    		masterKategoriService.update(id, kategori_name);
    	}
    	return "Update Data Successs!";
		
    }
    @GetMapping("/delete")
    public String deleteKategori(@RequestParam("id") Long id)
    {
    	
    	masterKategoriService.deleteMasterKategoriById(id);
    	return "redirect:/kategori";
    }
    
    
}

