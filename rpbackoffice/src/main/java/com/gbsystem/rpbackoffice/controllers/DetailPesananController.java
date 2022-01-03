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

import com.gbsystem.rpbackoffice.entities.DetailPesanan;
import com.gbsystem.rpbackoffice.services.DetailPesananService;

@RestController
@RequestMapping("/detailPesanan")
@CrossOrigin
public class DetailPesananController {
	
	@Autowired
	private DetailPesananService detailPesananService;
	
	@GetMapping("/all")
	public ResponseEntity<List<DetailPesanan>> all(@Param("id_transaksi") String id_transaksi){
		return new ResponseEntity<>(detailPesananService.all(id_transaksi), HttpStatus.OK);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<DetailPesanan>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(detailPesananService.search(keyword), HttpStatus.OK);
    }
	
	@PostMapping("/add")
	public String saveDetailPesanan(@RequestParam("id_transaksi") String id_transaksi, @RequestParam("id_store") int id_store,
			@RequestParam("lokasi_store") String lokasi_store, @RequestParam("nama_barang") String nama_barang,
			@RequestParam("harga") double harga,
			@RequestParam("kuantitas") double kuantitas, @RequestParam("total") double total) 
	{
		detailPesananService.saveDetailPesanan(id_transaksi, id_store, lokasi_store, nama_barang,
				harga, kuantitas, total);
		return "redirect:/penjualan";
	}
	
	@PostMapping(value = "/update")
    public @ResponseBody String update(@RequestParam("id") Long id, @RequestParam("tanggal_transaksi") Date tanggal_transaksi, 
    		@RequestParam("id_transaksi") String id_transaksi, @RequestParam("id_store") int id_store,
			@RequestParam("lokasi_store") String lokasi_store, @RequestParam("nama_barang") String nama_barang,
			@RequestParam("harga") double harga,
			@RequestParam("kuantitas") double kuantitas, @RequestParam("total") double total) throws Exception {
    	
    	if (id_transaksi != "") {
    		detailPesananService.update(id, tanggal_transaksi, id_transaksi, id_store, lokasi_store, nama_barang,
    				harga, kuantitas, total);
    	}
    	return "Update Data Successs!";
		
    }
	
	@GetMapping("/delete")
    public String deleteDetailPesanan(@RequestParam("id") Long id)
    {
    	
		detailPesananService.deleteDetailPesananById(id);
    	return "redirect:/penjualanStore";
    }

}
