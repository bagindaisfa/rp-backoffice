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
import com.gbsystem.rpbackoffice.entities.MasterAksesoris;
import com.gbsystem.rpbackoffice.services.MasterAksesorisService;

@RestController
@RequestMapping("/master/aksesoris")
@CrossOrigin
public class MasterAksesorisController {
	@Autowired
	private MasterAksesorisService masterAksesorisService;

    @GetMapping("/all")
	public ResponseEntity<List<MasterAksesoris>> getAll() {
        return new ResponseEntity<>(masterAksesorisService.getAllMasterAksesoris(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MasterAksesoris>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(masterAksesorisService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public String saveMaster_aksesoris(
    		@RequestParam("artikel_aksesoris") String artikel_aksesoris,
    		@RequestParam("nama_aksesoris") String nama_aksesoris,
    		@RequestParam("type") String type,
    		@RequestParam("type_name") String type_name,
    		@RequestParam("kategori") String kategori,
    		@RequestParam("nama_kategori") String nama_kategori,
    		@RequestParam("kuantitas") double kuantitas,
    		@RequestParam("hpp") double hpp,
    		@RequestParam("harga_jual") double harga_jual
    		) 
	{
    	masterAksesorisService.saveMasterAksesoris(artikel_aksesoris,nama_aksesoris,type,type_name,kategori,nama_kategori,kuantitas,hpp,harga_jual);
		return "redirect:/tipe";
	}
    
    @PostMapping("/update")
    public String update(
    		@RequestParam("id") Long id,
    		@RequestParam("artikel_aksesoris") String artikel_aksesoris,
    		@RequestParam("nama_aksesoris") String nama_aksesoris,
    		@RequestParam("type") String type,
    		@RequestParam("type_name") String type_name,
    		@RequestParam("kategori") String kategori,
    		@RequestParam("nama_kategori") String nama_kategori,
    		@RequestParam("kuantitas") double kuantitas,
    		@RequestParam("hpp") double hpp,
    		@RequestParam("harga_jual") double harga_jual
    		) throws Exception {
    	
    	if (type_name != "") {
    		masterAksesorisService.update(id, artikel_aksesoris,nama_aksesoris,type,type_name,kategori,nama_kategori,kuantitas,hpp,harga_jual);
    	}
    	return "Update Data Successs!";
		
    }
    @GetMapping("/delete")
    public String deleteTipe(@RequestParam("id") Long id)
    {
    	
    	masterAksesorisService.deleteMasterAksesorisById(id);
    	return "redirect:/kategori";
    }
}

