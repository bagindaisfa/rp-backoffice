package com.gbsystem.rpbackoffice.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.StockOffice;

@Service
public class MasterProductService {
	@Autowired
	private MasterProductRepository eRepo;
	
	@Autowired
	private StockOfficeRepository eStockRepo;
	
	public MasterProduct saveMasterProduct( MultipartFile image,
			String artikel_product, String nama_product, 
			String type, String type_name, String kategori, 
			String nama_kategori, String artikel_frame, String artikel_lens,
			double kuantitas,double hpp,double harga_jual, String remarks
			) {
		
		MasterProduct p = new MasterProduct();
		StockOffice q = new StockOffice();
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
			q.setFoto_barang(Base64.getEncoder().encodeToString(image.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		p.setArtikel_product(artikel_product);
		p.setNama_product(nama_product);
		p.setType(type);
		p.setType_name(type_name);
		p.setKategori(kategori);
		p.setNama_kategori(nama_kategori);
		p.setArtikel_frame(artikel_frame);
		p.setArtikel_lens(artikel_lens);
		p.setKuantitas(kuantitas);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRemarks(remarks);
		p.setRowstatus(1);
		
		q.setId_office(1);
		q.setLokasi_office("Kantor Pusat");
		q.setArtikel(artikel_product);
		q.setKategori(kategori);
		q.setTipe(type);
		q.setNama_barang(nama_product);
		q.setKuantitas(kuantitas);
		q.setUkuran("");
		
		q.setHpp(hpp);
		q.setHarga_jual(harga_jual);
		q.setRowstatus(1);
		eStockRepo.save(q);
		
		return eRepo.save(p);
	}

	public List<MasterProduct> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<MasterProduct> getAllMasterProduct(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<MasterProduct> getMasterProduct(List<String> artikel_product){

		return eRepo.findByMasterProductArtikel_product(artikel_product);
	}
	
	public void deleteMasterProductById(Long id)
    {
		MasterProduct p = new MasterProduct();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(
			Long id, MultipartFile image, String artikel_product,
			String nama_product, String type, String type_name,
			String kategori, String artikel_frame, String artikel_lens,
			String nama_kategori, double kuantitas,double hpp,
			double harga_jual, String remarks ) {
		MasterProduct p = new MasterProduct();
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
    	p.setArtikel_product(artikel_product);
		p.setNama_product(nama_product);
		p.setType(type);
		p.setType_name(type_name);
		p.setKategori(kategori);
		p.setNama_kategori(nama_kategori);
		p.setArtikel_frame(artikel_frame);
		p.setArtikel_lens(artikel_lens);
		
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRemarks(remarks);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
}
