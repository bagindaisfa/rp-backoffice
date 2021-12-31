package com.gbsystem.rpbackoffice.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.ReturGudang;
import com.gbsystem.rpbackoffice.repository.ReturGudangRepository;

@Service
public class ReturGudangService {
	
	@Autowired
	private ReturGudangRepository eRepo;
	
	public ReturGudang saveReturGudang(String lokasi_store_asal, String nama_gudang_tujuan,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		ReturGudang p = new ReturGudang();
		
		String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		p.setTanggal_retur(new Date());
		p.setLokasi_store_asal(lokasi_store_asal);
		p.setNama_gudang_tujuan(nama_gudang_tujuan);
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

	public List<ReturGudang> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<ReturGudang> getAllReturGudang(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deleteReturGudangById(Long id)
    {
		ReturGudang p = new ReturGudang();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, Date tanggal_retur, String lokasi_store_asal, String nama_gudang_tujuan,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		ReturGudang p = new ReturGudang();
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
    	
		p.setTanggal_retur(tanggal_retur);
		p.setLokasi_store_asal(lokasi_store_asal);
		p.setNama_gudang_tujuan(nama_gudang_tujuan);
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
