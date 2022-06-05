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

import com.gbsystem.rpbackoffice.repository.MasterOfficeRepository;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.MasterStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenerimaanSupplierRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;
import com.gbsystem.rpbackoffice.entities.MasterOffice;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.MasterStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.entities.StockStore;

@Service
public class MasterProductService {
	@Autowired
	private MasterProductRepository eRepo;
	
	@Autowired
	private MasterProductRepository eSecRepo;
	
	@Autowired
	private StockOfficeRepository eStockRepo;
	
	@Autowired
	private StockStoreRepository eStockStoreRepo;
	
	@Autowired
	private MasterStoreRepository eMasterStoreRepo;
	
	@Autowired
	private MasterOfficeRepository eMasterOfficeRepo;
	
	@Autowired
	private PenyimpananMasukRepository ePenyimpananRepo;
	
	@Autowired
	private PenerimaanSupplierRepository ePenerimaanSuppRepo;
	
	public MasterProduct saveMasterProduct( MultipartFile image,
			String sku_code,String artikel_product, String nama_product, 
			int type, String type_name, String kategori, 
			String nama_kategori, String artikel_frame, String artikel_lens,String ukuran,
			Double kuantitas,Double hpp,Double harga_jual, String remarks
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
		p.setSku_code(sku_code);
		p.setArtikel_product(artikel_product);
		p.setNama_product(nama_product);
		p.setType(type);
		p.setType_name(type_name);
		p.setKategori(kategori);
		p.setNama_kategori(nama_kategori);
		p.setArtikel_frame(artikel_frame);
		p.setArtikel_lens(artikel_lens);
		p.setUkuran(ukuran);
		p.setKuantitas(kuantitas);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRemarks(remarks);
		p.setRowstatus(1);
		
		q.setId_office(1);
		q.setLokasi_office("Kantor Pusat");
		q.setSku_code(sku_code);
		q.setArtikel(artikel_product);
		q.setKategori(kategori);
		q.setNama_kategori(nama_kategori);
		q.setType(type);
		q.setType_name(type_name);
		q.setNama_barang(nama_product);
		q.setKuantitas(kuantitas);
		q.setUkuran(ukuran);
		
		q.setHpp(hpp);
		q.setHarga_jual(harga_jual);
		q.setRowstatus(1);
		eStockRepo.save(q);
		
		if (kuantitas != null) {
			if (kuantitas > 0) {
				PenyimpananMasuk f = new PenyimpananMasuk();
				f.setId_office(1);
				f.setLokasi_office("Kantor Pusat");
				f.setPenerimaan_code("PS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (ePenerimaanSuppRepo.count()+1));
				f.setTanggal_masuk(new Date());
				f.setSku_code(sku_code);
				f.setArtikel(artikel_product);
				f.setKategori(kategori);
				f.setNama_kategori(nama_kategori);
				f.setType(type);
				f.setType_name(type_name);
				f.setNama_barang(nama_product);
				f.setKuantitas(kuantitas);
				f.setUkuran(ukuran);
				f.setHpp(hpp);
				f.setHarga_jual(harga_jual);
				f.setKeterangan("Barang Masuk Dari Master Product");
				f.setRowstatus(1);
				ePenyimpananRepo.save(f);
			}
		}
		
		return eRepo.save(p);
	}
	
	public String saveMasterProductCustom( 
			MultipartFile image, String sku_code,String artikel_f,String artikel_s,
			String artikel_product, String nama_product, 
			int type, String type_name, String kategori, String nama_kategori,
			String artikel_frame, String artikel_lens,String artikel_frame_ns, String artikel_lens_ns,
			String ukuran, Double kuantitas,Double hpp,Double harga_jual, String remarks
			) {
		// region new product
		MasterProduct p = new MasterProduct();
		StockOffice q = new StockOffice();
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		String result = "Custom Product Berhasil!";
		
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
			q.setFoto_barang(Base64.getEncoder().encodeToString(image.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		p.setSku_code(sku_code);
		p.setArtikel_product(artikel_product);
		p.setNama_product(nama_product);
		p.setType(type);
		p.setType_name(type_name);
		p.setKategori(kategori);
		p.setNama_kategori(nama_kategori);
		p.setArtikel_frame(artikel_frame);
		p.setArtikel_lens(artikel_lens);
		p.setUkuran(ukuran);
		p.setKuantitas(kuantitas);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRemarks(remarks);
		p.setRowstatus(1);
		
		// end region new product
		
		// region pengurangan stock
		StockOffice penguranganF = new StockOffice();
		penguranganF = eStockRepo.findById_officeAndArtikel(1, artikel_f);
		if (penguranganF != null) {
			penguranganF.setKuantitas(penguranganF.getKuantitas() - 1);
			eStockRepo.save(penguranganF);	
		} else {
			result = artikel_f + " tidak tersedia di gudang!";
		}
		
		StockOffice penguranganS = new StockOffice();
		penguranganS = eStockRepo.findById_officeAndArtikel(1, artikel_s);
		if (penguranganS != null) {
			penguranganS.setKuantitas(penguranganS.getKuantitas() - 1);
			eStockRepo.save(penguranganS);
		} else {
			result = artikel_s + " tidak tersedia di gudang!";
		}
		// end region pengurangan stock
		
		// region stock baru lens dan frame
		StockOffice penambahanFrame = new StockOffice();
		penambahanFrame = eStockRepo.findById_officeAndArtikel(1,artikel_frame_ns);
		if (penambahanFrame != null) {
			penambahanFrame.setKuantitas(penambahanFrame.getKuantitas() + 1);
			eStockRepo.save(penambahanFrame);	
		} else {
			result = artikel_frame_ns + " tidak tersedia di gudang!";
		}
		
		StockOffice penambahanLens = new StockOffice();
		penambahanLens = eStockRepo.findById_officeAndArtikel(1,artikel_lens_ns);
		if (penambahanLens != null) {
			penambahanLens.setKuantitas(penambahanLens.getKuantitas() + 1);
			eStockRepo.save(penambahanLens);	
		} else {
			result = artikel_lens_ns + " tidak tersedia di gudang!";
		}
		// end region stock baru lens dan frame
		
		if (penguranganF == null || penguranganS == null || penambahanFrame == null || penambahanLens == null) {
			result = "Custom Product Gagal!";
		} else {
			q.setId_office(1);
			q.setLokasi_office("Kantor Pusat");
			q.setSku_code(sku_code);
			q.setArtikel(artikel_product);
			q.setKategori(kategori);
			q.setNama_kategori(nama_kategori);
			q.setType(type);
			q.setType_name(type_name);
			q.setNama_barang(nama_product);
			q.setKuantitas(kuantitas);
			q.setUkuran(ukuran);
			
			q.setHpp(hpp);
			q.setHarga_jual(harga_jual);
			q.setRowstatus(1);
			eStockRepo.save(q);
			
			if (kuantitas != null) {
				if (kuantitas > 0) {
					PenyimpananMasuk f = new PenyimpananMasuk();
					f.setId_office(1);
					f.setLokasi_office("Kantor Pusat");
					f.setPenerimaan_code("PS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (ePenerimaanSuppRepo.count()+1));
					f.setTanggal_masuk(new Date());
					f.setArtikel(artikel_product);
					f.setSku_code(sku_code);
					f.setKategori(kategori);
					f.setNama_kategori(nama_kategori);
					f.setType(type);
					f.setType_name(type_name);
					f.setNama_barang(nama_product);
					f.setKuantitas(kuantitas);
					f.setUkuran(ukuran);
					f.setHpp(hpp);
					f.setHarga_jual(harga_jual);
					f.setKeterangan("Barang Masuk Dari Custom Product");
					f.setRowstatus(1);
					ePenyimpananRepo.save(f);
				}
			}
			eRepo.save(p);	
		}

		return result;
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
	
	public MasterProduct findById(String id) {
		return eRepo.findById(id);
	}
	
	public MasterProduct findBySkuCode(String sku_code) {
		return eRepo.findBySkuCode(sku_code);
	}
	
	public MasterProduct findByArticle(String article) {
		return eRepo.findByArticle(article);
	}
	
	public List<MasterProduct> findByType(int type) {
		return eRepo.findByType(type);
	}
	
	public String update(
			Long id, MultipartFile image, String sku_code, String artikel_product,
			String nama_product, int type, String type_name,
			String kategori, String artikel_frame, String artikel_lens,String ukuran,
			String nama_kategori, Double hpp,
			Double harga_jual, String remarks ) {
		
		String img = "";
		
		MasterProduct p = new MasterProduct();
    	p = eRepo.findById(id).get();
    	
    	String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		
    	if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		
		try {
			img = Base64.getEncoder().encodeToString(image.getBytes());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		List<MasterStore> masterStore = eMasterStoreRepo.findByRowstatus(1);
    	for (int i=0; i<masterStore.size(); i++) {
        	StockStore r = new StockStore();
        	r = eStockStoreRepo.findById_storeAndArtikel(Math.toIntExact(masterStore.get(i).getId()), artikel_product);
        	
        	r.setFoto_barang(img);
			r.setSku_code(sku_code);
			r.setArtikel(artikel_product);
			r.setKategori(kategori);
			r.setNama_kategori(nama_kategori);
			r.setType(type);
			r.setType_name(type_name);
			r.setNama_barang(nama_product);
			
			r.setHarga_jual(harga_jual);
			r.setHpp(hpp);
			eStockStoreRepo.save(r);
    	}

    	List<MasterOffice> masterOffice = eMasterOfficeRepo.findByRowstatus(1);
    	for (int i=0; i<masterOffice.size(); i++) {
    		
        	StockOffice q = new StockOffice();
        	q = eStockRepo.findById_officeAndArtikel(Math.toIntExact(masterOffice.get(i).getId()),artikel_product);
        	
    		q.setFoto_barang(img);
			q.setSku_code(sku_code);
			q.setArtikel(artikel_product);
			q.setKategori(kategori);
			q.setNama_kategori(nama_kategori);
			q.setType(type);
			q.setType_name(type_name);
			q.setNama_barang(nama_product);
			q.setUkuran(ukuran);
			q.setHpp(hpp);
			q.setHarga_jual(harga_jual);
			q.setRowstatus(1);
			eStockRepo.save(q);
    	}
    	
		p.setImage(img);
		p.setSku_code(sku_code);
    	p.setArtikel_product(artikel_product);
		p.setNama_product(nama_product);
		p.setType(type);
		p.setType_name(type_name);
		p.setKategori(kategori);
		p.setNama_kategori(nama_kategori);
		p.setArtikel_frame(artikel_frame);
		p.setArtikel_lens(artikel_lens);
		p.setUkuran(ukuran);
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRemarks(remarks);
		p.setRowstatus(1);
		eRepo.save(p);
		
		return "Update success";
	}
}
