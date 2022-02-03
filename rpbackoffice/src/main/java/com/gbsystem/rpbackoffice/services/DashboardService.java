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
	public Double getAllPenjualan(){
		
		Double office = ePenjualanOfficeRepo.counting(1) == null? 0.00 : ePenjualanOfficeRepo.counting(1);
		Double store = ePenjualanStoreRepo.counting(1) == null? 0.00 : ePenjualanStoreRepo.counting(1);
		
		Double total = office + store;
		
		return total;
	}
	
	public Double getAllPendapatan(){
		
		Double office = ePenjualanOfficeRepo.total(1) == null? 0.00 : ePenjualanOfficeRepo.total(1);
		Double store = ePenjualanStoreRepo.total(1) == null? 0.00 : ePenjualanStoreRepo.total(1);
		
		Double total = office + store;
		
		return total;
	}
	
	public Double getAllBiayaHPP(){
		
		Double office = ePenjualanOfficeRepo.totalHpp(1) == null? 0.00 : ePenjualanOfficeRepo.totalHpp(1);
		Double store = ePenjualanStoreRepo.totalHpp(1) == null? 0.00 : ePenjualanStoreRepo.totalHpp(1);
		
		Double total = office + store;
		
		return total;
	}
	
	public Double getKuntungan(){
		Double keuntunganOffice = ePenjualanOfficeRepo.total(1) == null? 0.00 : ePenjualanOfficeRepo.total(1);
		Double keuntunganStore = ePenjualanStoreRepo.total(1) == null? 0.00 : ePenjualanStoreRepo.total(1);
	
		Double hppOffice = ePenjualanOfficeRepo.totalHpp(1) == null? 0.00 : ePenjualanOfficeRepo.totalHpp(1);
		Double hppStore = ePenjualanStoreRepo.totalHpp(1) == null? 0.00 : ePenjualanStoreRepo.totalHpp(1);
		
		Double total = (keuntunganOffice + keuntunganStore) - (hppOffice + hppStore);
		
		return total;
	}
	// end region penjualan
	
	
	// region pembelian
	public Double getAllPembelian(){
		
		Double office = ePembelianRepo.totalPembelian(1) == null? 0.00 : ePembelianRepo.totalPembelian(1);
		
		return office;
	}
	
	public Double getAllBiayaPembelian(){
		
		Double office = ePembelianRepo.totalHpp(1) == null? 0.00 : ePembelianRepo.totalHpp(1);
		
		return office;
	}
	// end region pembelian
	
	
	// region product
	public Double totalProduct(){
		
		Double product = eProductRepo.totalProduct(1) == null? 0.00 : eProductRepo.totalProduct(1);
		return product;
	}
	
	public Double totalStockOffice(){
		
		Double office = eStockOfficeRepo.totalStockOffice(1) == null? 0.00 : eStockOfficeRepo.totalStockOffice(1);
		return office;
	}
	
	public Double totalStockStore(){
		
		Double store = eStockStoreRepo.totalStockStore(1) == null? 0.00 : eStockStoreRepo.totalStockStore(1);
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
