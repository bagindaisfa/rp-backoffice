package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.repository.ChartOfAccountRepository;
import com.gbsystem.rpbackoffice.entities.ChartOfAccount;

@Service
public class ChartOfAccountService {
	@Autowired
	private ChartOfAccountRepository eRepo;
	
	public ChartOfAccount saveChartOfAccount(
			String nama_akun, String kelompok, 
			String tipe, String relasi, String jenis_beban
			) {
		
		ChartOfAccount p = new ChartOfAccount();
		
		p.setNama_akun(nama_akun);
		p.setKelompok(kelompok);
		p.setTipe(tipe);
		p.setRelasi(relasi);
		p.setJenis_beban(jenis_beban);
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<ChartOfAccount> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<ChartOfAccount> getAllChartOfAccount(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<ChartOfAccount> getChartOfAccount(List<String> nama_akun){

		return eRepo.findByChartOfAccountNama_akun(nama_akun);
	}
	
	public void deleteChartOfAccountById(Long id)
    {
		ChartOfAccount p = new ChartOfAccount();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(
			Long id, String nama_akun, String kelompok, 
			String tipe, String relasi, String jenis_beban ) {
		ChartOfAccount p = new ChartOfAccount();
    	p = eRepo.findById(id).get();
    	p.setNama_akun(nama_akun);
		p.setKelompok(kelompok);
		p.setTipe(tipe);
		p.setRelasi(relasi);
		p.setJenis_beban(jenis_beban);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
}
