package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.Penjualan;
import com.gbsystem.rpbackoffice.repository.PenjualanRepository;

@Service
public class PenjualanService {
	
	@Autowired
	private PenjualanRepository eRepo;
	
	public void savePenjualan(String id_transaksi, String id_store, String lokasi_store, 
			String nama_pelanggan, String nama_karyawan,
			double diskon, String metode_bayar, String ekspedisi, double ongkir,
			double total, double kembalian) {
		
		Penjualan p = new Penjualan();
		p.setTanggal_transaksi(new Date());
		p.setId_transaksi(id_transaksi);
		p.setId_store(id_store);
		p.setLokasi_store(lokasi_store);
		p.setDiskon(diskon);
		p.setMetode_bayar(metode_bayar);
		p.setEkspedisi(ekspedisi);
		p.setOngkir(ongkir);
		p.setTotal(total);
		p.setKembalian(kembalian);
		p.setNama_pelanggan(nama_pelanggan);
		p.setNama_karyawan(nama_karyawan);
		p.setRowstatus(1);
		eRepo.save(p);
	}
	public List<Penjualan> getAllPenjualan(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<Penjualan> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public void deletePenjualanById(Long id)
    {
		Penjualan p = new Penjualan();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, Date tanggal_transaksi, String id_transaksi, String id_store, String lokasi_store,
			String nama_pelanggan, String nama_karyawan,
			double diskon, String metode_bayar, String ekspedisi, double ongkir,
			double total, double kembalian) {
		Penjualan p = new Penjualan();
    	p = eRepo.findById(id).get();
    	
		p.setTanggal_transaksi(tanggal_transaksi);
		p.setId_transaksi(id_transaksi);
		p.setId_store(id_store);
		p.setLokasi_store(lokasi_store);
		p.setDiskon(diskon);
		p.setMetode_bayar(metode_bayar);
		p.setEkspedisi(ekspedisi);
		p.setOngkir(ongkir);
		p.setTotal(total);
		p.setKembalian(kembalian);
		p.setNama_pelanggan(nama_pelanggan);
		p.setNama_karyawan(nama_karyawan);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
	
//	public void changePenjualanTanggal_transaksi(Long id ,Date tanggal_transaksi)
//    {
//		Penjualan p = new Penjualan();
//    	p = eRepo.findById(id).get();
//    	p.setTanggal_transaksi(tanggal_transaksi);
//    	eRepo.save(p);    
//    }
//	
//	public void changePenjualanId_transaksi(Long id ,String id_transaksi)
//    {
//		Penjualan p = new Penjualan();
//    	p = eRepo.findById(id).get();
//    	p.setId_transaksi(id_transaksi);
//    	eRepo.save(p);    
//    }
//	
//	public void changePenjualanId_store(Long id ,String id_store)
//    {
//		Penjualan p = new Penjualan();
//    	p = eRepo.findById(id).get();
//    	p.setId_store(id_store);
//    	eRepo.save(p);    
//    }
//	
//	public void changePenjualanLokasi_store(Long id ,String lokasi_store)
//    {
//		Penjualan p = new Penjualan();
//    	p = eRepo.findById(id).get();
//    	p.setLokasi_store(lokasi_store);
//    	eRepo.save(p);    
//    }
//	
//	public void changePenjualanKuantitas(Long id ,int kuantitas)
//    {
//		Penjualan p = new Penjualan();
//    	p = eRepo.findById(id).get();
//    	p.setKuantitas(kuantitas);
//    	eRepo.save(p);    
//    }
//	
//	public void changePenjualanTotal(Long id ,double total)
//    {
//		Penjualan p = new Penjualan();
//    	p = eRepo.findById(id).get();
//    	p.setTotal(total);
//    	eRepo.save(p);    
//    }

}
