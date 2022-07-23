package com.gbsystem.rpbackoffice.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.services.StockPerStoreService;

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
	
	@GetMapping("/allStock")
	public List<StockStore> allStock(@Param("id_store") int id_store){
		return stockService.stockAllItemPerStore(id_store);
	}

}
