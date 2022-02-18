package com.gbsystem.rpbackoffice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gbsystem.rpbackoffice.entities.DetailPengirimanStoreToStore;
import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.services.PenyimpananMobileService;

@RestController
@RequestMapping("/penyimpananMobile")
@CrossOrigin
public class PenyimpananMobileController {
	@Autowired
	private PenyimpananMobileService penyimpananMobileService;
	
	
	@GetMapping("/stockStore")
	public double totalStockStore(@Param("id_store") int id_store){
		return penyimpananMobileService.totalStockStore(id_store);
	}
	
	@GetMapping("/stockStoreMasukAndKeluar")
	public Map totalQtyMasukAndKeluar(@Param("id_store") int id_store){
		double masuk = penyimpananMobileService.totalQtyKeluar(id_store);
		double keluar = penyimpananMobileService.totalQtyMasuk(id_store);
		double pindah = penyimpananMobileService.totalStockStorePindah(id_store);
		
		Map<String, Map<String, String>> response = new HashMap<>();
		Map<String, String> map = new HashMap<>();
		map.put("val A", String.valueOf(masuk));
		map.put("val B", String.valueOf(keluar));
		map.put("val C", String.valueOf(pindah));
		response.put("result", map);
		return response;
	}
	
	@GetMapping("/availStockStore")
	public List<StockStore> stockAvailPerStore(@Param("id_store") int id_store){
		
		return penyimpananMobileService.stockAvailPerStore(id_store);
	}
	
	@GetMapping("/availStockStoreByCategory")
	public List<StockStore> stockAvailPerStoreByCategory(@Param("id_store") int id_store, @Param("kategori") String kategori){
		
		return penyimpananMobileService.stockAvailPerStoreByCategory(id_store, kategori);
	}
	
	@GetMapping("/searchStockStore")
	public List<StockStore> searchStockStore(@Param("id_store") int id_store, @Param("keyword") String keyword){
		
		return penyimpananMobileService.searchStockStore(id_store,keyword);
	}
	
	@GetMapping("/searchStockStoreByCategory")
	public List<StockStore> searchByCategory(@Param("id_store") int id_store, @Param("kategori") String kategori, @Param("keyword") String keyword){
		
		return penyimpananMobileService.searchByCategory(id_store,kategori,keyword);
	}
	
	@GetMapping("/riwayatPemindahan")
	public List<DetailPengirimanStoreToStore> allPindah(@Param("pengiriman_code") String pengiriman_code){
		
		return penyimpananMobileService.allPindah(pengiriman_code);
	}
	
	@PostMapping("/updateStock")
	public StockStore update(
			@RequestParam("id") Long id,@RequestParam("id_store") int id_store,@RequestParam("lokasi_store") String lokasi_store,
			@RequestParam("artikel") String artikel,@RequestParam("type") String type,@RequestParam("type_name") String type_name, 
			@RequestParam("kategori") String kategori,@RequestParam("nama_kategori") String nama_kategori,@RequestParam("nama_barang") String nama_barang,
			@RequestParam("kuantitas") double kuantitas,@RequestParam("ukuran") String ukuran,@RequestParam("foto_barang") MultipartFile foto_barang,
			@RequestParam("hpp")double hpp,@RequestParam("harga_jual") double harga_jual,@RequestParam("rowstatus") int rowstatus) {
		
		return penyimpananMobileService.update(id,id_store,lokasi_store,artikel,type,type_name,kategori, kategori, nama_barang, kuantitas, ukuran, foto_barang, hpp, harga_jual,rowstatus);
	}
	
	@GetMapping("/stockMovement")
	public Map getAllPerStore(@Param("id_store") int id_store){
		
		List<PenyimpananStoreKeluar> keluar = penyimpananMobileService.getAllPerStoreKeluar(id_store);
		List<PenyimpananStoreMasuk> masuk = penyimpananMobileService.getAllPerStoreMasuk(id_store);
		List<PengirimanStoreToStore> pindah = penyimpananMobileService.getAllPerStorePindah(id_store);
		
		Map<String, Map<String, Object>> response = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		map.put("keluar", keluar);
		map.put("masuk", masuk);
		map.put("pindah", pindah);
		response.put("result", map);
		return response;
	}
	
	@GetMapping("/searchBarangMasuk")
	public Map searchBarangMasuk(@Param("id_store") int id_store, @Param("keyword") String keyword){
		List<PenyimpananStoreMasuk> masuk = penyimpananMobileService.search(id_store, keyword);
		Map<String, Object> response = new HashMap<>();
		response.put("result", masuk);
		return response;
	}
	
	@GetMapping("/searchBarangKeluar")
	public Map searchBarangKeluar(@Param("id_store") int id_store, @Param("keyword") String keyword){
		List<PenyimpananStoreKeluar> keluar = penyimpananMobileService.searchKeluar(id_store, keyword);
		Map<String, Object> response = new HashMap<>();
		response.put("result", keluar);
		return response;
	}
	
	@GetMapping("/searchBarangPindah")
	public Map searchBarangPindah(@Param("id_store") int id_store, @Param("keyword") String keyword){
		List<PengirimanStoreToStore> pindah = penyimpananMobileService.searchStorePindah(id_store, keyword);
		Map<String, Object> response = new HashMap<>();
		response.put("result", pindah);
		return response;
	}
}
