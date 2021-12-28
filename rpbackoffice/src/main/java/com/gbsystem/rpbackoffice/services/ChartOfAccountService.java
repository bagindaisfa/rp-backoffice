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
			String noAkun,String namaAkun, String kelompok, 
			String tipe, String saldo_normal, double saldo_awal
			) {
		ChartOfAccount p = new ChartOfAccount();
		p.setNoAkun(noAkun);
		p.setNamaAkun(namaAkun);
		p.setKelompok(kelompok);
		p.setTipe(tipe);
		p.setSaldo_normal(saldo_normal);
		p.setSaldo_awal(saldo_awal);
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

		return eRepo.findByChartOfAccountNo_akun(nama_akun);
	}
	
	public void deleteChartOfAccountById(Long id)
    {
		ChartOfAccount p = new ChartOfAccount();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(
			Long id, String noAkun,String namaAkun, String kelompok, 
			String tipe, String saldo_normal, double saldo_awal ) {
		ChartOfAccount p = new ChartOfAccount();
    	p = eRepo.findById(id).get();
    	p.setNoAkun(noAkun);
		p.setNamaAkun(namaAkun);
		p.setKelompok(kelompok);
		p.setTipe(tipe);
		p.setSaldo_normal(saldo_normal);
		p.setSaldo_awal(saldo_awal);
    	eRepo.save(p);
	}
}
