package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PenerimaanByStoreReport;
import com.gbsystem.rpbackoffice.repository.PenerimaanByStoreReportRepository;

@Service
public class PenerimaanByStoreReportService {
	
	@Autowired
	private PenerimaanByStoreReportRepository eRepo;
	
	public List<PenerimaanByStoreReport> PenerimaanByStoreReport(Date tanggal_transaksi){
		return eRepo.PenerimaanByStoreReport(tanggal_transaksi);
	}

}
