package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbsystem.rpbackoffice.repository.MasterStoreRepository;
import com.gbsystem.rpbackoffice.entities.MasterStore;

@Service
public class MasterStoreService {
	@Autowired
	private MasterStoreRepository eRepo;
	
	public MasterStore saveMasterStore( String store_name, String no_tlpn, String alamat, String kepala_store ) {
		
		MasterStore p = new MasterStore();
		p.setStore_name(store_name);
		p.setNo_tlpn(no_tlpn);
		p.setAlamat(alamat);
		p.setKepala_store(kepala_store);
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<MasterStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<MasterStore> getAllMasterStore(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deleteMasterStoreById(Long id)
    {
		MasterStore p = new MasterStore();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, String store_name, String no_tlpn, String alamat, String kepala_store ) {
		MasterStore p = new MasterStore();
    	p = eRepo.findById(id).get();
    	p.setStore_name(store_name);
		p.setNo_tlpn(no_tlpn);
		p.setAlamat(alamat);
		p.setKepala_store(kepala_store);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
}
