package com.gbsystem.rpbackoffice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.services.DashboardService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;
	
	//region penjualan
	@GetMapping("/penjualan")
	public Double getAllPenjualan(){
		return dashboardService.getAllPenjualan();
	}
	
	@GetMapping("/pendapatan")
	public Double getAllPendapatan(){
		return dashboardService.getAllPendapatan();
	}
	
	@GetMapping("/biaya")
	public Double getAllBiayaHPP(){
		return dashboardService.getAllBiayaHPP();
	}
	
	@GetMapping("/keuntungan")
	public Double getKuntungan(){
		return dashboardService.getKuntungan();
	}
	// end region penjualan
	
	
	// region pembelian
	@GetMapping("/pembelian")
	public Double getAllPembelian(){
		return dashboardService.getAllPembelian();
	}
	
	@GetMapping("/biayaPembelian")
	public Double getAllBiayaPembelian(){
		return dashboardService.getAllBiayaPembelian();
	}
	// end region pembelian
	
	
	// region product
	@GetMapping("/jmlProduct")
	public Double getALLtotalProduct(){
		return dashboardService.totalProduct();	
	}
	@GetMapping("/stockOffice")
	public Double getALLtotalStockOffice(){
		return dashboardService.totalStockOffice();
	}
	@GetMapping("/stockStore")
	public Double getALLtotalStockStore(){
		return dashboardService.totalStockStore();
	}
	// end region product
	
	
	// region pemasok and pelanggan
	@GetMapping("/jmlSupplier")
	public Long totalPemasok(){
		return dashboardService.totalPemasok();			
	}
	@GetMapping("/jmlCustomer")
	public Long totalPelanggan(){
		return dashboardService.totalPelanggan();	
	}
	// end region pemasok and pelanggan
}
