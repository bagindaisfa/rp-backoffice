package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.repository.NeracaSaldoRepository;
import com.gbsystem.rpbackoffice.entities.NeracaSaldo;

@Service
public class NeracaSaldoService {
	@Autowired
	private NeracaSaldoRepository eNeracaRepo;
	
	public List<NeracaSaldo> neracaSaldo(String year){
		return eNeracaRepo.findNeracaSaldo(year);
	}
	
}
