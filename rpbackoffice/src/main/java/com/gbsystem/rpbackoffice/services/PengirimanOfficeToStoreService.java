package com.gbsystem.rpbackoffice.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.PengirimanOfiiceToStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.PengirimanOfficeToStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananKeluarRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PengirimanOfficeToStoreService {
	
	@Autowired
	private PengirimanOfficeToStoreRepository eRepo;

	@Autowired
	private StockStoreRepository eStockRepo;
	
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	
	@Autowired
	private PenyimpananKeluarRepository ePenyimpananRepo;
	
	@Autowired
	private PenyimpananStoreMasukRepository ePenyimpananStoreRepo;
	
	public PengirimanOfiiceToStore savePengirimanOffice(int id_office, String office_name, int id_store,String store_name, String artikel, String kategori, 
			String tipe, String nama_barang, double kuantitas,String ukuran, MultipartFile foto_barang, double hpp,double harga_jual) {
		
		String code_pengiriman = "POS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		String code_penerimaan_store = code_pengiriman + "S";
		String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		
		PengirimanOfiiceToStore p = new PengirimanOfiiceToStore();
		
		StockStore d = new StockStore();
		
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
			d.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		d.setId_store(id_store);
		d.setLokasi_store(store_name);
		d.setArtikel(artikel);
		d.setKategori(kategori);
		d.setTipe(tipe);
		d.setNama_barang(nama_barang);
		d.setKuantitas(kuantitas);
		d.setUkuran(ukuran);
		d.setHpp(hpp);
		d.setHarga_jual(harga_jual);
		d.setRowstatus(1);
		eStockRepo.save(d);
		
		StockOffice prev = new StockOffice();
		prev = eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel).get(0);
		
		double prev_qty = prev.getKuantitas();
		
		StockOffice g = new StockOffice();
		g = eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel).get(0);
		g.setKuantitas(prev_qty - kuantitas);
		eStockOfficeRepo.save(g);
		
		PenyimpananKeluar f = new PenyimpananKeluar();
		f.setPengiriman_code(code_pengiriman);
		f.setTanggal_keluar(new Date());
		f.setId_store(id_store);
		f.setLokasi_store(store_name);
		f.setArtikel(artikel);
		f.setKategori(kategori);
		f.setTipe(tipe);
		f.setNama_barang(nama_barang);
		f.setKuantitas(kuantitas);
		f.setUkuran(ukuran);
		f.setHpp(hpp);
		f.setHarga_jual(harga_jual);
		f.setKeterangan("Barang Dikirim Ke Store");
		f.setRowstatus(1);
		ePenyimpananRepo.save(f);
		
		PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
		h.setId_office(id_office);
		h.setLokasi_office(office_name);
		h.setPenerimaan_code(code_penerimaan_store);
		h.setTanggal_masuk(new Date());
		h.setId_store(id_store);
		h.setLokasi_store(store_name);
		h.setArtikel(artikel);
		h.setKategori(kategori);
		h.setTipe(tipe);
		h.setNama_barang(nama_barang);
		h.setKuantitas(kuantitas);
		h.setUkuran(ukuran);
		h.setHpp(hpp);
		h.setHarga_jual(harga_jual);
		h.setRowstatus(1);
		ePenyimpananStoreRepo.save(h);
		
		p.setPengiriman_code(code_pengiriman);
		p.setTanggal_pengiriman(new Date());
		p.setId_office(id_office);
		p.setOffice_name(office_name);
		p.setId_store(id_store);
		p.setStore_name(store_name);
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setTipe(tipe);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRowstatus(1);
		
		return eRepo.save(p);
	}

	public List<PengirimanOfiiceToStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PengirimanOfiiceToStore> getAllPengirimanGudang(){

		return eRepo.findByRowstatus(1);
	}
	
	public PengirimanOfiiceToStore deletePengirimanGudangById(Long id, int id_office,int id_store, String artikel, String pengiriman_code)
    {
		PengirimanOfiiceToStore p = new PengirimanOfiiceToStore();
		p.setRowstatus(0);
		
		StockStore prevStore = new StockStore();
		prevStore = eStockRepo.findById_storeAndArtikel(id_store, artikel).get(0);
		
		double prev_qty_store = prevStore.getKuantitas();
		
		StockStore d = new StockStore();
		d = eStockRepo.findById_storeAndArtikel(id_store, artikel).get(0);
		d.setKuantitas(prev_qty_store - p.getKuantitas());
		eStockRepo.save(d);
		
		StockOffice prev = new StockOffice();
		prev = eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel).get(0);
		
		double prev_qty = prev.getKuantitas();
		
		StockOffice e = new StockOffice();
		eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel).get(0);
		e.setKuantitas(prev_qty + p.getKuantitas());
		eStockOfficeRepo.save(e);
		
		PenyimpananKeluar f = new PenyimpananKeluar();
		f = ePenyimpananRepo.findByPengiriman_code(pengiriman_code).get(0);
    	f.setRowstatus(0);
    	ePenyimpananRepo.save(f); 
    	
    	PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
		h = ePenyimpananStoreRepo.findByPenerimaan_code(pengiriman_code+"S").get(0);
		h.setRowstatus(0);
		ePenyimpananStoreRepo.save(h);
		
    	return eRepo.save(p);
    }
	
	public PengirimanOfiiceToStore update(Long id, String pengiriman_code, Date tanggal_pengiriman, int id_office, String office_name, int id_store, 
			String store_name, String artikel, String kategori, 
			String tipe, String nama_barang, double kuantitas,
			String ukuran, MultipartFile foto_barang, double hpp,
			double harga_jual) {
		
		PengirimanOfiiceToStore p = new PengirimanOfiiceToStore();
    	p = eRepo.findById(id).get();
    	
    	StockStore prevStore = new StockStore();
		prevStore = eStockRepo.findById_storeAndArtikel(id_store, artikel).get(0);
		
		double prev_qty_store = prevStore.getKuantitas();
		
    	StockStore d = new StockStore();
		d = eStockRepo.findById_storeAndArtikel(id_store,artikel).get(0);
		d.setKuantitas((prev_qty_store-p.getKuantitas()) + kuantitas);
		eStockRepo.save(d);
		
		PenyimpananKeluar f = new PenyimpananKeluar();
		f = ePenyimpananRepo.findByPengiriman_code(pengiriman_code).get(0);
		f.setTanggal_keluar(tanggal_pengiriman);
		f.setId_store(id_store);
		f.setLokasi_store(store_name);
		f.setArtikel(artikel);
		f.setKategori(kategori);
		f.setTipe(tipe);
		f.setNama_barang(nama_barang);
		f.setKuantitas(kuantitas);
		f.setUkuran(ukuran);
		f.setHpp(hpp);
		f.setHarga_jual(harga_jual);
		f.setKeterangan("Barang Dikirim Ke Store");
		f.setRowstatus(1);
		ePenyimpananRepo.save(f);
		
		PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
		h = ePenyimpananStoreRepo.findByPenerimaan_code(pengiriman_code+"S").get(0);
		h.setId_office(id_office);
		h.setLokasi_office(office_name);
		h.setTanggal_masuk(tanggal_pengiriman);
		h.setId_store(id_store);
		h.setLokasi_store(store_name);
		h.setArtikel(artikel);
		h.setKategori(kategori);
		h.setTipe(tipe);
		h.setNama_barang(nama_barang);
		h.setKuantitas(kuantitas);
		h.setUkuran(ukuran);
		h.setHpp(hpp);
		h.setHarga_jual(harga_jual);
		h.setRowstatus(1);
		ePenyimpananStoreRepo.save(h);
		
		StockOffice prev = new StockOffice();
		prev = eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel).get(0);
		
		double prev_qty = prev.getKuantitas();
		
		StockOffice g = new StockOffice();
		g = eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel).get(0);
		g.setKuantitas((prev_qty + p.getKuantitas()) - kuantitas);
		eStockOfficeRepo.save(g);
		
    	String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
    	
		p.setTanggal_pengiriman(tanggal_pengiriman);
		p.setId_office(id_office);
		p.setOffice_name(office_name);
		p.setId_store(id_store);
		p.setStore_name(store_name);
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setTipe(tipe);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRowstatus(1);
		
		return eRepo.save(p);
	}

}
