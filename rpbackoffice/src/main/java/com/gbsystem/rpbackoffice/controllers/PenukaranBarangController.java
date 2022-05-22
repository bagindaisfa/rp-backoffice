package com.gbsystem.rpbackoffice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.PenukaranBarang;
import com.gbsystem.rpbackoffice.services.PenukaranBarangService;

@RestController
@RequestMapping("/penukaranBarang")
@CrossOrigin
public class PenukaranBarangController {
	@Autowired
	private PenukaranBarangService penukaranBarangService;
	
	@GetMapping("/penukaran")
	public ResponseEntity<List<PenukaranBarang>> getAllPerStore(@Param("id_store") int id_store){
		return new ResponseEntity<>(penukaranBarangService.getAllPerStore(id_store), HttpStatus.OK);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<PenukaranBarang>> search(@Param("id_store") int id_store,@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penukaranBarangService.searchPerStore(id_store,keyword), HttpStatus.OK);
    }
	
	@PostMapping("/add")
	@ResponseBody
	public Map savePenukaran(@RequestBody List<PenukaranBarang> penukaranBarang) 
	{
		Map<String, String> map = new HashMap<>();
		penukaranBarangService.savePenyimpananMasuk(penukaranBarang);
		map.put("result", "Barang Berhasil Ditukar!");
		return map;
	}
}
