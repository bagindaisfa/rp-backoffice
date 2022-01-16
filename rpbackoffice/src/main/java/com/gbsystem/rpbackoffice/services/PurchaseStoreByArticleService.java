package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PurchaseStoreByArticle;
import com.gbsystem.rpbackoffice.repository.PurchaseStoreByArticleRepository;

@Service
public class PurchaseStoreByArticleService {
	@Autowired
	private PurchaseStoreByArticleRepository eRepo;
	
	public List<PurchaseStoreByArticle> PurchaseStoreByArticle(String artikel){
		return eRepo.PurchaseStoreByArticle(artikel);
	}
}
