package com.gbsystem.rpbackoffice.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.KasMasuk;
import com.gbsystem.rpbackoffice.repository.KasMasukRepository;

@Service
public class KasMasukService {
	@Autowired
	private KasMasukRepository eRepo;

	public KasMasuk save(KasMasuk kasMasuk) {
		KasMasuk p = new KasMasuk();
		p.setNominalKasMasuk(Double.valueOf(kasMasuk.getNominalKasMasuk()));
		p.setWaktuMasuk(LocalDateTime.now());
		p.setId_store(kasMasuk.getId_store());
		p.setLokasi_store(kasMasuk.getLokasi_store());
		p.setId_karyawan(kasMasuk.getId_karyawan());
		p.setNama_karyawan(kasMasuk.getNama_karyawan());
		p.setCatatan(kasMasuk.getCatatan());
		p.setRowstatus(1);
		return eRepo.save(p);
	}
	
	public List<KasMasuk> all(int id_store){
		return eRepo.all(id_store);
	}
}
