package com.gbsystem.rpbackoffice.services;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.entities.StockOpname;
import com.gbsystem.rpbackoffice.repository.StockOpnameRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananKeluarRepository;

@Service
public class StockOpnameService {
	@Autowired
	private StockOpnameRepository eRepo;
	
	@Autowired
	private PenyimpananMasukRepository eRepoMasuk;
	
	@Autowired
	private PenyimpananKeluarRepository eRepoKeluar;
	
	@Autowired
	private StockOfficeRepository eStockRepo;
	
	public StockOpname saveStockOpname(String sku_code,String artikel, String kategori, String nama_kategori
			,int type,String type_name, String nama_barang, Double stock_opname) {
		
		StockOpname p = new StockOpname();
		
		Date date = new Date();
		Date date_before = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		
		Double kuantitas_masuk = eRepoMasuk.generateKuantitasMasuk(artikel, date, date_before) == null ? 0.0 : eRepoMasuk.generateKuantitasMasuk(artikel, date, date_before);
		Double kuantitas_keluar = eRepoKeluar.generateKuantitasKeluar(artikel, date, date_before) == null ? 0.0 : eRepoKeluar.generateKuantitasKeluar(artikel, date, date_before);
		Double stock = 0.0;
		if (kuantitas_masuk.equals(0.0)) {
			StockOffice d = new StockOffice();
			d = eStockRepo.findById_officeAndArtikel(1,artikel);
			stock = d.getKuantitas() == null ? 0.0 : d.getKuantitas();
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
			,int type,String type_name, String nama_barang, Double stock_opname) {
		StockOpname p = new StockOpname();
    	p = eRepo.findById(id).get();
    	
    	Date date = new Date();
		Date date_before = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		
		Double kuantitas_masuk = eRepoMasuk.generateKuantitasMasuk(artikel, date, date_before) == null ? 0.0 : eRepoMasuk.generateKuantitasMasuk(artikel, date, date_before);
		Double kuantitas_keluar = eRepoKeluar.generateKuantitasKeluar(artikel, date, date_before) == null ? 0.0 : eRepoKeluar.generateKuantitasKeluar(artikel, date, date_before);
		Double stock = 0.0;
		if (kuantitas_masuk.equals(0.0)) {
			StockOffice d = new StockOffice();
			d = eStockRepo.findById_officeAndArtikel(1,artikel);
			stock = d.getKuantitas() == null ? 0.0 : d.getKuantitas();
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
