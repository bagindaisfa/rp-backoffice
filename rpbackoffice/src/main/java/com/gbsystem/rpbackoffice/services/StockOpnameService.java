package com.gbsystem.rpbackoffice.services;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.StockOpname;
import com.gbsystem.rpbackoffice.repository.StockOpnameRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananKeluarRepository;

@Service
public class StockOpnameService {
	@Autowired
	private StockOpnameRepository eRepo;
	
	@Autowired
	private PenyimpananMasukRepository eRepoMasuk;
	
	@Autowired
	private PenyimpananKeluarRepository eRepoKeluar;
	
	public StockOpname saveStockOpname(String sku_code,String artikel, String kategori, String nama_kategori
			,String type,String type_name, String nama_barang, double stock_opname) {
		
		StockOpname p = new StockOpname();
		
		Date date = new Date();
		Date date_before = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		
		Float kuantitas_masuk = eRepoMasuk.generateKuantitasMasuk(sku_code, date, date_before);
		Float kuantitas_keluar = eRepoKeluar.generateKuantitasKeluar(sku_code, date, date_before);
		
		Float stock = kuantitas_masuk - kuantitas_keluar;
		
		String keterangan = "";
		if (stock == stock_opname) {
			keterangan = "Sesuai";
		} else {
			keterangan = "Tidak Sesuai";
		}
		
		p.setTanggal_so(date);
		p.setSku_code(sku_code);
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setNama_kategori(nama_kategori);
		p.setType(type);
		p.setType_name(type_name);
		p.setNama_barang(nama_barang);
		p.setKuantitas_masuk(kuantitas_masuk);
		p.setKuantitas_keluar(kuantitas_keluar);
		p.setStock(stock);
		p.setStock_opname(stock_opname);
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
	
	public void update(Long id, String sku_code,String artikel, String kategori, String nama_kategori
			,String type,String type_name, String nama_barang, double stock_opname) {
		StockOpname p = new StockOpname();
    	p = eRepo.findById(id).get();
    	
    	Date date = new Date();
		Date date_before = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		
		Float kuantitas_masuk = eRepoMasuk.generateKuantitasMasuk(artikel, date, date_before);
		Float kuantitas_keluar = eRepoKeluar.generateKuantitasKeluar(artikel, date, date_before);
		
		Float stock = kuantitas_masuk - kuantitas_keluar;
		
		String keterangan = "";
		if (stock == stock_opname) {
			keterangan = "Sesuai";
		} else {
			keterangan = "Tidak Sesuai";
		}
    	
		p.setTanggal_so(date);
		p.setSku_code(sku_code);
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setNama_kategori(nama_kategori);
		p.setType(type);
		p.setType_name(type_name);
		p.setNama_barang(nama_barang);
		p.setKuantitas_masuk(kuantitas_masuk);
		p.setKuantitas_keluar(kuantitas_keluar);
		p.setStock(kuantitas_masuk - kuantitas_keluar);
		p.setStock_opname(stock_opname);
		p.setKeterangan(keterangan);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
}
