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

import com.gbsystem.rpbackoffice.entities.PengirimanStore;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;
import com.gbsystem.rpbackoffice.repository.PengirimanStoreRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananKeluarRepository;

@Service
public class PengirimanStoreService {
	
	@Autowired
	private PengirimanStoreRepository eRepo;
	
	@Autowired
	private StockOfficeRepository eStockRepo;
	
	@Autowired
	private PenyimpananKeluarRepository ePenyimpananRepo;
	
	public PengirimanStore savePengirimanStore(String lokasi_store_asal, String id_store_tujuan, String lokasi_store_tujuan,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		PengirimanStore p = new PengirimanStore();
		StockOffice d = new StockOffice();
		d = eStockRepo.findByArtikel(artikel).get(0);
		PenyimpananKeluar f = new PenyimpananKeluar();
		
		String code_pengiriman = "PK-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		p.setPengiriman_code(code_pengiriman);
		p.setTanggal_pengiriman(new Date());
		p.setLokasi_store_asal(lokasi_store_asal);
		p.setId_store_tujuan(id_store_tujuan);
		p.setLokasi_store_tujuan(lokasi_store_tujuan);
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setTipe(tipe);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRowstatus(1);
		
		// region add stock
		d.setKategori(kategori);
		d.setTipe(tipe);
		d.setNama_barang(nama_barang);
		d.setKuantitas(d.getKuantitas() - kuantitas);
		d.setUkuran(ukuran);
		d.setHpp(hpp);
		d.setHarga_jual(harga_jual);
		d.setRowstatus(1);
		eStockRepo.save(d);
		// end region add stock
		
		// region penyimpanan barang keluar
		f.setPengiriman_code(code_pengiriman);
		f.setTanggal_keluar(new Date());
		f.setId_store(id_store_tujuan);
		f.setLokasi_store(lokasi_store_tujuan);
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
		// end region
		
		return eRepo.save(p);
	}

	public List<PengirimanStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PengirimanStore> getAllPengirimanStore(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deletePengirimanStoreById(Long id, String artikel)
    {
		PengirimanStore p = new PengirimanStore();
		p = eRepo.findById(id).get();
    	
    	StockOffice d = new StockOffice();
		d = eStockRepo.findByArtikel(artikel).get(0);
		
    	p.setRowstatus(0);
    	d.setKuantitas(d.getKuantitas() + p.getKuantitas());
    	
    	eRepo.save(p);   
    	eStockRepo.save(d);  
    }
	
	public void update(Long id, String pengiriman_code, Date tanggal_pengiriman, String lokasi_store_asal, String id_store_tujuan, String lokasi_store_tujuan,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		PengirimanStore p = new PengirimanStore();
    	p = eRepo.findById(id).get();
    	StockOffice d = new StockOffice();
		d = eStockRepo.findByArtikel(artikel).get(0);
		PenyimpananKeluar f = new PenyimpananKeluar();
		f = ePenyimpananRepo.findByPengiriman_code(pengiriman_code).get(0);
		
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
		p.setLokasi_store_asal(lokasi_store_asal);
		p.setId_store_tujuan(id_store_tujuan);
		p.setLokasi_store_tujuan(lokasi_store_tujuan);
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setTipe(tipe);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRowstatus(1);
		eRepo.save(p);
		
		// region add stock
		d.setKategori(kategori);
		d.setTipe(tipe);
		d.setNama_barang(nama_barang);
		d.setKuantitas((d.getKuantitas()-p.getKuantitas()) - kuantitas);
		d.setUkuran(ukuran);
		d.setHpp(hpp);
		d.setHarga_jual(harga_jual);
		d.setRowstatus(1);
		eStockRepo.save(d);
		// end region add stock
		
		// region penyimpanan barang keluar
		f.setTanggal_keluar(tanggal_pengiriman);
		f.setId_store(id_store_tujuan);
		f.setLokasi_store(lokasi_store_tujuan);
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
	}

}
