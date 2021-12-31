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

import com.gbsystem.rpbackoffice.entities.PenerimaanSupplier;
import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.PenerimaanSupplierRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;

@Service
public class PenerimaanSupplierService {

	@Autowired
	private PenerimaanSupplierRepository eRepo;
	
	@Autowired
	private StockOfficeRepository eStockRepo;
	
	@Autowired
	private PenyimpananMasukRepository ePenyimpananRepo;
	
	public PenerimaanSupplier savePenerimaanSupplier(String lokasi_penerimaan, String id_supplier, String nama_supplier,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		PenerimaanSupplier p = new PenerimaanSupplier();
		StockOffice d = new StockOffice();
		d = eStockRepo.findByArtikel(artikel).get(0);
		PenyimpananMasuk f = new PenyimpananMasuk();
		
		String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		String code_penerimaan = "PM-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
			d.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		p.setPenerimaan_code(code_penerimaan);
		p.setTanggal_penerimaan(new Date());
		p.setLokasi_penerimaan(lokasi_penerimaan);
		p.setId_supplier(id_supplier);
		p.setNama_supplier(nama_supplier);
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
		d.setKuantitas(d.getKuantitas() + kuantitas);
		d.setUkuran(ukuran);
		d.setHpp(hpp);
		d.setHarga_jual(harga_jual);
		d.setRowstatus(1);
		eStockRepo.save(d);
		// end region add stock
		
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
		f.setKeterangan("Barang Masuk Dari Supplier");
		f.setRowstatus(1);
		ePenyimpananRepo.save(f);
		// end region 
		return eRepo.save(p);
	}

	public List<PenerimaanSupplier> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PenerimaanSupplier> getAllPenerimaanSupplier(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deletePenerimaanSupplierById(Long id, String artikel)
    {
		PenerimaanSupplier p = new PenerimaanSupplier();
    	p = eRepo.findById(id).get();
    	
    	StockOffice d = new StockOffice();
		d = eStockRepo.findByArtikel(artikel).get(0);
		
    	p.setRowstatus(0);
    	d.setKuantitas(d.getKuantitas()-p.getKuantitas());
    	
    	eRepo.save(p);   
    	eStockRepo.save(d);
    }
	
	public void update(Long id, String penerimaan_code, Date tanggal_penerimaan, String lokasi_penerimaan, String id_supplier, String nama_supplier,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		PenerimaanSupplier p = new PenerimaanSupplier();
    	p = eRepo.findById(id).get();
    	StockOffice d = new StockOffice();
		d = eStockRepo.findByArtikel(artikel).get(0);
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
    	
		p.setTanggal_penerimaan(tanggal_penerimaan);
		p.setLokasi_penerimaan(lokasi_penerimaan);
		p.setId_supplier(id_supplier);
		p.setNama_supplier(nama_supplier);
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
		d.setKuantitas((d.getKuantitas()-p.getKuantitas()) + kuantitas);
		d.setUkuran(ukuran);
		d.setHpp(hpp);
		d.setHarga_jual(harga_jual);
		d.setRowstatus(1);
		eStockRepo.save(d);
		// end region add stock
		
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
		
    	
	}
	
}
