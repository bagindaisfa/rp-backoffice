package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStoreReport;
import com.gbsystem.rpbackoffice.entities.TransferRequest;
import com.gbsystem.rpbackoffice.repository.PengirimanStoreToStoreReportRepository;
import com.gbsystem.rpbackoffice.repository.TransferRequestRepository;

@Service
public class PengirimanStoreToStoreReportService {
	
	@Autowired
	private PengirimanStoreToStoreReportRepository eRepo;
	
	@Autowired
	private TransferRequestRepository eTFRepo;
	
	public List<PengirimanStoreToStoreReport> PengirimanStoreToStoreReport(Date date_from, Date date_to){
		return eRepo.PengirimanStoreToStoreReport(date_from, date_to);
	}
	
	public List<TransferRequest> TransferRequestStore(String pengiriman_code){
		return eTFRepo.TransferRequestStore(pengiriman_code);
	}

}
