package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PurchaseStoreByArticle;
import com.gbsystem.rpbackoffice.repository.PurchaseStoreByArticleRepository;

@Service
public class PurchaseStoreByArticleService {
	@Autowired
	private PurchaseStoreByArticleRepository eRepo;
	
	public List<PurchaseStoreByArticle> PurchaseStoreByArticle(String artikel, Date date_from, Date date_to){
		return eRepo.PurchaseStoreByArticle(artikel, date_from, date_to);
	}
	
	public List<PurchaseStoreByArticle> BestArticle(String id_store, Date date_from, Date date_to){
		return eRepo.BestArticle(id_store, date_from, date_to);
	}
}
