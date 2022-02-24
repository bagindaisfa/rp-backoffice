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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.StockOpname;
import com.gbsystem.rpbackoffice.services.StockOpnameService;

@RestController
@RequestMapping("/stockOpname")
@CrossOrigin
public class StockOpnameController {
	
	@Autowired
	private StockOpnameService stockOpnameService;
	
    @GetMapping("/all")
	public ResponseEntity<List<StockOpname>> getAll() {
        return new ResponseEntity<>(stockOpnameService.getAllStockOpname(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<StockOpname>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(stockOpnameService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping(value = "/add")
    public @ResponseBody String saveProduct(@RequestParam("sku_code") String sku_code,@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("nama_kategori") String nama_kategori, @RequestParam("type") String type,@RequestParam("type_name") String type_name,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("stock_opname") double stock_opname) throws Exception {
    	
    	if (artikel != "") {
    		stockOpnameService.saveStockOpname(sku_code,artikel, kategori,nama_kategori, type,type_name, nama_barang, stock_opname);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @PostMapping(value = "/update")
    public @ResponseBody String update(@RequestParam("id") Long id,@RequestParam("sku_code") String sku_code,@RequestParam("artikel") String artikel,
    		@RequestParam("kategori") String kategori,@RequestParam("nama_kategori") String nama_kategori, @RequestParam("type") String type,@RequestParam("type_name") String type_name,
    		@RequestParam("nama_barang") String nama_barang,@RequestParam("stock_opname") double stock_opname) throws Exception {
    	
    	if (artikel != "") {
    		stockOpnameService.update(id, sku_code,artikel, kategori,nama_kategori, type,type_name, nama_barang, stock_opname);
    	}
    	return "Update Data Successs!";
		
    }
    
    @GetMapping("/delete")
    public String deleteStockOpname(@RequestParam("id") Long id)
    {
    	stockOpnameService.deleteStockOpnameById(id);
    	return "redirect:/all";
    }
}
