package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PengirimanGudangToStoreReport;
import com.gbsystem.rpbackoffice.entities.TransferRequest;
import com.gbsystem.rpbackoffice.repository.PengirimanGudangToStoreReportRepository;
import com.gbsystem.rpbackoffice.repository.TransferRequestRepository;

@Service
public class PengirimanGudangToStoreReportService {
	
	@Autowired
	private PengirimanGudangToStoreReportRepository eRepo;
	
	@Autowired
	private TransferRequestRepository eTFRepo;
	
	public List<PengirimanGudangToStoreReport> PengirimanGudangToStoreReport(Date date_from, Date date_to){
		return eRepo.PengirimanGudangToStoreReport(date_from, date_to);
	}
	
	public List<TransferRequest> TransferRequest(String pengiriman_code){
		return eTFRepo.TransferRequest(pengiriman_code);
	}
	
}
