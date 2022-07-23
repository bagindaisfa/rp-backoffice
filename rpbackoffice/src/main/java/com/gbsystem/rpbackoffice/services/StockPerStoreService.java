package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockPerStoreList;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreKeluarRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockPerStoreListRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class StockPerStoreService {
	
	@Autowired
	private PenyimpananStoreMasukRepository eMasukRepo;
	
	@Autowired
	private PenyimpananStoreKeluarRepository eKeluarRepo;
	
	@Autowired
	private StockStoreRepository eStockStoreRepo;
	
	@Autowired
	private StockPerStoreListRepository eListRepo;
	
	public List<PenyimpananStoreMasuk> storeMasuk (int id_store, Date date_from, Date date_to) {
		List<PenyimpananStoreMasuk> storeMasuk = eMasukRepo.allPenyimpananStoreMasuk(id_store, date_from, date_to);
		return storeMasuk;
	}
	
	public List<PenyimpananStoreKeluar> storeKeluar (int id_store, Date date_from, Date date_to) {
		List<PenyimpananStoreKeluar> storeMasuk = eKeluarRepo.allPenyimpananStoreKeluar(id_store, date_from, date_to);
		return storeMasuk;
	}
	
	public List<StockStore> stockAllItemPerStore(int id_store) {
		
		return eStockStoreRepo.stockAllItemPerStore(id_store);
	}
	
	public List<StockPerStoreList> allStock() {
		
		return eListRepo.allStock();
	}
}
