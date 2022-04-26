package com.gbsystem.rpbackoffice.controllers;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gbsystem.rpbackoffice.entities.DetailPesanan;
import com.gbsystem.rpbackoffice.entities.Penjualan;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerArtikel;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerKaryawan;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerKategori;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerPelanggan;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerProduk;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerTipe;
import com.gbsystem.rpbackoffice.services.PenjualanService;

@RestController
@RequestMapping("/store")
@CrossOrigin
public class PenjualanController {

	@Autowired
	private PenjualanService penjualanService;
	
	@GetMapping("/penjualan")
	public ResponseEntity<List<Penjualan>> getAll(){
		return new ResponseEntity<>(penjualanService.getAllPenjualan(), HttpStatus.OK);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<Penjualan>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(penjualanService.search(keyword), HttpStatus.OK);
    }
	
	@PostMapping("/add")
	@ResponseBody
	public List<Penjualan> savePenjualan(@RequestBody Penjualan penjualan) 
	{
		List<Penjualan> penjualanResponse = penjualanService.savePenjualan(penjualan);
		return penjualanResponse;
	}
	
	@PostMapping(value = "/update")
	@ResponseBody
    public Penjualan update(@RequestBody Penjualan penjualan) throws Exception {
    	
		Penjualan penjualanResponse = penjualanService.update(penjualan);
    	return penjualanResponse;
		
    }
	
	@PostMapping("/delete")
    public Penjualan deletePenjualan(@RequestParam("id") Long id)
    {
		Penjualan penjualanResponse = penjualanService.deletePenjualanById(id);
    	return penjualanResponse;
    }
	
	@GetMapping("/rangkumanMobile")
    public Map rangkumanPenjualanMobile(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		Double jmlPenjualan = penjualanService.countingPenjualanMobile(id_store, start_date, end_date);
		Double omset = penjualanService.pendapatanMobile(id_store, start_date, end_date);
		
		Map<String, Map<String, String>> response = new HashMap<>();
		Map<String, String> map = new HashMap<>();
		map.put("jmlPenjualan", String.valueOf(jmlPenjualan));
		map.put("omset", String.valueOf(omset));
		response.put("result", map);
		return response;
    }
	
	@GetMapping("/rangkumanTotalArtikel")
    public Map rangkumanPenjualanMobilePerArtikel(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		Double totalTerjual = penjualanService.countingArtikel(id_store, start_date, end_date);
		Double omset = penjualanService.totalPerArtikel(id_store, start_date, end_date);
		
		Map<String, Map<String, String>> response = new HashMap<>();
		Map<String, String> map = new HashMap<>();
		map.put("totalTerjual", String.valueOf(totalTerjual));
		map.put("omset", String.valueOf(omset));
		response.put("result", map);
		return response;
    }
	
	@GetMapping("/rangkumanTotalTipe")
    public Map rangkumanPenjualanMobilePerTipe(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		Double totalTerjual = penjualanService.countingTipe(id_store, start_date, end_date);
		Double omset = penjualanService.totalPerTipe(id_store, start_date, end_date);
		
		Map<String, Map<String, String>> response = new HashMap<>();
		Map<String, String> map = new HashMap<>();
		map.put("totalTerjual", String.valueOf(totalTerjual));
		map.put("omset", String.valueOf(omset));
		response.put("result", map);
		return response;
    }
	 
	@GetMapping("/rangkumanMobileKaryawan")
    public List<RekapPenjualanPerKaryawan> rekapKaryawan(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		List<RekapPenjualanPerKaryawan> rekapKaryawan = penjualanService.rekapKaryawan(id_store, start_date, end_date);
		return rekapKaryawan;
    }
	
	@GetMapping("/rangkumanMobileArtikel")
    public List<RekapPenjualanPerArtikel> rekapArtikel(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		List<RekapPenjualanPerArtikel> rekapArtikel = penjualanService.rekapArtikel(id_store, start_date, end_date);
		return rekapArtikel;
    }
	
	@GetMapping("/rangkumanMobileTipe")
    public List<RekapPenjualanPerTipe> rekapTipe(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		List<RekapPenjualanPerTipe> rekapTipe = penjualanService.rekapTipe(id_store, start_date, end_date);
		return rekapTipe;
    }
	
