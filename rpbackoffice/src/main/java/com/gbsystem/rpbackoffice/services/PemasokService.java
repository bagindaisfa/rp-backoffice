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
	
	public String savePemasok(String kode_pemasok, String nama_pemasok, String no_hp, String email, String alamat) {
		String response = "";
		Pemasok q = new Pemasok();
		q = eRepo.findByKodePemasok(kode_pemasok);
		if (q != null) {
			response = "Pemasok sudah ada!";
		} else {
			Pemasok p = new Pemasok();
			
			p.setTanggal_join(new Date());
			p.setKode_pemasok(kode_pemasok);
			p.setNama_pemasok(nama_pemasok);
			p.setNo_hp(no_hp);
			p.setEmail(email);
			p.setAlamat(alamat);
			
			p.setRowstatus(1);
			eRepo.save(p);
			response = "Sukses!";
		}
		return response;
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
	
	public void deletePemasokById(int id)
    {
		Pemasok p = new Pemasok();
    	p = eRepo.findById(id);
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(int id, String kode_pemasok, String nama_pemasok, String no_hp, String email, String alamat) {
		Pemasok p = new Pemasok();
    	p = eRepo.findById(id);
		p.setKode_pemasok(kode_pemasok);
		p.setNama_pemasok(nama_pemasok);
		p.setNo_hp(no_hp);
		p.setEmail(email);
		p.setAlamat(alamat);
		
		p.setRowstatus(1);
		eRepo.save(p);
	}

}
