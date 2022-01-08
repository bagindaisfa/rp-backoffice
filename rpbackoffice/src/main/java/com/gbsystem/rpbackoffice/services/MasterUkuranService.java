package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbsystem.rpbackoffice.repository.MasterUkuranRepository;
import com.gbsystem.rpbackoffice.entities.MasterUkuran;

@Service
public class MasterUkuranService {
	@Autowired
	private MasterUkuranRepository eRepo;
	
	public MasterUkuran saveMasterUkuran( String ukuran ) {
		
		MasterUkuran p = new MasterUkuran();
		p.setUkuran(ukuran);
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<MasterUkuran> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<MasterUkuran> getAllMasterUkuran(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deleteMasterUkuranById(Long id)
    {
		MasterUkuran p = new MasterUkuran();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, String ukuran ) {
		MasterUkuran p = new MasterUkuran();
    	p = eRepo.findById(id).get();
    	p.setUkuran(ukuran);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
}
