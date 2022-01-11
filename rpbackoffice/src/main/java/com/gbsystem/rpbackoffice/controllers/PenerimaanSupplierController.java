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

import com.gbsystem.rpbackoffice.entities.PenerimaanSupplier;
import com.gbsystem.rpbackoffice.services.PenerimaanSupplierService;

@RestController
@RequestMapping("/penerimaanSupplier")
@CrossOrigin
public class PenerimaanSupplierController {
	
	@Autowired
	private PenerimaanSupplierService penerimaanSupplierService;

    @GetMapping("/all")
	public ResponseEntity<List<PenerimaanSupplier>> getAll() {
        return new ResponseEntity<>(penerimaanSupplierService.getAllPenerimaanSupplier(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PenerimaanSupplier>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penerimaanSupplierService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public @ResponseBody String saveProduct(@RequestParam("pembelian_code") String pembelian_code) throws Exception {
    	
    	if (pembelian_code != "") {
    		penerimaanSupplierService.savePenerimaanSupplier(pembelian_code);
    	}
    	return "Insert Data Successs!";
		
    }
    
    @GetMapping("/delete")
    public String deletePenerimaanSupplier(@RequestParam("id") Long id, @RequestParam("id_office") int id_office, @RequestParam("artikel") String artikel, @RequestParam("ukuran") String ukuran)
    {
    	penerimaanSupplierService.deletePenerimaanSupplierById(id,id_office,artikel,ukuran);
    	return "redirect:/all";
    }

}
