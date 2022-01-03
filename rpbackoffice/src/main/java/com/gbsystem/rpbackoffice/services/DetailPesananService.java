package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPesanan;
import com.gbsystem.rpbackoffice.repository.DetailPesananRepository;

@Service
public class DetailPesananService {
	
	@Autowired
	private DetailPesananRepository eRepo;
	
	public void saveDetailPesanan(String id_transaksi, int id_store, String lokasi_store, 
			String nama_barang, double harga, double kuantitas, double total) {
		
		DetailPesanan p = new DetailPesanan();
		p.setTanggal_transaksi(new Date());
		p.setId_transaksi(id_transaksi);
		p.setId_store(id_store);
		p.setLokasi_store(lokasi_store);
		p.setNama_barang(nama_barang);
		p.setHarga(harga);
		p.setKuantitas(kuantitas);
		p.setTotal(total);
		p.setRowstatus(1);
		eRepo.save(p);
	}
	public List<DetailPesanan> getAllDetailPesanan(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<DetailPesanan> all(String id_transaksi){
		return eRepo.all(id_transaksi);
	}
	
	public List<DetailPesanan> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public void deleteDetailPesananById(Long id)
    {
		DetailPesanan p = new DetailPesanan();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, Date tanggal_transaksi, String id_transaksi, int id_store, String lokasi_store, 
			String nama_barang, double harga, double kuantitas, double total) {
		DetailPesanan p = new DetailPesanan();
    	p = eRepo.findById(id).get();
    	
		p.setTanggal_transaksi(tanggal_transaksi);
		p.setId_transaksi(id_transaksi);
		p.setId_store(id_store);
		p.setLokasi_store(lokasi_store);
		p.setNama_barang(nama_barang);
		p.setHarga(harga);
		p.setKuantitas(kuantitas);
		p.setTotal(total);
		p.setRowstatus(1);
    	eRepo.save(p);
	}

}
