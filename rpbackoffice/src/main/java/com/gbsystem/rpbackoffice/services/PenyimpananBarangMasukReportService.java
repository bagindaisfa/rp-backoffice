package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PenyimpananBarangMasukReport;
import com.gbsystem.rpbackoffice.repository.PenyimpananBarangMasukReportRepository;

@Service
public class PenyimpananBarangMasukReportService {
	
	@Autowired
	private PenyimpananBarangMasukReportRepository eRepo;
	
	public List<PenyimpananBarangMasukReport> PenyimpananBarangMasukReport(Date date_from, Date date_to){
		return eRepo.PenyimpananBarangMasukReport(date_from, date_to);
	}

}
