package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.SalesByStore;
import com.gbsystem.rpbackoffice.repository.SalesByStoreRepository;

@Service
public class SalesByStoreService {
	@Autowired
	private SalesByStoreRepository eRepo;
	
	public List<SalesByStore> SalesByStore(String id_store, Date date_from, Date date_to){
		Double qty = eRepo.totalQty(id_store, date_from, date_to);
		Double harga = eRepo.totalHarga(id_store, date_from, date_to);
		return eRepo.SalesByStore(id_store, date_from, date_to, qty, harga);
	}
	
	public List<SalesByStore> BestArticle(String id_store, Date date_from, Date date_to){
		
		return eRepo.BestArticle(id_store, date_from, date_to);
	}
}
