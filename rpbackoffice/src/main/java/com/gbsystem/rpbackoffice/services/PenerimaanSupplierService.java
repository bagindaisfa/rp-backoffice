package com.gbsystem.rpbackoffice.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.PenerimaanSupplier;
import com.gbsystem.rpbackoffice.repository.PenerimaanSupplierRepository;

@Service
public class PenerimaanSupplierService {

	@Autowired
	private PenerimaanSupplierRepository eRepo;
	
	public PenerimaanSupplier savePenerimaanSupplier(String lokasi_penerimaan, String id_supplier, String nama_supplier,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		
		PenerimaanSupplier p = new PenerimaanSupplier();
		
		String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
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
		return eRepo.save(p);
	}

	public List<PenerimaanSupplier> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PenerimaanSupplier> getAllPenerimaanSupplier(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deletePenerimaanSupplierById(Long id)
    {
		PenerimaanSupplier p = new PenerimaanSupplier();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, Date tanggal_penerimaan, String lokasi_penerimaan, String id_supplier, String nama_supplier,
			String artikel, String kategori,String tipe, String nama_barang, double kuantitas, String ukuran, 
			MultipartFile foto_barang, double hpp, double harga_jual) {
		PenerimaanSupplier p = new PenerimaanSupplier();
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
	}
	
}
