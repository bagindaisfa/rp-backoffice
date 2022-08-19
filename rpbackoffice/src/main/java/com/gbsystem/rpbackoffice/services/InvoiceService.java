package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.Invoice;
import com.gbsystem.rpbackoffice.repository.InvoiceOfficeRepository;

@Service
public class InvoiceService {
	@Autowired
	private InvoiceOfficeRepository eRepo;
	
	public List<Invoice> Invoice(int id_office, String id_transaksi) {
		return eRepo.Invoice(id_office, id_transaksi);
	}
}
