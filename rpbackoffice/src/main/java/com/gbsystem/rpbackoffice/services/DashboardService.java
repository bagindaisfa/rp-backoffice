package com.gbsystem.rpbackoffice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.repository.PenjualanOfficeRepository;
import com.gbsystem.rpbackoffice.repository.PenjualanRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;
import com.gbsystem.rpbackoffice.repository.PembelianRepository;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
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
	private MasterProductRepository eProductRepo;
	@Autowired
	private PelangganRepository ePelangganRepo;
	@Autowired
	private PemasokRepository ePemasokRepo;
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	@Autowired
	private StockStoreRepository eStockStoreRepo;
	
	// Region Penjualan
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
	// end region penjualan
	
	
	// region pembelian
	public double getAllPembelian(){
		
		double office = ePembelianRepo.totalPembelian(1);
		
		
		return office;
	}
	
	public double getAllBiayaPembelian(){
		
		double office = ePembelianRepo.totalHpp(1);
		
		
		return office;
	}
	// end region pembelian
	
	
	// region product
	public double totalProduct(){
		
		double product = eProductRepo.totalProduct(1);
		
		
		return product;
	}
	
	public double totalStockOffice(){
		
		double office = eStockOfficeRepo.totalStockOffice(1);
		
		
		return office;
	}
	
	public double totalStockStore(){
		
		double store = eStockStoreRepo.totalStockStore(1);
		
		
		return store;
	}
	// end region product
	
	// region pemasok and pelanggan
	public Long totalPemasok(){
			
			Long pemasok = ePemasokRepo.totalPemasok(1);
			
			
			return pemasok;
	}
	public Long totalPelanggan(){
		
		Long pelanggan = ePelangganRepo.totalPelanggan(1);
		
		
		return pelanggan;
	}
	// end region pemasok and pelanggan
}
