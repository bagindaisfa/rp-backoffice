package com.gbsystem.rpbackoffice.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.DetailPengirimanStoreToStore;
import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.DetailPengirimanStoreToStoreRepository;
import com.gbsystem.rpbackoffice.repository.PengirimanStoreToStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreKeluarRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PenyimpananMobileService {
	@Autowired
	private StockStoreRepository eStockStoreRepo;
	@Autowired
	private PenyimpananStoreMasukRepository ePenyimpananStoreMasukRepository;
	@Autowired
	private PenyimpananStoreKeluarRepository ePenyimpananStoreKeluarRepository;
	@Autowired
	private DetailPengirimanStoreToStoreRepository eDetailPengirimanStoreToStoreRepository;
	@Autowired
	private PengirimanStoreToStoreRepository ePengirimanStoreToStoreRepository; 
	
	public double totalStockStore(int id_store){
		
		double stock_store = eStockStoreRepo.totalStocPerkStore(1, id_store);
		
		
		return stock_store;
	}
	
	public double totalQtyMasuk(int id_store){
			
		double store_masuk = ePenyimpananStoreMasukRepository.totalQtyMasuk(1, id_store);
		
		
		return store_masuk;
	}
	
	public double totalQtyKeluar(int id_store){
		
		double store_keluar = ePenyimpananStoreKeluarRepository.totalQtyKeluar(1, id_store);
		
		
		return store_keluar;
	}


	public double totalStockStorePindah(int id_store){
		
		double store_pindah = eDetailPengirimanStoreToStoreRepository.totalQtyPindah(1,id_store);
		
		
		return store_pindah;
	}
	
	public List<StockStore> stockAvailPerStore(int id_store) {
		
		return eStockStoreRepo.stockAvailPerStore(id_store);
	}
	
	public List<StockStore> stockAllItemPerStore(int id_store) {
		
		return eStockStoreRepo.stockAllItemPerStore(id_store);
	}
	
	public List<StockStore> stockAvailPerStoreByCategory(int id_store, String kategori) {
		
		return eStockStoreRepo.stockAvailPerStoreByCategory(id_store,kategori);
	}
	
	public List<StockStore> stockAvailPerStoreByType(int id_store, String type) {
		
		return eStockStoreRepo.stockAvailPerStoreByType(id_store,type);
	}
	
	public List<StockStore> searchStockStore(int id_store, String keyword) {
		
		return eStockStoreRepo.search(id_store, keyword);
	}
	
	public List<StockStore> searchAllStock(int id_store, String keyword) {
		
		return eStockStoreRepo.searchAllStock(id_store, keyword);
	}
	
	public List<StockStore> searchByCategory(int id_store, String kategori, String keyword) {
		
		return eStockStoreRepo.searchByCategory(id_store, kategori, keyword);
	}
	
	public List<StockStore> searchByType(int id_store, int type, String keyword) {
		
		return eStockStoreRepo.searchByType(id_store, type, keyword);
	}
	
	public List<StockStore> stockMinimum(int id_store) {
		return eStockStoreRepo.stockMinimum(id_store);
	}
	
	public List<PenyimpananStoreKeluar> getAllPerStoreKeluar(int id_store) {
		
		return ePenyimpananStoreKeluarRepository.getAllPerStoreKeluar(id_store);
	}
	
	public List<PenyimpananStoreMasuk> getAllPerStoreMasuk(int id_store) {
		
		return ePenyimpananStoreMasukRepository.getAllPerStoreMasuk(id_store);
	}
	
	public List<PenyimpananStoreMasuk> search(int id_store, String keyword) {
		
		return ePenyimpananStoreMasukRepository.searchMobile(id_store, keyword);
	}
	
	public List<PenyimpananStoreKeluar> searchKeluar(int id_store, String keyword) {
		
		return ePenyimpananStoreKeluarRepository.searchMobile(id_store, keyword);
	}
	
	public List<PengirimanStoreToStore> getAllPerStorePindah(int id_store) {
		
		return ePengirimanStoreToStoreRepository.getAllPerStorePindah(id_store);
	}
	
	public List<PengirimanStoreToStore> searchStorePindah(int id_store, String keyword) {
		
		return ePengirimanStoreToStoreRepository.searchMobile(id_store, keyword);
	}
	
	public List<DetailPengirimanStoreToStore> allPindah(String pengiriman_code){
		return eDetailPengirimanStoreToStoreRepository.all(pengiriman_code);
	}
	
	public StockStore update(Long id,int id_store, String lokasi_store,
			String artikel, int type, String type_name, 
			String kategori, String nama_kategori, String nama_barang,
			double kuantitas, String ukuran, MultipartFile foto_barang,
			double hpp, double harga_jual, int rowstatus) {
		
		StockStore p = new StockStore();
		p = eStockStoreRepo.getById(id);
		p.setArtikel(artikel);
		p.setId_store(id_store);
		p.setLokasi_store(lokasi_store);
		p.setType(type);
		p.setType_name(type_name);
		p.setKategori(kategori);
		p.setNama_kategori(nama_kategori);
		p.setNama_barang(nama_barang);
		p.setKuantitas(kuantitas);
		p.setUkuran(ukuran);
		String fileName = StringUtils.cleanPath(foto_barang.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setFoto_barang(Base64.getEncoder().encodeToString(foto_barang.getBytes()));
		}catch(IOException e) {
			e.printStackTrace();
		}
		p.setHpp(hpp);
		p.setHarga_jual(harga_jual);
		p.setRowstatus(1);
		return eStockStoreRepo.save(p);
	}
}
