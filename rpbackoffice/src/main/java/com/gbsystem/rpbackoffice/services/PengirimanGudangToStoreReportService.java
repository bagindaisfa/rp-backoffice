package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PengirimanGudangToStoreReport;
import com.gbsystem.rpbackoffice.repository.PengirimanGudangToStoreReportRepository;

@Service
public class PengirimanGudangToStoreReportService {
	
	@Autowired
	private PengirimanGudangToStoreReportRepository eRepo;
	
	public List<PengirimanGudangToStoreReport> PengirimanGudangToStoreReport(Date tanggal_transaksi){
		return eRepo.PengirimanGudangToStoreReport(tanggal_transaksi);
	}

}
