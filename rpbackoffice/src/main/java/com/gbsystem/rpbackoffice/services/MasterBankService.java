package com.gbsystem.rpbackoffice.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.MasterBank;
import com.gbsystem.rpbackoffice.repository.MasterBankRepository;

@Service
public class MasterBankService {
	@Autowired
	private MasterBankRepository eRepo;
	
	public MasterBank saveMasterBank( String bank_name, String owner_name, String acc_number, MultipartFile image ) {
		
		MasterBank p = new MasterBank();
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
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
	
	public MasterBank findByNorek(String acc_number) {
		return eRepo.findByNorek(acc_number);
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

