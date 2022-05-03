package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.MasterBank;
import com.gbsystem.rpbackoffice.repository.MasterBankRepository;

@Service
public class MasterBankService {
	@Autowired
	private MasterBankRepository eRepo;
	
	public MasterBank saveMasterBank( String bank_name, String acc_number, String owner_name ) {
		
		MasterBank p = new MasterBank();
		p.setBank_name(bank_name);
		p.setOwner_name(owner_name);
		p.setAcc_number(acc_number);
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<MasterBank> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<MasterBank> getAllMasterBank(){

		return eRepo.findByRowstatus(1);
	}
	
	public MasterBank findByName(String bank_name) {
		return eRepo.findByName(bank_name);
	}
	
	public MasterBank findByOwner(String owner_name) {
		return eRepo.findByOwner(owner_name);
	}
	
	public void deleteMasterBankById(Long id)
    {
		MasterBank p = new MasterBank();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, String bank_name, String owner_name, String acc_number ) {
		MasterBank p = new MasterBank();
    	p = eRepo.findById(id).get();
		p.setBank_name(bank_name);
		p.setOwner_name(owner_name);
		p.setAcc_number(acc_number);
		p.setRowstatus(1);
    	eRepo.save(p);
	}

}

