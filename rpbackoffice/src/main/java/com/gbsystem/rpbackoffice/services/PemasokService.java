package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.Pemasok;
import com.gbsystem.rpbackoffice.repository.PemasokRepository;

@Service
public class PemasokService {
	
	@Autowired
	private PemasokRepository eRepo;
	
	public Pemasok savePemasok(String kode_pemasok, String nama_pemasok, String no_hp, String email, String alamat) {
		
		Pemasok p = new Pemasok();
		
		p.setTanggal_join(new Date());
		p.setKode_pemasok(kode_pemasok);
		p.setNama_pemasok(nama_pemasok);
		p.setNo_hp(no_hp);
		p.setEmail(email);
		p.setAlamat(alamat);
		
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<Pemasok> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public void download(){
		eRepo.download();
	}
	
	public List<Pemasok> getAllPemasok(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deletePemasokById(Long id)
    {
		Pemasok p = new Pemasok();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(int id, String kode_pemasok, String nama_pemasok, String no_hp, String email, String alamat) {
		Pemasok p = new Pemasok();
    	p = eRepo.findById(Long.valueOf(id)).get();
		p.setKode_pemasok(kode_pemasok);
		p.setNama_pemasok(nama_pemasok);
		p.setNo_hp(no_hp);
		p.setEmail(email);
		p.setAlamat(alamat);
		
		p.setRowstatus(1);
		eRepo.save(p);
	}

}
