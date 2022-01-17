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
	
	public List<PenyimpananBarangMasukReport> PenyimpananBarangMasukReport(Date tanggal_transaksi){
		return eRepo.PenyimpananBarangMasukReport(tanggal_transaksi);
	}

}
