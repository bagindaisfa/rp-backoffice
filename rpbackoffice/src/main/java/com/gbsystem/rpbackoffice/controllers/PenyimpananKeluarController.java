package com.gbsystem.rpbackoffice.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;
import com.gbsystem.rpbackoffice.services.PenyimpananKeluarService;

@RestController
@RequestMapping("/penyimpananKeluar")
@CrossOrigin
public class PenyimpananKeluarController {
	
	@Autowired
	private PenyimpananKeluarService penyimpananKeluarService;

    @GetMapping("/all")
	public ResponseEntity<List<PenyimpananKeluar>> getAll() {
        return new ResponseEntity<>(penyimpananKeluarService.getAllPenyimpananKeluar(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PenyimpananKeluar>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penyimpananKeluarService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String save(
    		@RequestParam("id_store") int id_store,@RequestParam("lokasi_store") String lokasi_store,
    		@RequestParam("artikel") String artikel,@RequestParam("kategori") String kategori,
    		@RequestParam("nama_kategori") String nama_kategori, @RequestParam("type") int type,
    		@RequestParam("type_name") String type_name,@RequestParam("nama_barang") String nama_barang,
    		@RequestParam("kuantitas") double kuantitas,@RequestParam("ukuran") String ukuran,
    		@RequestParam("foto_barang") MultipartFile foto_barang,@RequestParam("hpp") double hpp,
    		@RequestParam("harga_total") double harga_total,@RequestParam("keterangan") String keterangan
    		) throws Exception {
    	
    	if (artikel != "") {
    		penyimpananKeluarService.savePenyimpananKeluar(id_store, lokasi_store, artikel, kategori,nama_kategori, type,type_name, nama_barang, 
    				kuantitas, ukuran, foto_barang, hpp, harga_total, keterangan);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String update(@RequestParam("id") Long id, @RequestParam("tanggal_keluar") Date tanggal_keluar, @RequestParam("id_store") int id_store,
    		@RequestParam("lokasi_store") String lokasi_store, @RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("nama_kategori") String nama_kategori, @RequestParam("type") int type,@RequestParam("type_name") String type_name,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("kuantitas") double kuantitas,
    		@RequestParam("ukuran") String ukuran, @RequestParam("foto_barang") MultipartFile foto_barang,
    		@RequestParam("hpp") double hpp, @RequestParam("harga_jual") double harga_jual,
    		@RequestParam("keterangan") String keterangan) throws Exception {
    	
    	if (artikel != "") {
    		penyimpananKeluarService.update(id, tanggal_keluar, id_store, lokasi_store, artikel, kategori,nama_kategori, type,type_name, nama_barang, 
    				kuantitas, ukuran, foto_barang, hpp, harga_jual, keterangan);
    	}
    	return "Update Data Successs!";
		
    }
    
    @GetMapping("/delete")
    public String deletePenyimpananKeluar(@RequestParam("id") Long id)
    {
    	penyimpananKeluarService.deletePenyimpananKeluarById(id);
    	return "redirect:/all";
    }

}
