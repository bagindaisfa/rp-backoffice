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
	
	public List<PenerimaanBySupplierReport> PenerimaanBySupplierReport(Date tanggal_transaksi){
		return eRepo.PenerimaanBySupplierReport(tanggal_transaksi);
	}

}
