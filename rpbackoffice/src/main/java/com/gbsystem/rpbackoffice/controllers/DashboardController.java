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
	public double getAllPenjualan(){
		return dashboardService.getAllPenjualan();
	}
	
	@GetMapping("/pendapatan")
	public double getAllPendapatan(){
		return dashboardService.getAllPendapatan();
	}
	
	@GetMapping("/biaya")
	public double getAllBiayaHPP(){
		return dashboardService.getAllBiayaHPP();
	}
	
	@GetMapping("/keuntungan")
	public double getKuntungan(){
		return dashboardService.getKuntungan();
	}
	// end region penjualan
	
	
	// region pembelian
	@GetMapping("/pembelian")
	public double getAllPembelian(){
		return dashboardService.getAllPembelian();
	}
	
	@GetMapping("/biayaPembelian")
	public double getAllBiayaPembelian(){
		return dashboardService.getAllBiayaPembelian();
	}
	// end region pembelian
	
	
	// region product
	@GetMapping("/jmlProduct")
	public double getALLtotalProduct(){
		return dashboardService.totalProduct();	
	}
	@GetMapping("/stockOffice")
	public double getALLtotalStockOffice(){
		return dashboardService.totalStockOffice();
	}
	@GetMapping("/stockStore")
	public double getALLtotalStockStore(){
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
