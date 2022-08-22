package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbsystem.rpbackoffice.repository.MasterTipeRepository;
import com.gbsystem.rpbackoffice.entities.MasterTipe;

@Service
public class MasterTipeService {
	@Autowired
	private MasterTipeRepository eRepo;
	
	public String saveMasterTipe( String type_name ) {
		String response = "";
		MasterTipe q = new MasterTipe();
		q = eRepo.findByName(type_name);
		if (q!=null) {
			response = "Tipe sudah ada!";
		} else {
			MasterTipe p = new MasterTipe();
			p.setType_name(type_name);
			p.setRowstatus(1);
			eRepo.save(p);
			response = "Sukses!";
		}
		
		return response;
	}

	public List<MasterTipe> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<MasterTipe> getAllMasterTipe(){

		return eRepo.findByRowstatus(1);
	}
	
	public MasterTipe findByName(String type_name) {
		return eRepo.findByName(type_name);
	}
	
	public void deleteMasterTipeById(int id)
    {
		MasterTipe p = new MasterTipe();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(int id, String type_name ) {
		MasterTipe p = new MasterTipe();
    	p = eRepo.findById(id).get();
		p.setType_name(type_name);
		p.setRowstatus(1);
    	eRepo.save(p);
	}

}
