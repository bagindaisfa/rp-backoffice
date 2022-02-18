package com.gbsystem.rpbackoffice.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.KasKeluar;
import com.gbsystem.rpbackoffice.repository.KasKeluarRepository;

@Service
public class KasKeluarService {
	@Autowired
	private KasKeluarRepository eRepo;

	public KasKeluar save(KasKeluar kasKeluar) {
		KasKeluar p = new KasKeluar();
		p.setNominalKasKeluar(Double.valueOf(kasKeluar.getNominalKasKeluar()));
		p.setWaktuKeluar(LocalDateTime.now());
		p.setId_store(kasKeluar.getId_store());
		p.setLokasi_store(kasKeluar.getLokasi_store());
		p.setId_karyawan(kasKeluar.getId_karyawan());
		p.setNama_karyawan(kasKeluar.getNama_karyawan());
		p.setCatatan(kasKeluar.getCatatan());
		p.setRowstatus(1);
		return eRepo.save(p);
	}
	
	public List<KasKeluar> all(int id_store){
		return eRepo.all(id_store);
	}
}
