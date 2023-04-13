package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.SalesByOffice;
import com.gbsystem.rpbackoffice.repository.SalesByOfficeRepository;

@Service
public class SalesByOfficeService {
	@Autowired
	private SalesByOfficeRepository eRepo;
	
	public List<SalesByOffice> SalesByOffice(String id_office, Date date_from, Date date_to){
		Double qty = eRepo.totalQty(id_office, date_from, date_to);
		Double harga = eRepo.totalHarga(id_office, date_from, date_to);
		return eRepo.SalesByOffice(id_office, date_from, date_to,qty,harga);
	}
	
	public List<SalesByOffice> BestArticle(String id_office, Date date_from, Date date_to){
		
		return eRepo.BestArticle(id_office, date_from, date_to);
	}
}
