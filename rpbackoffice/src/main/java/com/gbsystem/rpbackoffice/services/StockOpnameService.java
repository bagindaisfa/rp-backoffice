package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.StockOpname;
import com.gbsystem.rpbackoffice.repository.StockOpnameRepository;

@Service
public class StockOpnameService {
	@Autowired
	private StockOpnameRepository eRepo;
	
	public StockOpname saveStockOpname(String artikel, String kategori
			,String tipe, String nama_barang, double kuantitas, String ukuran, double hpp, double harga_jual, String keterangan ) {
		
		StockOpname p = new StockOpname();
		
		p.setTanggal_transaksi(new Date());
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

	public List<StockOpname> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<StockOpname> getAllStockOpname(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deleteStockOpnameById(Long id)
    {
		StockOpname p = new StockOpname();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, String artikel, String kategori, String tipe, String nama_barang, double kuantitas, String ukuran, double hpp, double harga_jual, String keterangan ) {
		StockOpname p = new StockOpname();
    	p = eRepo.findById(id).get();
    	
		p.setTanggal_transaksi(new Date());
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
