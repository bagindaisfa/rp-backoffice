package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbsystem.rpbackoffice.repository.MasterKategoriRepository;
import com.gbsystem.rpbackoffice.entities.MasterKategori;

@Service
public class MasterKategoriService {
	@Autowired
	private MasterKategoriRepository eRepo;
	
	public String saveMasterKategori( String kategori_name ) {
		String response = "";
		MasterKategori p = new MasterKategori();
		p = eRepo.findByName(kategori_name);
		if (p != null) {
			response = "Kategori sudah ada!";
		} else {
			MasterKategori q = new MasterKategori();
			q.setKategori_name(kategori_name);
			q.setRowstatus(1);
			eRepo.save(q);
			response = "Sukses!";
		}
		
		return response;
	}

	public List<MasterKategori> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<MasterKategori> getAllMasterKategori(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deleteMasterKategoriById(Long id)
    {
		MasterKategori p = new MasterKategori();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, String kategori_name ) {
		MasterKategori p = new MasterKategori();
    	p = eRepo.findById(id).get();
		p.setKategori_name(kategori_name);
		p.setRowstatus(1);
    	eRepo.save(p);
	}


}
