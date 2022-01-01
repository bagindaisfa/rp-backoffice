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

import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStore;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.PengirimanStoreToStoreRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PengirimanStoreToStoreService {
	
	@Autowired
	private PengirimanStoreToStoreRepository eRepo;
	
	@Autowired
	private StockStoreRepository eStockRepo;
	
	public PengirimanStoreToStore savePengirimanStore(int id_store_asal, String lokasi_store_asal, int id_store_tujuan, String lokasi_store_tujuan,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		PengirimanStoreToStore p = new PengirimanStoreToStore();
		
		StockStore d = new StockStore();
		d = eStockRepo.findById_storeAndArtikel(id_store_asal,artikel).get(0);
		d.setKuantitas(d.getKuantitas() - kuantitas);
		
		StockStore g = new StockStore();
		g = eStockRepo.findById_storeAndArtikel(id_store_tujuan,artikel).get(0);
		g.setKuantitas(g.getKuantitas() + kuantitas);
		
		String code_pengiriman = "PS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
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
		p.setId_store_asal(id_store_asal);
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
		
		// region menambah stock store tujuan
		g.setId_store(id_store_tujuan);
		g.setLokasi_store(lokasi_store_tujuan);
		g.setArtikel(artikel);
		g.setKategori(kategori);
		g.setTipe(tipe);
		g.setNama_barang(nama_barang);
		g.setUkuran(ukuran);
		g.setHpp(hpp);
		g.setHarga_jual(harga_jual);
		g.setRowstatus(1);
		eStockRepo.save(g);
		// end region add stock store
		
		// region mengurangi stock store asal
		d.setId_store(id_store_asal);
		d.setLokasi_store(lokasi_store_asal);
		d.setArtikel(artikel);
		d.setKategori(kategori);
		d.setTipe(tipe);
		d.setNama_barang(nama_barang);
		d.setUkuran(ukuran);
		d.setHpp(hpp);
		d.setHarga_jual(harga_jual);
		d.setRowstatus(1);
		eStockRepo.save(d);
		// end region add stock store
		return eRepo.save(p);
	}

	public List<PengirimanStoreToStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PengirimanStoreToStore> getAllPengirimanStore(){

		return eRepo.findByRowstatus(1);
	}
	
	public PengirimanStoreToStore deletePengirimanStoreById(Long id, int id_store_asal, int id_store_tujuan, String artikel)
    {
		PengirimanStoreToStore p = new PengirimanStoreToStore();
		p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	
    	StockStore d = new StockStore();
		d = eStockRepo.findById_storeAndArtikel(id_store_asal,artikel).get(0);
		d.setKuantitas(d.getKuantitas() + p.getKuantitas());
		
		StockStore g = new StockStore();
		g = eStockRepo.findById_storeAndArtikel(id_store_tujuan,artikel).get(0);
    	g.setKuantitas(g.getKuantitas() - p.getKuantitas());
    	
    	eStockRepo.save(d);
    	eStockRepo.save(g);
    	return eRepo.save(p);  
    }
	
	public PengirimanStoreToStore update(Long id,String pengiriman_code, Date tanggal_pengiriman,int id_store_asal, String lokasi_store_asal, int id_store_tujuan, String lokasi_store_tujuan,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		PengirimanStoreToStore p = new PengirimanStoreToStore();
    	p = eRepo.findById(id).get();
    	
    	
    	StockStore d = new StockStore();
		d = eStockRepo.findById_storeAndArtikel(id_store_asal,artikel).get(0);
		d.setKuantitas((d.getKuantitas()-p.getKuantitas()) - kuantitas);
		
		StockStore g = new StockStore();
		g = eStockRepo.findById_storeAndArtikel(id_store_tujuan,artikel).get(0);
		g.setKuantitas((g.getKuantitas()-p.getKuantitas()) + kuantitas);
		
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
		
		// region menambah stock store tujuan
		g.setId_store(id_store_tujuan);
		g.setLokasi_store(lokasi_store_tujuan);
		g.setArtikel(artikel);
		g.setKategori(kategori);
		g.setTipe(tipe);
		g.setNama_barang(nama_barang);
		
		g.setUkuran(ukuran);
		g.setHpp(hpp);
		g.setHarga_jual(harga_jual);
		g.setRowstatus(1);
		eStockRepo.save(g);
		// end region add stock store
		
		// region mengurangi stock store asal
		d.setId_store(id_store_asal);
		d.setLokasi_store(lokasi_store_asal);
		d.setArtikel(artikel);
		d.setKategori(kategori);
		d.setTipe(tipe);
		d.setNama_barang(nama_barang);
		
		d.setUkuran(ukuran);
		d.setHpp(hpp);
		d.setHarga_jual(harga_jual);
		d.setRowstatus(1);
		eStockRepo.save(d);
		// end region add stock store
		
		return eRepo.save(p);
		
	}

}
