package com.gbsystem.rpbackoffice.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.Karyawan;
import com.gbsystem.rpbackoffice.repository.KaryawanRepository;

@Service
public class KaryawanService {

	@Autowired
	private KaryawanRepository eRepo;
	
	public Karyawan saveKaryawan(String nama_karyawan,int id_office, String lokasi_office, int id_store, String lokasi_store,String jabatan
			,String no_hp, String email, String alamat, double total_transaksi, MultipartFile image) {
		
		Karyawan k = new Karyawan();
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			k.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		k.setTanggal_join(new Date());
		k.setId_office(id_office);
		k.setLokasi_office(lokasi_office);
		k.setId_store(id_store);
		k.setLokasi_store(lokasi_store);
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
	
	public void update(Long id, String nama_karyawan, int id_office, String lokasi_office, int id_store, String lokasi_store, String jabatan
			,String no_hp, String email, String alamat, double total_transaksi, MultipartFile image) {
		Karyawan k = new Karyawan();
		k = eRepo.findById(id).get();
		
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			k.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		k.setNama_karyawan(nama_karyawan);
		k.setId_office(id_office);
		k.setLokasi_office(lokasi_office);
		k.setId_store(id_store);
		k.setLokasi_store(lokasi_store);
		k.setJabatan(jabatan);
		k.setNo_hp(no_hp);
		k.setEmail(email);
		k.setAlamat(alamat);
		k.setTotal_transaksi(total_transaksi);
		k.setRowstatus(1);
    	eRepo.save(k);
	}
	
	public void pindahStore(Karyawan karyawan) {
		Karyawan k = new Karyawan();
		k = eRepo.findById(karyawan.getId()).get();
		k.setId_store(karyawan.getId_store());
		k.setLokasi_store(karyawan.getLokasi_store());
		eRepo.save(k);
	}
	
}
