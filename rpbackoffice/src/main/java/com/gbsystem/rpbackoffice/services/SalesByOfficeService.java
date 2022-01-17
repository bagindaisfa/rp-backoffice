package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.SalesByOffice;
import com.gbsystem.rpbackoffice.repository.SalesByOfficeRepository;

@Service
public class SalesByOfficeService {
	@Autowired
	private SalesByOfficeRepository eRepo;
	
	public List<SalesByOffice> SalesByOffice(String id_office){
		return eRepo.SalesByOffice(id_office);
	}
}
