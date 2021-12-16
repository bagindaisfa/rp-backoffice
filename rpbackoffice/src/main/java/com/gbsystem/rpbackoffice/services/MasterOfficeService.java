package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbsystem.rpbackoffice.repository.MasterOfficeRepository;
import com.gbsystem.rpbackoffice.entities.MasterOffice;

@Service
public class MasterOfficeService {
	@Autowired
	private MasterOfficeRepository eRepo;
	
	public MasterOffice saveMasterOffice( String office_name, String no_tlpn, String alamat, String kepala_office ) {
		
		MasterOffice p = new MasterOffice();
		p.setOffice_name(office_name);
		p.setNo_tlpn(no_tlpn);
		p.setAlamat(alamat);
		p.setKepala_office(kepala_office);
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<MasterOffice> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<MasterOffice> getAllMasterOffice(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deleteMasterOfficeById(Long id)
    {
		MasterOffice p = new MasterOffice();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, String office_name, String no_tlpn, String alamat, String kepala_office ) {
		MasterOffice p = new MasterOffice();
    	p = eRepo.findById(id).get();
    	p.setOffice_name(office_name);
		p.setNo_tlpn(no_tlpn);
		p.setAlamat(alamat);
		p.setKepala_office(kepala_office);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
}
