package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.Pembelian;
import com.gbsystem.rpbackoffice.services.PembelianService;

@Controller
public class PembelianController {
	
	@Autowired
	private PembelianService pembelianService;
	
	@GetMapping("/pembelian")
	public ResponseEntity<List<Pembelian>> getAll() {
        return new ResponseEntity<>(pembelianService.getAllPembelian(), HttpStatus.OK);
    }
	
	@PostMapping("/addPembelian")
    public String saveProduct(@RequestParam("file") MultipartFile file,
    		@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,
    		@RequestParam("tipe") String tipe,
    		@RequestParam("nama_barang") String nama_barang,
    		@RequestParam("kuantitas") int kuantitas,
    		@RequestParam("ukuran") String ukuran,
    		@RequestParam("hpp") double hpp,
    		@RequestParam("harga_jual") double harga_jual)
    {
		pembelianService.savePembelian(file, artikel, kategori, tipe, nama_barang, kuantitas, ukuran, hpp, harga_jual);
    	return "redirect:/pembelian";
    }
    
    @GetMapping("/deletePembelian/{id}")
    public String deletePembelian(@PathVariable("id") Long id)
    {
    	
    	pembelianService.deletePembelianById(id);
    	return "redirect:/pembelian";
    }
    
    
    @PostMapping("/changeArtikel")
    public String changeArtikel(@RequestParam("id") Long id ,@RequestParam("newArtikel") String artikel)
    {
    	pembelianService.changePembelianArtikel(id, artikel);
    	return "redirect:/pembelian"; 
    }
    @PostMapping("/changeKategori")
    public String changePembelianKategori(@RequestParam("id") Long id , @RequestParam("newKategori") String kategori)
    {
    	pembelianService.changePembelianKategori(id, kategori);
    	return "redirect:/pembelian"; 
    }
    @PostMapping("/changeTipe")
    public String changePembelianTipe(@RequestParam("id") Long id ,@RequestParam("newTipe") String tipe)
    {
    	pembelianService.changePembelianTipe(id, tipe);
    	return "redirect:/pembelian"; 
    }
    @PostMapping("/changeNamaBarang")
    public String changePembelianNamaBarang(@RequestParam("id") Long id ,@RequestParam("newNamaBarang") String nama_barang)
    {
    	pembelianService.changePembelianNamaBarang(id, nama_barang);
    	return "redirect:/pembelian"; 
	}
    @PostMapping("/changeKuantitas")
    public String changePembelianKuantitas(@RequestParam("id") Long id ,@RequestParam("newKuantitas") int kuantitas)
    {
    	pembelianService.changePembelianKuantitas(id, kuantitas);
    	return "redirect:/pembelian"; 
	}
    @PostMapping("/changeUkuran")
    public String changePembelianUkuran(@RequestParam("id") Long id ,@RequestParam("newUkuran") String ukuran)
    {
    	pembelianService.changePembelianUkuran(id, ukuran);
    	return "redirect:/pembelian"; 
	}
    @PostMapping("/changeHpp")
    public String changePembelianHpp(@RequestParam("id") Long id ,@RequestParam("newHpp") double hpp)
    {
    	pembelianService.changePembelianHpp(id, hpp);
    	return "redirect:/pembelian"; 
	}
    @PostMapping("/changeHargaJual")
    public String changePembelianHargaJual(@RequestParam("id") Long id ,@RequestParam("newHargaJual") double harga_jual)
    {
    	pembelianService.changePembelianHargaJual(id, harga_jual);
    	return "redirect:/pembelian"; 
	}
}
