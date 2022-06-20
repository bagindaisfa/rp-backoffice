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
import com.gbsystem.rpbackoffice.entities.MasterOffice;
import com.gbsystem.rpbackoffice.entities.MasterStore;
import com.gbsystem.rpbackoffice.repository.KaryawanRepository;
import com.gbsystem.rpbackoffice.repository.MasterOfficeRepository;
import com.gbsystem.rpbackoffice.repository.MasterStoreRepository;

@Service
public class KaryawanService {

	@Autowired
	private KaryawanRepository eRepo;
	
	@Autowired
	private MasterOfficeRepository eOfficeRepo;
	
	@Autowired
	private MasterStoreRepository eStoreRepo;
	
	public Karyawan saveKaryawan(
			Date tanggal_join,String nama_karyawan,Date tanggal_lahir,
			int id_office, String lokasi_office,int id_store,
			String lokasi_store,String jabatan,String no_hp,
			String email, String alamat,
			MultipartFile image) {
		
		Karyawan k = new Karyawan();
		MasterOffice office = new MasterOffice();
		office = eOfficeRepo.getById(Long.valueOf(id_office));
		
		MasterStore store = new MasterStore();
		store = eStoreRepo.getById(Long.valueOf(id_store));
		
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			k.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		k.setTanggal_join(tanggal_join);
		k.setNama_karyawan(nama_karyawan);
		k.setTanggal_lahir(tanggal_lahir);
		k.setId_office(id_office);
		k.setLokasi_office(office.getOffice_name());
		k.setId_store(id_store);
		k.setLokasi_store(store.getStore_name());
		k.setJabatan(jabatan);
		k.setNo_hp(no_hp);
		k.setEmail(email);
		k.setAlamat(alamat);
		k.setTotal_transaksi(0.0);
		k.setRowstatus(1);
		return eRepo.save(k);
	}

	public List<Karyawan> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<Karyawan> searchForStore(String keyword, int id_store){
		return eRepo.searchForStore(keyword, id_store);
	}
	
	public List<Karyawan> getAllKaryawan(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<Karyawan> getAllByIdStore(int id_store){

		return eRepo.getAllByIdStore(id_store);
	}
	
	public void deleteKaryawanById(Long id)
    {
		Karyawan k = new Karyawan();
    	k = eRepo.findById(id).get();
    	k.setRowstatus(0);
    	eRepo.save(k);    
    }
	
	public void update(Long id, Date tanggal_join,String nama_karyawan,Date tanggal_lahir, int id_office, String lokasi_office, int id_store, String lokasi_store, String jabatan
			,String no_hp, String email, String alamat, double total_transaksi, MultipartFile image) {
		Karyawan k = new Karyawan();
		k = eRepo.findById(id).get();
		
		MasterOffice office = new MasterOffice();
		office = eOfficeRepo.getById(Long.valueOf(id_office));
		
		MasterStore store = new MasterStore();
		store = eStoreRepo.getById(Long.valueOf(id_store));
		
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			k.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		k.setTanggal_join(tanggal_join);
		k.setNama_karyawan(nama_karyawan);
		k.setTanggal_lahir(tanggal_lahir);
		k.setId_office(id_office);
		k.setLokasi_office(office.getOffice_name());
		k.setId_store(id_store);
		k.setLokasi_store(store.getStore_name());
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
		
		MasterStore store = new MasterStore();
		store = eStoreRepo.getById(Long.valueOf(karyawan.getId_store()));
		
		k.setId_store(karyawan.getId_store());
		k.setLokasi_store(store.getStore_name());
		eRepo.save(k);
	}
	
}
