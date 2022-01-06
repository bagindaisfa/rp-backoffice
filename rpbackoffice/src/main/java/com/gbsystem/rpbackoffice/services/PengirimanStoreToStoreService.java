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
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.repository.PengirimanStoreToStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreKeluarRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PengirimanStoreToStoreService {
	
	@Autowired
	private PengirimanStoreToStoreRepository eRepo;
	
	@Autowired
	private StockStoreRepository eStockRepo;
	
	@Autowired
	private PenyimpananStoreKeluarRepository ePenyimpananStoreKeluarRepo;
	
	@Autowired
	private PenyimpananStoreMasukRepository ePenyimpananStoreMasukRepo;
	
	public PengirimanStoreToStore savePengirimanStore(int id_store_asal, String lokasi_store_asal, int id_store_tujuan, String lokasi_store_tujuan,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		PengirimanStoreToStore p = new PengirimanStoreToStore();
		String code_pengiriman = "PSS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		String code_pengiriman_store = code_pengiriman + "S";
		
		PenyimpananStoreKeluar store_asal = new PenyimpananStoreKeluar();
		store_asal.setPengiriman_code(code_pengiriman_store);
		store_asal.setLokasi_office("-");
		store_asal.setTanggal_keluar(new Date());
		store_asal.setId_store(id_store_tujuan);
		store_asal.setLokasi_store(lokasi_store_tujuan);
		store_asal.setArtikel(artikel);
		store_asal.setKategori(kategori);
		store_asal.setTipe(tipe);
		store_asal.setNama_barang(nama_barang);
		store_asal.setKuantitas(kuantitas);
		store_asal.setUkuran(ukuran);
		store_asal.setHpp(hpp);
		store_asal.setHarga_jual(harga_jual);
		store_asal.setRowstatus(1);
		ePenyimpananStoreKeluarRepo.save(store_asal);

    	StockStore prevStoreAsal = new StockStore();
    	prevStoreAsal = eStockRepo.findById_storeAndArtikel(id_store_asal, artikel);
		
		double prev_qty_store_asal = prevStoreAsal.getKuantitas();
		
		StockStore d = new StockStore();
		d = eStockRepo.findById_storeAndArtikel(id_store_asal,artikel);
		d.setKuantitas(prev_qty_store_asal - kuantitas);
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
		
		PenyimpananStoreMasuk store_tujuan = new PenyimpananStoreMasuk();
		store_tujuan.setPenerimaan_code(code_pengiriman_store);
		store_tujuan.setLokasi_office("-");
		store_tujuan.setTanggal_masuk(new Date());
		store_tujuan.setId_store(id_store_tujuan);
		store_tujuan.setLokasi_store(lokasi_store_tujuan);
		store_tujuan.setArtikel(artikel);
		store_tujuan.setKategori(kategori);
		store_tujuan.setTipe(tipe);
		store_tujuan.setNama_barang(nama_barang);
		store_tujuan.setKuantitas(kuantitas);
		store_tujuan.setUkuran(ukuran);
		store_tujuan.setHpp(hpp);
		store_tujuan.setHarga_jual(harga_jual);
		store_tujuan.setRowstatus(1);
		ePenyimpananStoreMasukRepo.save(store_tujuan);

    	StockStore prevStoreTujuan = new StockStore();
    	prevStoreTujuan = eStockRepo.findById_storeAndArtikel(id_store_tujuan, artikel);
		
		double prev_qty_store_tujuan = prevStoreTujuan.getKuantitas();
		
		StockStore g = new StockStore();
		g = eStockRepo.findById_storeAndArtikel(id_store_tujuan,artikel);
		g.setKuantitas(prev_qty_store_tujuan + kuantitas);
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
		
		return eRepo.save(p);
	}

	public List<PengirimanStoreToStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PengirimanStoreToStore> getAllPengirimanStore(){

		return eRepo.findByRowstatus(1);
	}
	
	public PengirimanStoreToStore deletePengirimanStoreById(Long id, int id_store_asal, int id_store_tujuan, String artikel, String pengiriman_code)
    {
		PengirimanStoreToStore p = new PengirimanStoreToStore();
		p = eRepo.findById(id).get();
    	p.setRowstatus(0);

    	StockStore prevStoreAsal = new StockStore();
    	prevStoreAsal = eStockRepo.findById_storeAndArtikel(id_store_asal, artikel);
		
		double prev_qty_store_asal = prevStoreAsal.getKuantitas();
		
    	StockStore d = new StockStore();
		d = eStockRepo.findById_storeAndArtikel(id_store_asal,artikel);
		d.setKuantitas(prev_qty_store_asal + p.getKuantitas());
		eStockRepo.save(d);

    	StockStore prevStoreTujuan = new StockStore();
    	prevStoreTujuan = eStockRepo.findById_storeAndArtikel(id_store_tujuan, artikel);
		
		double prev_qty_store_tujuan = prevStoreTujuan.getKuantitas();
		
		StockStore g = new StockStore();
		g = eStockRepo.findById_storeAndArtikel(id_store_tujuan,artikel);
    	g.setKuantitas(prev_qty_store_tujuan - p.getKuantitas());
    	eStockRepo.save(g);
    	
    	PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
		h = ePenyimpananStoreMasukRepo.findByPenerimaan_code("PK-"+(pengiriman_code.substring(3))+"S");
		h.setRowstatus(0);
		ePenyimpananStoreMasukRepo.save(h);
    	
		PenyimpananStoreKeluar i = new PenyimpananStoreKeluar();
		i = ePenyimpananStoreKeluarRepo.findByPengiriman_code(pengiriman_code+"S");
		i.setRowstatus(0);
		ePenyimpananStoreKeluarRepo.save(i);
		
		return eRepo.save(p);  
    }
	
	public PengirimanStoreToStore update(Long id,String pengiriman_code, Date tanggal_pengiriman,int id_store_asal, String lokasi_store_asal, int id_store_tujuan, String lokasi_store_tujuan,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		PengirimanStoreToStore p = new PengirimanStoreToStore();
    	p = eRepo.findById(id).get();

    	StockStore prevStoreAsal = new StockStore();
    	prevStoreAsal = eStockRepo.findById_storeAndArtikel(id_store_asal, artikel);
		
		double prev_qty_store_asal = prevStoreAsal.getKuantitas();
		
    	StockStore d = new StockStore();
		d = eStockRepo.findById_storeAndArtikel(id_store_asal,artikel);
		d.setKuantitas((prev_qty_store_asal + p.getKuantitas()) - kuantitas);
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

    	StockStore prevStoreTujuan = new StockStore();
    	prevStoreTujuan = eStockRepo.findById_storeAndArtikel(id_store_tujuan, artikel);
		
		double prev_qty_store_tujuan = prevStoreTujuan.getKuantitas();
		
		StockStore g = new StockStore();
		g = eStockRepo.findById_storeAndArtikel(id_store_tujuan,artikel);
		g.setKuantitas((prev_qty_store_tujuan - p.getKuantitas()) + kuantitas);
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
		
		PenyimpananStoreKeluar store_asal = new PenyimpananStoreKeluar();		
		store_asal = ePenyimpananStoreKeluarRepo.findByPengiriman_code(pengiriman_code+"S");
		store_asal.setLokasi_office("-");
		store_asal.setTanggal_keluar(tanggal_pengiriman);
		store_asal.setId_store(id_store_tujuan);
		store_asal.setLokasi_store(lokasi_store_tujuan);
		store_asal.setArtikel(artikel);
		store_asal.setKategori(kategori);
		store_asal.setTipe(tipe);
		store_asal.setNama_barang(nama_barang);
		store_asal.setKuantitas(kuantitas);
		store_asal.setUkuran(ukuran);
		store_asal.setHpp(hpp);
		store_asal.setHarga_jual(harga_jual);
		store_asal.setRowstatus(1);
		ePenyimpananStoreKeluarRepo.save(store_asal);
		
		PenyimpananStoreMasuk store_tujuan = new PenyimpananStoreMasuk();
		store_tujuan = ePenyimpananStoreMasukRepo.findByPenerimaan_code("PK-"+(pengiriman_code.substring(3))+"S");
		store_tujuan.setLokasi_office("-");
		store_tujuan.setTanggal_masuk(tanggal_pengiriman);
		store_tujuan.setId_store(id_store_tujuan);
		store_tujuan.setLokasi_store(lokasi_store_tujuan);
		store_tujuan.setArtikel(artikel);
		store_tujuan.setKategori(kategori);
		store_tujuan.setTipe(tipe);
		store_tujuan.setNama_barang(nama_barang);
		store_tujuan.setKuantitas(kuantitas);
		store_tujuan.setUkuran(ukuran);
		store_tujuan.setHpp(hpp);
		store_tujuan.setHarga_jual(harga_jual);
		store_tujuan.setRowstatus(1);
		ePenyimpananStoreMasukRepo.save(store_tujuan);
		
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
		
		return eRepo.save(p);
		
	}

}
