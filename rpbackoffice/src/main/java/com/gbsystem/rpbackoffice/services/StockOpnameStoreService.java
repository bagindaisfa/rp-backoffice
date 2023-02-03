package com.gbsystem.rpbackoffice.services;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.StockOpnameStore;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.StockOpnameStoreRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreKeluarRepository;

@Service
public class StockOpnameStoreService {
	@Autowired
	private StockOpnameStoreRepository eRepo;
	
	@Autowired
	private PenyimpananStoreMasukRepository eRepoMasuk;
	
	@Autowired
	private PenyimpananStoreKeluarRepository eRepoKeluar;
	
	@Autowired
	private StockStoreRepository eStockStoreRepo;
	
	public StockOpnameStore saveStockOpname(
			int id_store, String lokasi_store,String sku_code,
			String artikel, String kategori, String nama_kategori,
			int type,String type_name, String nama_barang, Double stock_opname) {
		
		StockOpnameStore p = new StockOpnameStore();

		String keterangan = "";
		Date date = new Date();
		Date date_before = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		
		Double kuantitas_masuk = eRepoMasuk.generateKuantitasMasuk(artikel, date, date_before) == null ? 0.0 : eRepoMasuk.generateKuantitasMasuk(artikel, date, date_before);
		Double kuantitas_keluar = eRepoKeluar.generateKuantitasKeluar(artikel, date, date_before) == null ? 0.0 : eRepoKeluar.generateKuantitasKeluar(artikel, date, date_before);
		Double stock = 0.0;
		
		if (kuantitas_masuk == 0.0) {
			StockStore stockStore = new StockStore();
			stockStore = eStockStoreRepo.findById_storeAndArtikel(id_store,artikel);
			if (stockStore != null) {
				stock = stockStore.getKuantitas() == null ? 0.0 : stockStore.getKuantitas();	
			} else {
				stock = 0.0;
			}
		} else {
			stock = kuantitas_masuk - kuantitas_keluar;
		}
		
		if (stock.equals(stock_opname)) {
			keterangan = "Sesuai";
		} else {
			keterangan = "Tidak Sesuai";
		}
		
		p.setTanggal_so(date);
		p.setId_store(id_store);
		p.setLokasi_store(lokasi_store);
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

	public List<StockOpnameStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<StockOpnameStore> getAllStockOpname(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deleteStockOpnameById(Long id)
    {
		StockOpnameStore p = new StockOpnameStore();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, int id_store, String lokasi_store,String sku_code,String artikel, String kategori, String nama_kategori
			,int type,String type_name, String nama_barang, Double stock_opname) {
		StockOpnameStore p = new StockOpnameStore();
    	p = eRepo.findById(id).get();
    	
    	Date date = new Date();
		Date date_before = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		
		Double kuantitas_masuk = eRepoMasuk.generateKuantitasMasuk(artikel, date, date_before) == null ? 0.0 : eRepoMasuk.generateKuantitasMasuk(artikel, date, date_before);
		Double kuantitas_keluar = eRepoKeluar.generateKuantitasKeluar(artikel, date, date_before) == null ? 0.0 : eRepoKeluar.generateKuantitasKeluar(artikel, date, date_before);
		Double stock = 0.0;
		
		if (kuantitas_masuk.equals(0.0)) {
			StockStore stockStore = new StockStore();
			stockStore = eStockStoreRepo.findById_storeAndArtikel(id_store,artikel);
			if (stockStore != null) {
				stock = stockStore.getKuantitas() == null ? 0.0 : stockStore.getKuantitas();	
			} else {
				stock = 0.0;
			}
		} else {
			stock = kuantitas_masuk - kuantitas_keluar;
		}
		
		String keterangan = "";
		if (stock.equals(stock_opname)) {
			keterangan = "Sesuai";
		} else {
			keterangan = "Tidak Sesuai";
		}
    	
		p.setTanggal_so(date);
		p.setId_store(id_store);
		p.setLokasi_store(lokasi_store);
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
    	eRepo.save(p);
	}

}
