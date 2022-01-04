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
}
