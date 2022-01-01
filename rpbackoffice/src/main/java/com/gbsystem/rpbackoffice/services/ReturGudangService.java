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

import com.gbsystem.rpbackoffice.entities.ReturGudang;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.ReturGudangRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class ReturGudangService {
	
	@Autowired
	private ReturGudangRepository eRepo;
	
	@Autowired
	private StockStoreRepository eStockRepo;
	
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	
	@Autowired
	private PenyimpananMasukRepository ePenyimpananRepo;
	
	public ReturGudang saveReturGudang(int id_store_asal,String lokasi_store_asal,int id_office_tujuan, String lokasi_office_tujuan,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		ReturGudang p = new ReturGudang();
		
		StockStore d = new StockStore();
		d = eStockRepo.findById_storeAndArtikel(id_store_asal,artikel).get(0);
		d.setKuantitas(d.getKuantitas() - kuantitas);
		eStockRepo.save(d);
		
		StockOffice g = new StockOffice();
		g = eStockOfficeRepo.findById_officeAndArtikel(id_office_tujuan, artikel).get(0);
		g.setKuantitas(g.getKuantitas() + kuantitas);
		eStockOfficeRepo.save(g);
		
		PenyimpananMasuk f = new PenyimpananMasuk();
		
		String code_retur = "RT-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		p.setPengiriman_code(code_retur);
		p.setTanggal_retur(new Date());
		p.setId_store_asal(id_store_asal);
		p.setLokasi_store_asal(lokasi_store_asal);
		p.setId_office_tujuan(id_office_tujuan);
		p.setLokasi_office_tujuan(lokasi_office_tujuan);
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setTipe(tipe);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRowstatus(1);
		
		// region penyimpanan barang masuk
		f.setPenerimaan_code(code_retur);
		f.setTanggal_masuk(new Date());
		f.setArtikel(artikel);
		f.setKategori(kategori);
		f.setTipe(tipe);
		f.setNama_barang(nama_barang);
		f.setKuantitas(kuantitas);
		f.setUkuran(ukuran);
		f.setHpp(hpp);
		f.setHarga_jual(harga_jual);
		f.setKeterangan("Pengembalian Barang Dari Store");
		f.setRowstatus(1);
		ePenyimpananRepo.save(f);
		// end region
		return eRepo.save(p);
	}

	public List<ReturGudang> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<ReturGudang> getAllReturGudang(){

		return eRepo.findByRowstatus(1);
	}
	
	public ReturGudang deleteReturGudangById(Long id, int id_office,int id_store, String artikel, String pengiriman_code)
    {
		ReturGudang p = new ReturGudang();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	
    	StockStore d = new StockStore();
		d = eStockRepo.findById_storeAndArtikel(id_store, artikel).get(0);
		d.setKuantitas(d.getKuantitas() + p.getKuantitas());
		eStockRepo.save(d);
		
		StockOffice e = new StockOffice();
		eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel).get(0);
		e.setKuantitas(e.getKuantitas() - p.getKuantitas());
		eStockOfficeRepo.save(e);
		
		PenyimpananMasuk f = new PenyimpananMasuk();
		f = ePenyimpananRepo.findByPenerimaan_code(pengiriman_code).get(0);
    	f.setRowstatus(0);
    	ePenyimpananRepo.save(f);
    	
    	return eRepo.save(p);    
    }
	
	public ReturGudang update(Long id,String pengiriman_code, Date tanggal_retur, int id_store_asal,String lokasi_store_asal,int id_office_tujuan,
			String lokasi_office_tujuan,String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		ReturGudang p = new ReturGudang();
    	p = eRepo.findById(id).get();
    	
    	StockStore d = new StockStore();
		d = eStockRepo.findById_storeAndArtikel(id_store_asal,artikel).get(0);
		d.setKuantitas((d.getKuantitas()-p.getKuantitas()) - kuantitas);
		eStockRepo.save(d);
    	
		StockOffice g = new StockOffice();
		g = eStockOfficeRepo.findById_officeAndArtikel(id_office_tujuan, artikel).get(0);
		g.setKuantitas((g.getKuantitas()-p.getKuantitas()) + kuantitas);
		eStockOfficeRepo.save(g);
		
		PenyimpananMasuk f = new PenyimpananMasuk();
		f = ePenyimpananRepo.findByPenerimaan_code(pengiriman_code).get(0);
		
    	String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		p.setPengiriman_code(pengiriman_code);
		p.setTanggal_retur(tanggal_retur);
		p.setId_store_asal(id_store_asal);
		p.setLokasi_store_asal(lokasi_store_asal);
		p.setId_office_tujuan(id_office_tujuan);
		p.setLokasi_office_tujuan(lokasi_office_tujuan);
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setTipe(tipe);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRowstatus(1);
		
		// region penyimpanan barang masuk
		f.setPenerimaan_code(pengiriman_code);
		f.setTanggal_masuk(new Date());
		f.setArtikel(artikel);
		f.setKategori(kategori);
		f.setTipe(tipe);
		f.setNama_barang(nama_barang);
		f.setKuantitas(kuantitas);
		f.setUkuran(ukuran);
		f.setHpp(hpp);
		f.setHarga_jual(harga_jual);
		f.setKeterangan("Pengembalian Barang Dari Store");
		f.setRowstatus(1);
		ePenyimpananRepo.save(f);
		
		return eRepo.save(p);
	}

}
