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

import com.gbsystem.rpbackoffice.entities.PenjualanOffice;
import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.PenjualanOfficeRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananKeluarRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;

@Service
public class PenjualanOfficeService {
	@Autowired
	private PenjualanOfficeRepository eRepo;
	
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	
	@Autowired
	private PenyimpananKeluarRepository ePenyimpananRepo;
	
	public void savePenjualanOffice(int id_office, String lokasi_office, String artikel,
			String tipe, String kategori, String nama_barang, double kuantitas, String ukuran, MultipartFile foto_barang,
			String metode_pembayaran, double harga_satuan_barang, double ongkos_kirim, double pajak_biaya, double total) {
		
		String id_transaksi = "INV-" + new SimpleDateFormat("yyMM").format(new Date()) + "-O" + (eRepo.count() + 1);
		PenjualanOffice p = new PenjualanOffice();
		StockOffice prev = new StockOffice();
		prev = eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel);
		
		double prev_qty = prev.getKuantitas();
		
		StockOffice g = new StockOffice();
		g = eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel);
		g.setKuantitas(prev_qty - kuantitas);
		
		PenyimpananKeluar f = new PenyimpananKeluar();
		f.setPengiriman_code(id_transaksi);
		f.setTanggal_keluar(new Date());
		f.setId_office(id_office);
		f.setLokasi_office(lokasi_office);
		f.setLokasi_store("-");
		f.setArtikel(artikel);
		f.setKategori(kategori);
		f.setTipe(tipe);
		f.setNama_barang(nama_barang);
		f.setKuantitas(kuantitas);
		f.setUkuran(ukuran);
		f.setHpp(g.getHpp());
		f.setHarga_jual(g.getHarga_juala());
		f.setKeterangan("Penjualan Office");
		f.setRowstatus(1);
		ePenyimpananRepo.save(f);
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
		
		p.setTanggal_transaksi(new Date());
		p.setId_transaksi(id_transaksi);
		p.setId_office(id_office);
		p.setLokasi_office(lokasi_office);
		p.setArtikel(artikel);
		p.setTipe(tipe);
		p.setKategori(kategori);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setMetode_pembayaran(metode_pembayaran);
		p.setHarga_satuan_barang(harga_satuan_barang);
		p.setOngkos_kirim(ongkos_kirim);
		p.setPajak_biaya(pajak_biaya);
		p.setTotal(total);
		p.setRowstatus(1);
		eRepo.save(p);
	}
	public List<PenjualanOffice> getAllPenjualan(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<PenjualanOffice> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public void deletePenjualanById(Long id, int id_office, String artikel, String id_transaksi)
    {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	
    	StockOffice prev = new StockOffice();
		prev = eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel);
		
		double prev_qty = prev.getKuantitas();
		
		StockOffice e = new StockOffice();
		eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel);
		e.setKuantitas(prev_qty + p.getKuantitas());
		eStockOfficeRepo.save(e);
		
		PenyimpananKeluar f = new PenyimpananKeluar();
		f = ePenyimpananRepo.findByPengiriman_code(id_transaksi);
    	f.setRowstatus(0);
    	ePenyimpananRepo.save(f); 
    	
    	eRepo.save(p);    
    }
	
	public void update(Long id, String id_transaksi, int id_office, String lokasi_office, String artikel,
			String tipe, String kategori, String nama_barang, double kuantitas, String ukuran, MultipartFile foto_barang,
			String metode_pembayaran, double harga_satuan_barang, double ongkos_kirim, double pajak_biaya, double total) {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	StockOffice prev = new StockOffice();
		prev = eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel);
		
		double prev_qty = prev.getKuantitas();
		
		StockOffice g = new StockOffice();
		g = eStockOfficeRepo.findById_officeAndArtikel(id_office, artikel);
		g.setKuantitas((prev_qty + p.getKuantitas()) - kuantitas);
		
		PenyimpananKeluar f = new PenyimpananKeluar();
		f = ePenyimpananRepo.findByPengiriman_code(id_transaksi);
		f.setTanggal_keluar(new Date());
		f.setId_office(id_office);
		f.setLokasi_office(lokasi_office);
		f.setLokasi_store("-");
		f.setArtikel(artikel);
		f.setKategori(kategori);
		f.setTipe(tipe);
		f.setNama_barang(nama_barang);
		f.setKuantitas(kuantitas);
		f.setUkuran(ukuran);
		f.setHpp(g.getHpp());
		f.setHarga_jual(g.getHarga_juala());
		f.setKeterangan("Penjualan Office");
		f.setRowstatus(1);
		ePenyimpananRepo.save(f);
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
    	
		p.setId_office(id_office);
		p.setLokasi_office(lokasi_office);
		p.setArtikel(artikel);
		p.setTipe(tipe);
		p.setKategori(kategori);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setMetode_pembayaran(metode_pembayaran);
		p.setHarga_satuan_barang(harga_satuan_barang);
		p.setOngkos_kirim(ongkos_kirim);
		p.setPajak_biaya(pajak_biaya);
		p.setTotal(total);
		p.setRowstatus(1);
		eRepo.save(p);
	}
	
//	public void changePenjualanTanggal_transaksi(Long id ,Date tanggal_transaksi)
//    {
//		PenjualanOffice p = new PenjualanOffice();
//    	p = eRepo.findById(id).get();
//    	p.setTanggal_transaksi(tanggal_transaksi);
//    	eRepo.save(p);    
//    }
//	
//	public void changePenjualanId_transaksi(Long id ,String id_transaksi)
//    {
//		PenjualanOffice p = new PenjualanOffice();
//    	p = eRepo.findById(id).get();
//    	p.setId_transaksi(id_transaksi);
//    	eRepo.save(p);    
//    }
//	
//	public void changePenjualanId_office(Long id ,String id_office)
//    {
//		PenjualanOffice p = new PenjualanOffice();
//    	p = eRepo.findById(id).get();
//    	p.setId_office(id_office);
//    	eRepo.save(p);    
//    }
//	
//	public void changePenjualanLokasi_office(Long id ,String lokasi_office)
//    {
//		PenjualanOffice p = new PenjualanOffice();
//    	p = eRepo.findById(id).get();
//    	p.setLokasi_office(lokasi_office);
//    	eRepo.save(p);    
//    }
//	
//	public void changePenjualanKuantitas(Long id ,int kuantitas)
//    {
//		PenjualanOffice p = new PenjualanOffice();
//    	p = eRepo.findById(id).get();
//    	p.setKuantitas(kuantitas);
//    	eRepo.save(p);    
//    }
//	
//	public void changePenjualanTotal(Long id ,double total)
//    {
//		PenjualanOffice p = new PenjualanOffice();
//    	p = eRepo.findById(id).get();
//    	p.setTotal(total);
//    	eRepo.save(p);    
//    }
}
