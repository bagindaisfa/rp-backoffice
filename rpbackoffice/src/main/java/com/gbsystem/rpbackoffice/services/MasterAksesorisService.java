package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbsystem.rpbackoffice.repository.MasterAksesorisRepository;
import com.gbsystem.rpbackoffice.entities.MasterAksesoris;

@Service
public class MasterAksesorisService {
	@Autowired
	private MasterAksesorisRepository eRepo;
	
	public MasterAksesoris saveMasterAksesoris( String artikel_aksesoris, String nama_aksesoris, String type, String type_name, String kategori, String nama_kategori, double kuantitas,double hpp,double harga_jual
			) {
		
		MasterAksesoris p = new MasterAksesoris();
		p.setArtikel_aksesoris(artikel_aksesoris);
		p.setNama_aksesoris(nama_aksesoris);
		p.setType(type);
		p.setType_name(type_name);
		p.setKategori(kategori);
		p.setNama_kategori(nama_kategori);
		p.setKuantitas(kuantitas);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<MasterAksesoris> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<MasterAksesoris> getAllMasterAksesoris(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deleteMasterAksesorisById(Long id)
    {
		MasterAksesoris p = new MasterAksesoris();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, String artikel_aksesoris, String nama_aksesoris, String type, String type_name, String kategori, String nama_kategori, double kuantitas,double hpp,double harga_jual ) {
		MasterAksesoris p = new MasterAksesoris();
    	p = eRepo.findById(id).get();
    	p.setArtikel_aksesoris(artikel_aksesoris);
		p.setNama_aksesoris(nama_aksesoris);
		p.setType(type);
		p.setType_name(type_name);
		p.setKategori(kategori);
		p.setNama_kategori(nama_kategori);
		p.setKuantitas(kuantitas);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRowstatus(1);
    	eRepo.save(p);
	}

}

