package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PenyimpananBarangKeluarReport;
import com.gbsystem.rpbackoffice.repository.PenyimpananBarangKeluarReportRepository;

@Service
public class PenyimpananBarangKeluarReportService {
	
	@Autowired
	private PenyimpananBarangKeluarReportRepository eRepo;
	
	public List<PenyimpananBarangKeluarReport> PenyimpananBarangKeluarReport(Date date_from, Date date_to){
		return eRepo.PenyimpananBarangKeluarReport(date_from, date_to);
	}

}
