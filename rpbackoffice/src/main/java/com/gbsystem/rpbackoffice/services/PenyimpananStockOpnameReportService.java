package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PenyimpananStockOpnameReport;
import com.gbsystem.rpbackoffice.entities.PenyimpananStockOpnameStoreReport;
import com.gbsystem.rpbackoffice.repository.PenyimpananStockOpnameReportRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStockOpnameStoreReportRepository;

@Service
public class PenyimpananStockOpnameReportService {
	
	@Autowired
	private PenyimpananStockOpnameReportRepository eRepo;
	
	@Autowired
	private PenyimpananStockOpnameStoreReportRepository eStoreRepo;
	
	public List<PenyimpananStockOpnameReport> PenyimpananStockOpnameReport(Date date_from, Date date_to){
		return eRepo.PenyimpananStockOpnameReport(date_from, date_to);
	}
	
	public List<PenyimpananStockOpnameStoreReport> PenyimpananStockOpnameStoreReport(int id_store, Date date_from, Date date_to){
		return eStoreRepo.PenyimpananStockOpnameStoreReport(id_store,date_from, date_to);
	}

}
