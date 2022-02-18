package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.KasMasuk;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanStore;
import com.gbsystem.rpbackoffice.repository.KasMasukRepository;
import com.gbsystem.rpbackoffice.repository.RekapPenjualanStoreRepository;

@Service
public class RekapPenjualanStoreService {
	@Autowired
	private RekapPenjualanStoreRepository eRepo;
	@Autowired
	private KasMasukRepository eKasMasukRepo;
	
	public RekapPenjualanStore save(RekapPenjualanStore rekapPenjualanStore) {
		long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy | HH:mm");
        String dateString = sdf.format(date);
        
		RekapPenjualanStore p = new RekapPenjualanStore();
		p.setUangMasukTunai(rekapPenjualanStore.getUangMasukTunai());
		p.setUangMasukNonTunai(rekapPenjualanStore.getUangMasukNonTunai());
		p.setWaktuMasuk(LocalDateTime.now());
		p.setId_store(rekapPenjualanStore.getId_store());
		p.setLokasi_store(rekapPenjualanStore.getLokasi_store());
		p.setId_karyawan(rekapPenjualanStore.getId_karyawan());
		p.setNama_karyawan(rekapPenjualanStore.getNama_karyawan());
		p.setCatatan(rekapPenjualanStore.getCatatan());
		p.setRowstatus(1);
		
		KasMasuk q = new KasMasuk();
		q.setNominalKasMasuk(rekapPenjualanStore.getUangMasukTunai());
		q.setWaktuMasuk(LocalDateTime.now());
		q.setId_store(rekapPenjualanStore.getId_store());
		q.setLokasi_store(rekapPenjualanStore.getLokasi_store());
		q.setId_karyawan(rekapPenjualanStore.getId_karyawan());
		q.setNama_karyawan(rekapPenjualanStore.getNama_karyawan());
		q.setCatatan("Peneriman uang tunai hasil Penjualan pada, " + dateString);
		q.setRowstatus(1);
		eKasMasukRepo.save(q);
		
		return eRepo.save(p);
	}
	
	public List<RekapPenjualanStore> all(int id_store){
		return eRepo.all(id_store);
	}
}
