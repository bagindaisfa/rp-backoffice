package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailReturGudangReport;
import com.gbsystem.rpbackoffice.repository.DetailReturGudangReportRepository;

@Service
public class DetailReturGudangService {
	
	@Autowired
	private DetailReturGudangReportRepository eReportRepo;
	
	public List<DetailReturGudangReport> ReturGudangReport(String pengiriman_code){
		return eReportRepo.allReport(pengiriman_code);
	}

}
