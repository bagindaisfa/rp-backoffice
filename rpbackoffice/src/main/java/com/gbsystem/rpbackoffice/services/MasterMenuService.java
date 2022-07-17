package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbsystem.rpbackoffice.repository.MasterMenuRepository;
import com.gbsystem.rpbackoffice.entities.MasterMenu;

@Service
public class MasterMenuService {
	@Autowired
	private MasterMenuRepository eRepo;
	

	public MasterMenu saveMasterMenu( String nama_menu ) {
		
		MasterMenu p = new MasterMenu();
		p.setKode_menu("MM-"+ (eRepo.count()+1));
		p.setNama_menu(nama_menu);
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<MasterMenu> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<MasterMenu> getAllMasterMenu(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deleteMasterMenuById(int id)
    {
		MasterMenu p = new MasterMenu();
    	p = eRepo.findById(id);
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(int id, String kode_menu, String nama_menu ) {
		MasterMenu p = new MasterMenu();
    	p = eRepo.findById(id);
    	p.setKode_menu(kode_menu);
		p.setNama_menu(nama_menu);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
}
