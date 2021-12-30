package com.gbsystem.rpbackoffice.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.PengirimanGudang;
import com.gbsystem.rpbackoffice.repository.PengirimanGudangRepository;

@Service
public class PengirimanGudangService {
	
	@Autowired
	private PengirimanGudangRepository eRepo;
	
	public PengirimanGudang savePengirimanGudang(String nama_gudang, String lokasi_store,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		PengirimanGudang p = new PengirimanGudang();
		
		String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		p.setTanggal_pengiriman(new Date());
		p.setNama_gudang(nama_gudang);
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
		return eRepo.save(p);
	}

	public List<PengirimanGudang> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PengirimanGudang> getAllPengirimanGudang(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deletePengirimanGudangById(Long id)
    {
		PengirimanGudang p = new PengirimanGudang();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, Date tanggal_pengiriman, String nama_gudang, String lokasi_store,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		PengirimanGudang p = new PengirimanGudang();
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
    	
		p.setTanggal_pengiriman(tanggal_pengiriman);
		p.setNama_gudang(nama_gudang);
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
		eRepo.save(p);
	}

}
