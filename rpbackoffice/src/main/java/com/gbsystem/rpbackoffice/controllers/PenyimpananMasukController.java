package com.gbsystem.rpbackoffice.controllers;

import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.services.PenyimpananMasukService;

@RestController
@RequestMapping("/penyimpananMasuk")
@CrossOrigin
public class PenyimpananMasukController {
	
	@Autowired
	private PenyimpananMasukService penyimpananMasukService;

    @GetMapping("/all")
	public ResponseEntity<List<PenyimpananMasuk>> getAll() {
        return new ResponseEntity<>(penyimpananMasukService.getAllPenyimpananMasuk(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PenyimpananMasuk>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penyimpananMasukService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add")
    public @ResponseBody String saveProduct(@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("nama_kategori") String nama_kategori, @RequestParam("type") int type,@RequestParam("type_name") String type_name,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("kuantitas") double kuantitas,
    		@RequestParam("ukuran") String ukuran, @RequestParam("hpp") double hpp,
    		@RequestParam("harga_jual") double harga_jual, @RequestParam("keterangan") String keterangan) throws Exception {
    	
    	if (artikel != "") {
    		penyimpananMasukService.savePenyimpananMasuk(artikel, kategori,nama_kategori, type,type_name, nama_barang, kuantitas, ukuran, hpp, harga_jual, keterangan);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update")
    public @ResponseBody String update(@RequestParam("id") Long id, @RequestParam("tanggal_masuk") Date tanggal_masuk, @RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("nama_kategori") String nama_kategori, @RequestParam("type") int type,@RequestParam("type_name") String type_name,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("kuantitas") double kuantitas,
    		@RequestParam("ukuran") String ukuran, @RequestParam("hpp") double hpp,
    		@RequestParam("harga_jual") double harga_jual, @RequestParam("keterangan") String keterangan) throws Exception {
    	
    	if (artikel != "") {
    		penyimpananMasukService.update(id, tanggal_masuk, artikel, kategori,nama_kategori, type,type_name, nama_barang, kuantitas, ukuran, hpp, harga_jual, keterangan);
    	}
    	return "Update Data Successs!";
		
    }
    
    @GetMapping("/delete")
    public String deletePenyimpananMasuk(@RequestParam("id") Long id)
    {
    	penyimpananMasukService.deletePenyimpananMasukById(id);
    	return "redirect:/all";
    }
}
