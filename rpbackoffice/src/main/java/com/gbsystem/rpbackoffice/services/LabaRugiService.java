package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.repository.LabaRugiRepository;
import com.gbsystem.rpbackoffice.entities.LabaRugi;


@Service
public class LabaRugiService {
	@Autowired
	private LabaRugiRepository eRepo;
	
	public List<LabaRugi> labaRugi(String year){
		return eRepo.findLabaRugi(year);
	}
	
	public List<LabaRugi> neracaKeuangan(String year){
		return eRepo.findNeracaKeuangan(year);
	}
}
