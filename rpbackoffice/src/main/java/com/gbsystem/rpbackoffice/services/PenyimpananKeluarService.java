package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;
import com.gbsystem.rpbackoffice.repository.PenyimpananKeluarRepository;

@Service
public class PenyimpananKeluarService {

	@Autowired
	private PenyimpananKeluarRepository eRepo;
	
	public PenyimpananKeluar savePenyimpananKeluar(Long id_store, String lokasi_store, String artikel, String kategori
			,String tipe, String nama_barang, double kuantitas, String ukuran, double hpp, double harga_jual, String keterangan ) {
		
		PenyimpananKeluar p = new PenyimpananKeluar();
		
		p.setTanggal_transaksi(new Date());
		p.setId_store(id_store);
		p.setLokasi_store(lokasi_store);
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setTipe(tipe);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setTotal_hpp(kuantitas * harga_jual);
		p.setKeterangan(keterangan);
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<PenyimpananKeluar> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PenyimpananKeluar> getAllPenyimpananKeluar(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deletePenyimpananKeluarById(Long id)
    {
		PenyimpananKeluar p = new PenyimpananKeluar();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, Long id_store, String lokasi_store, String artikel, String kategori, String tipe, String nama_barang, double kuantitas, String ukuran, double hpp, double harga_jual, String keterangan ) {
		PenyimpananKeluar p = new PenyimpananKeluar();
    	p = eRepo.findById(id).get();
    	
		p.setTanggal_transaksi(new Date());
		p.setId_store(id_store);
		p.setLokasi_store(lokasi_store);
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setTipe(tipe);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setTotal_hpp(kuantitas * harga_jual);
		p.setKeterangan(keterangan);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
}
