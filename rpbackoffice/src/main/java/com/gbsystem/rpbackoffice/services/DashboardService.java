package com.gbsystem.rpbackoffice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.Pelanggan;
import com.gbsystem.rpbackoffice.entities.Pemasok;
import com.gbsystem.rpbackoffice.entities.PenjualanOffice;
import com.gbsystem.rpbackoffice.entities.Penjualan;
import com.gbsystem.rpbackoffice.entities.Pembelian;
import com.gbsystem.rpbackoffice.repository.PenjualanOfficeRepository;
import com.gbsystem.rpbackoffice.repository.PenjualanRepository;
import com.gbsystem.rpbackoffice.repository.PembelianRepository;
import com.gbsystem.rpbackoffice.repository.PelangganRepository;
import com.gbsystem.rpbackoffice.repository.PemasokRepository;

@Service
public class DashboardService {
	@Autowired
	private PenjualanOfficeRepository ePenjualanOfficeRepo;
	@Autowired
	private PenjualanRepository ePenjualanStoreRepo;
	@Autowired
	private PembelianRepository ePembelianRepo;
	@Autowired
	private PelangganRepository ePelangganRepo;
	@Autowired
	private PemasokRepository ePemasokRepo;
	
	public double getAllPenjualan(){
		
		double office = ePenjualanOfficeRepo.counting(1);
		double store = ePenjualanStoreRepo.counting(1);
		
		double total = office + store;
		
		return total;
	}
	
	public double getAllPendapatan(){
		
		double office = ePenjualanOfficeRepo.total(1);
		double store = ePenjualanStoreRepo.total(1);
		
		double total = office + store;
		
		return total;
	}
	
	public double getAllBiayaHPP(){
		
		double office = ePenjualanOfficeRepo.totalHpp(1);
		double store = ePenjualanStoreRepo.totalHpp(1);
		
		double total = office + store;
		
		return total;
	}
	
	public double getKuntungan(){
		double keuntunganOffice = ePenjualanOfficeRepo.total(1);
		double keuntunganStore = ePenjualanStoreRepo.total(1);
	
		double hppOffice = ePenjualanOfficeRepo.totalHpp(1);
		double hppStore = ePenjualanStoreRepo.totalHpp(1);
		
		double total = (keuntunganOffice + keuntunganStore) - (hppOffice + hppStore);
		
		return total;
	}
}
