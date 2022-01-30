package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PurchaseStoreBySummary;
import com.gbsystem.rpbackoffice.repository.PurchaseStoreBySummaryRepository;

@Service
public class PurchaseStoreBySummaryService {

	@Autowired
	private PurchaseStoreBySummaryRepository eRepo;
	
	public List<PurchaseStoreBySummary> PurchaseStoreBySummary(String no_hp_pelanggan, Date date_from, Date date_to){
		return eRepo.PurchaseStoreBySummary(no_hp_pelanggan, date_from, date_to);
	}
}
