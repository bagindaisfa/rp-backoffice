package com.gbsystem.rpbackoffice.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockPerStoreList;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.services.StockPerStoreService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/stockPerStore")
@CrossOrigin
public class StockPerStoreController {
	
	@Autowired
	private StockPerStoreService stockService;
	
	@GetMapping("/storeMasuk")
	public List<PenyimpananStoreMasuk> allStockMasuk(
			@Param("id_store") int id_store,
			@Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, 
			@Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to
			){
		return stockService.storeMasuk(id_store, date_from, date_to);
	}
	
	@GetMapping("/storeKeluar")
	public List<PenyimpananStoreKeluar> allStockKeluar(
			@Param("id_store") int id_store,
			@Param("date_from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_from, 
			@Param("date_to") @DateTimeFormat(pattern="yyyy-MM-dd") Date date_to
			){
		return stockService.storeKeluar(id_store, date_from, date_to);
	}
	
	@GetMapping("/allStockPerStore")
	public List<StockStore> allStock(@Param("id_store") int id_store){
		return stockService.stockAllItemPerStore(id_store);
	}

	@GetMapping("/allStock")
	public List<StockPerStoreList> allStock(){
		return stockService.allStock();
	}
	
	@GetMapping("/searchStock")
	public ResponseEntity<List<StockPerStoreList>> searchStock(@Param("keyword") String keyword){
		return new ResponseEntity<>(stockService.searchStock(keyword), HttpStatus.OK);
	}
	
	@GetMapping("/downloadStock")
	  public ResponseEntity<Resource> getFile() throws Exception, JRException {
	    String filename = "Stock Store_"+ new SimpleDateFormat("dd-MMMM-yyyy").format(new Date()) +".xlsx";
	    InputStreamResource file = new InputStreamResource(stockService.loadExcel());

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
	        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	        .body(file);
	  }
}
