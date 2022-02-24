package com.gbsystem.rpbackoffice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.repository.PenjualanOfficeRepository;
import com.gbsystem.rpbackoffice.repository.PenjualanRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;
import com.gbsystem.rpbackoffice.repository.DetailPembelianRepository;
import com.gbsystem.rpbackoffice.repository.DetailPenjualanOfficeRepository;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PelangganRepository;
import com.gbsystem.rpbackoffice.repository.PemasokRepository;

@Service
public class DashboardService {
	@Autowired
	private PenjualanOfficeRepository ePenjualanOfficeRepo;
	@Autowired
	private DetailPenjualanOfficeRepository eDetailPenjualanOfficeRepo;
	@Autowired
	private PenjualanRepository ePenjualanStoreRepo;
	@Autowired
	private DetailPembelianRepository ePembelianRepo;
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
		
		Double office = eDetailPenjualanOfficeRepo.total(1) == null? 0.00 : eDetailPenjualanOfficeRepo.total(1);
		Double store = ePenjualanStoreRepo.total(1) == null? 0.00 : ePenjualanStoreRepo.total(1);
		
		Double total = office + store;
		
		return total;
	}
	
	public Double getAllBiayaHPP(){
		
		Double office = eDetailPenjualanOfficeRepo.totalHpp(1) == null? 0.00 : eDetailPenjualanOfficeRepo.totalHpp(1);
		Double store = ePenjualanStoreRepo.totalHpp(1) == null? 0.00 : ePenjualanStoreRepo.totalHpp(1);
		
		Double total = office + store;
		
		return total;
	}
	
	public Double getKuntungan(){
		Double keuntunganOffice = eDetailPenjualanOfficeRepo.total(1) == null? 0.00 : eDetailPenjualanOfficeRepo.total(1);
		Double keuntunganStore = ePenjualanStoreRepo.total(1) == null? 0.00 : ePenjualanStoreRepo.total(1);
	
		Double hppOffice = eDetailPenjualanOfficeRepo.totalHpp(1) == null? 0.00 : eDetailPenjualanOfficeRepo.totalHpp(1);
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
		Double res = eProductRepo.totalProduct(1);
		Double product = res == null? 0.00 : res;
		return product;
	}
	
	public Double totalStockOffice(){
		Double res = eStockOfficeRepo.totalStockOffice(1);
		Double office = res == null ? 0.00 : res;
		return office;
	}
	
	public Double totalStockStore(){
		Double res = eStockStoreRepo.totalStockStore(1);
		Double store = res == null? 0.00 : res;
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
