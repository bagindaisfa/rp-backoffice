package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PenerimaanBySupplierReport;
import com.gbsystem.rpbackoffice.repository.PenerimaanBySupplierReportRepository;

@Service
public class PenerimaanBySupplierReportService {
	
	@Autowired
	private PenerimaanBySupplierReportRepository eRepo;
	
	public List<PenerimaanBySupplierReport> PenerimaanBySupplierReport(Date date_from, Date date_to){
		return eRepo.PenerimaanBySupplierReport(date_from, date_to);
	}

}
