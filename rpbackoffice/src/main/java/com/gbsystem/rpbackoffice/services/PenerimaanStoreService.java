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

import com.gbsystem.rpbackoffice.entities.PenerimaanStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.PenerimaanStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;

@Service
public class PenerimaanStoreService {
	
	@Autowired
	private PenerimaanStoreRepository eRepo;
	
	@Autowired
	private StockOfficeRepository eStockRepo;
	
	@Autowired
	private PenyimpananMasukRepository ePenyimpananRepo;
	
	public PenerimaanStore savePenerimaanStore(int id_office, String lokasi_office, int id_store, String lokasi_store,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		PenerimaanStore p = new PenerimaanStore();
		
		StockOffice d = new StockOffice();
		d = eStockRepo.findById_officeAndArtikel(id_office,artikel).get(0);
		d.setKuantitas(d.getKuantitas() + kuantitas);
		eStockRepo.save(d);
		
		PenyimpananMasuk f = new PenyimpananMasuk();
		
		String code_penerimaan = "RT-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		p.setPenerimaan_code(code_penerimaan);
		p.setTanggal_penerimaan(new Date());
		p.setId_office(id_office);
		p.setLokasi_office(lokasi_office);
		p.setId_store(id_store);
		p.setLokasi_store(lokasi_store);
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
		f.setPenerimaan_code(code_penerimaan);
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

	public List<PenerimaanStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PenerimaanStore> getAllPenerimaanStore(){

		return eRepo.findByRowstatus(1);
	}
	
	public PenerimaanStore deletePenerimaanStoreById(Long id, int id_office, String artikel)
    {
		PenerimaanStore p = new PenerimaanStore();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	StockOffice d = new StockOffice();
		d = eStockRepo.findById_officeAndArtikel(id_office,artikel).get(0);
    	d.setKuantitas(d.getKuantitas()-p.getKuantitas());  
    	
    	eStockRepo.save(d);
    	return eRepo.save(p);    
    }
	
	public PenerimaanStore update(Long id,String penerimaan_code, Date tanggal_penerimaan, int id_office, String lokasi_office, int id_store, String lokasi_store,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		PenerimaanStore p = new PenerimaanStore();
    	p = eRepo.findById(id).get();
    	
    	StockOffice d = new StockOffice();
		d = eStockRepo.findById_officeAndArtikel(id_office,artikel).get(0);
		d.setKuantitas((d.getKuantitas()-p.getKuantitas()) + kuantitas);
		eStockRepo.save(d);
		
		PenyimpananMasuk f = new PenyimpananMasuk();
		f = ePenyimpananRepo.findByPenerimaan_code(penerimaan_code).get(0);
		
    	
    	String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
    	
		p.setPenerimaan_code(penerimaan_code);
		p.setTanggal_penerimaan(tanggal_penerimaan);
		p.setId_office(id_office);
		p.setLokasi_office(lokasi_office);
		p.setId_store(id_store);
		p.setLokasi_store(lokasi_store);
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
		f.setTanggal_masuk(tanggal_penerimaan);
		f.setArtikel(artikel);
		f.setKategori(kategori);
		f.setTipe(tipe);
		f.setNama_barang(nama_barang);
		f.setKuantitas(kuantitas);
		f.setUkuran(ukuran);
		f.setHpp(hpp);
		f.setHarga_jual(harga_jual);
		f.setKeterangan("Barang Masuk Dari Supplier");
		f.setRowstatus(1);
		ePenyimpananRepo.save(f);
		
		// end region 
		
		return eRepo.save(p);
	}

}
