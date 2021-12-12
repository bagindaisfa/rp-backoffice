package com.gbsystem.rpbackoffice.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import com.gbsystem.rpbackoffice.repository.PembelianRepository;
import com.gbsystem.rpbackoffice.entities.Pembelian;

@Service
public class PembelianService {
	@Autowired
	private PembelianRepository eRepo;
	
	public Pembelian savePembelian(MultipartFile image, String artikel, String kategori
			,String tipe, String nama_barang, double kuantitas, String ukuran, double hpp, double harga_jual ) {
		
		Pembelian p = new Pembelian();
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		p.setTanggal_transaksi(new Date());
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setTipe(tipe);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setTotal_hpp(kuantitas * harga_jual);
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<Pembelian> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<Pembelian> getAllPembelian(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deletePembelianById(Long id)
    {
		Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, MultipartFile image, String artikel, String kategori, String tipe, String nama_barang, double kuantitas, String ukuran, double hpp, double harga_jual ) {
		Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
    	String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		p.setTanggal_transaksi(new Date());
		p.setArtikel(artikel);
		p.setKategori(kategori);
		p.setTipe(tipe);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setTotal_hpp(kuantitas * harga_jual);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
	
	public void changePembelianTanggal_transaksi(Long id ,Date tanggal_transaksi)
    {
		Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
    	p.setTanggal_transaksi(tanggal_transaksi);
    	eRepo.save(p);    
    }
	public void changePembelianArtikel(Long id ,String artikel)
    {
		Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
    	p.setArtikel(artikel);
    	eRepo.save(p);    
    }
    public void changePembelianKategori(Long id , String kategori)
    {
    	Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
    	p.setKategori(kategori);
    	eRepo.save(p);
    }
    public void changePembelianTipe(Long id,String tipe)
    {
    	Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
    	p.setTipe(tipe);
    	eRepo.save(p);
    }
    public void changePembelianNamaBarang(Long id,String nama_barang)
    {
    	Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
	    p.setNama_barang(nama_barang);
		eRepo.save(p);
	}
    
    public void changePembelianKuantitas(Long id,int kuantitas)
    {
    	Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
		p.setKuantitas(kuantitas);
		eRepo.save(p);
	}
    
    public void changePembelianUkuran(Long id,String ukuran)
    {
    	Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
		p.setUkuran(ukuran);
		eRepo.save(p);
	}
    
    public void changePembelianHpp(Long id,double hpp)
    {
    	Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
		p.setHpp(hpp);
		eRepo.save(p);
	}
    
    public void changePembelianHargaJual(Long id,double harga_jual)
    {
    	Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
		p.setHarga_jual(harga_jual);
		eRepo.save(p);
	}
    
    public void changePembelianTotal_hpp(Long id)
    {
    	Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
		p.setTotal_hpp(p.getHpp()*p.getKuantitas());
		eRepo.save(p);
	}
	
}
