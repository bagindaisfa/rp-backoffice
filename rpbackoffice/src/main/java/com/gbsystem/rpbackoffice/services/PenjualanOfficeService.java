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
import com.gbsystem.rpbackoffice.repository.PenjualanOfficeRepository;

@Service
public class PenjualanOfficeService {
	@Autowired
	private PenjualanOfficeRepository eRepo;
	
	
	public void savePenjualanOffice(String id_office, String lokasi_office, String artikel,
			String tipe, String kategori, String nama_barang, double kuantitas, String ukuran, MultipartFile foto_barang,
			String metode_pembayaran, double harga_satuan_barang, double ongkos_kirim, double pajak_biaya, double total) {
		
		PenjualanOffice p = new PenjualanOffice();
		
		String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		String id_transaksi = "INV-" + new SimpleDateFormat("yyMM").format(new Date()) + "-O" + (eRepo.count() + 1);
		
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
	
	public void deletePenjualanById(Long id)
    {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, String id_office, String lokasi_office, String artikel,
			String tipe, String kategori, String nama_barang, double kuantitas, String ukuran, MultipartFile foto_barang,
			String metode_pembayaran, double harga_satuan_barang, double ongkos_kirim, double pajak_biaya, double total) {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	
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
