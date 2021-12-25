package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.Karyawan;
import com.gbsystem.rpbackoffice.repository.KaryawanRepository;

@Service
public class KaryawanService {

	@Autowired
	private KaryawanRepository eRepo;
	
	public Karyawan saveKaryawan(String nama_karyawan, String lokasi_office, String jabatan
			,String no_hp, String email, String alamat, double total_transaksi) {
		
		Karyawan k = new Karyawan();
		
		k.setTanggal_join(new Date());
		k.setLokasi_office(lokasi_office);
		k.setJabatan(jabatan);
		k.setNo_hp(no_hp);
		k.setEmail(email);
		k.setAlamat(alamat);
		k.setTotal_transaksi(total_transaksi);
		k.setRowstatus(1);
		return eRepo.save(k);
	}

	public List<Karyawan> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<Karyawan> getAllKaryawan(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deleteKaryawanById(Long id)
    {
		Karyawan k = new Karyawan();
    	k = eRepo.findById(id).get();
    	k.setRowstatus(0);
    	eRepo.save(k);    
    }
	
	public void update(Long id, String nama_karyawan, String lokasi_office, String jabatan
			,String no_hp, String email, String alamat, double total_transaksi) {
		Karyawan k = new Karyawan();
    	
		k.setNama_karyawan(nama_karyawan);
		k.setLokasi_office(lokasi_office);
		k.setJabatan(jabatan);
		k.setNo_hp(no_hp);
		k.setEmail(email);
		k.setAlamat(alamat);
		k.setTotal_transaksi(total_transaksi);
		k.setRowstatus(1);
    	eRepo.save(k);
	}
	
}