	@GetMapping("/rangkumanMobileTipeTerlaris")
    public List<RekapPenjualanPerTipe> rekapTipeTerlaris(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		List<RekapPenjualanPerTipe> rekapTipeTerlaris = penjualanService.rekapTipeTerlaris(id_store, start_date, end_date);
		return rekapTipeTerlaris;
    }
	
	@GetMapping("/rangkumanMobileKategori")
    public List<RekapPenjualanPerKategori> rekapKategori(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		List<RekapPenjualanPerKategori> rekapKategori = penjualanService.rekapkategori(id_store, start_date, end_date);
		return rekapKategori;
    }
	
	@GetMapping("/rangkumanMobileKategoriTerlaris")
    public List<RekapPenjualanPerKategori> rekapKategoriTerlaris(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		List<RekapPenjualanPerKategori> rekapKategoriTerlaris = penjualanService.rekapkategoriTerlaris(id_store, start_date, end_date);
		return rekapKategoriTerlaris;
    }
	
	@GetMapping("/rangkumanMobileProduk")
    public List<RekapPenjualanPerProduk> rekapProduk(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		List<RekapPenjualanPerProduk> rekapProduk = penjualanService.rekapProduk(id_store, start_date, end_date);
		return rekapProduk;
    }
	
	@GetMapping("/rangkumanMobileProdukShorted")
    public List<RekapPenjualanPerProduk> rekapProdukShorted(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date, @Param("orderBy") String orderBy,@Param("sortDir") String sortDir) {
		List<RekapPenjualanPerProduk> rekapProdukShorted = penjualanService.rekapProdukShorted(id_store, start_date, end_date, orderBy, sortDir );
		return rekapProdukShorted;
    }
	
	@GetMapping("/rangkumanMobileProdukTerlaris")
    public List<RekapPenjualanPerProduk> rekapProdukTerlaris(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		List<RekapPenjualanPerProduk> rekapProdukTerlaris = penjualanService.rekapProdukTerlaris(id_store, start_date, end_date);
		return rekapProdukTerlaris;
    }
	
	@GetMapping("/rangkumanMobilePelanggan")
    public List<RekapPenjualanPerPelanggan> rekapPelanggan(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		List<RekapPenjualanPerPelanggan> rekapPelanggan = penjualanService.rekapPelanggan(id_store, start_date, end_date);
		return rekapPelanggan;
    }
	
	@GetMapping("/rangkumanMobilePelangganPerTanggal")
    public List<Penjualan> rekapPelangganPerTanggal(@Param("id_store") int id_store,@Param("start_date") String start_date,@Param("end_date") String end_date, @Param("no_hp_pelanggan") String no_hp_pelanggan) {
		List<Penjualan> rekapPelangganPerTanggal = penjualanService.rekapPelangganPerTanggal(id_store, start_date, end_date, no_hp_pelanggan);
		return rekapPelangganPerTanggal;
    }
	
	@GetMapping("/subRiwayatPelanggan")
	public List<Penjualan> subRiwayatPelanggan(int id_store, String start_date, String end_date, String no_hp_pelanggan) {
		List<Penjualan> subRiwayatPelanggan = penjualanService.subRiwayatPelanggan(id_store,start_date,end_date,no_hp_pelanggan);
		return subRiwayatPelanggan;
	}
	
	@GetMapping("/subRiwayatTerakhir")
	public List<Penjualan> subRiwayatTerakhir(int id_store, String start_date, String end_date) {
		List<Penjualan> subRiwayatTerakhir = penjualanService.subRiwayatTerakhir(id_store,start_date,end_date);
		return subRiwayatTerakhir;
	}
	
	@GetMapping("/riwayatPertanggal")
	public List<Penjualan> riwayatPertanggal(int id_store, String start_date, String end_date) {
		List<Penjualan> riwayatPertanggal = penjualanService.riwayatPertanggal(id_store,start_date,end_date);
		return riwayatPertanggal;
	}
	
	@GetMapping("/rangkumanMobilePenjualanKaryawan")
    public List<DetailPesanan> findByKaryawanId(@Param("id_store") int id_store,@Param("id_karyawan") int id_karyawan,@Param("start_date") String start_date,@Param("end_date") String end_date) {
		List<DetailPesanan> rekapKaryawan = penjualanService.findByKaryawanId(id_store,id_karyawan, start_date, end_date);
		return rekapKaryawan;
    }
}
