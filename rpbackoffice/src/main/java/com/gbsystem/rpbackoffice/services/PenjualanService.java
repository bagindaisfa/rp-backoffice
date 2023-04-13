package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.Penjualan;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerArtikel;
import com.gbsystem.rpbackoffice.entities.DetailPesanan;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.Pelanggan;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerKaryawan;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerKategori;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerPelanggan;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerProduk;
import com.gbsystem.rpbackoffice.entities.RekapPenjualanPerTipe;
import com.gbsystem.rpbackoffice.repository.DetailPesananRepository;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PelangganRepository;
import com.gbsystem.rpbackoffice.repository.PenjualanRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreKeluarRepository;
import com.gbsystem.rpbackoffice.repository.PesananTungguRepository;
import com.gbsystem.rpbackoffice.repository.RekapPenjualanPerArtikelRepository;
import com.gbsystem.rpbackoffice.repository.RekapPenjualanPerKaryawanRepository;
import com.gbsystem.rpbackoffice.repository.RekapPenjualanPerKategoriRepository;
import com.gbsystem.rpbackoffice.repository.RekapPenjualanPerPelangganRepository;
import com.gbsystem.rpbackoffice.repository.RekapPenjualanPerProdukRepository;
import com.gbsystem.rpbackoffice.repository.RekapPenjualanPerTipeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PenjualanService {
	
	@Autowired
	private PenjualanRepository eRepo;
	
	@Autowired
	private DetailPesananRepository eDetailRepo;
	
	@Autowired
	private StockStoreRepository eStockRepo;
	
	@Autowired
	private PenyimpananStoreKeluarRepository ePenyimpananStoreKeluarRepo;
	
	@Autowired
	private PelangganRepository ePelangganRepo;
	
	@Autowired
	private RekapPenjualanPerKaryawanRepository eRekapPenjualanPerKaryawanRepository;
	
	@Autowired
	private RekapPenjualanPerArtikelRepository eRekapPenjualanPerArtikelRepository;
	
	@Autowired
	private RekapPenjualanPerTipeRepository eRekapPenjualanPerTipeRepository;
	
	@Autowired
	private RekapPenjualanPerKategoriRepository eRekapPenjualanPerKategoriRepository;
	
	@Autowired
	private RekapPenjualanPerProdukRepository eRekapPenjualanPerProdukRepository;
	
	@Autowired
	private RekapPenjualanPerPelangganRepository eRekapPenjualanPerPelangganRepository;
	
	@Autowired
	private MasterProductRepository eMasterProductRepository;
	
	@Autowired
	private PesananTungguRepository ePesananTungguRepo;
	
	public List<Penjualan> savePenjualan( Penjualan penjualan) {
		if (penjualan.getId_store() != 8) {
			Penjualan item = new Penjualan();
			Double sum_qty = 0.0;
			Double total_harga = 0.0;
			if (penjualan.getId_transaksi() == null) {
				System.out.println("Tidak ada pesanan tunggu");
			} else {
				ePesananTungguRepo.deleteByIdTransaksi(penjualan.getId_transaksi());
			}
			String id_transaksi = "INV-" + new SimpleDateFormat("yyMM").format(new Date()) + "-S" + (eRepo.count() + 1);
			Date tanggal_transaksi = new Date();
			List<DetailPesanan> details = new ArrayList<>();
			item.setTanggal_transaksi(tanggal_transaksi);
			item.setId_transaksi(id_transaksi);
			item.setId_store(penjualan.getId_store());
			item.setLokasi_store(penjualan.getLokasi_store());
			item.setNama_pelanggan(penjualan.getNama_pelanggan());
			item.setNo_hp_pelanggan(penjualan.getNo_hp_pelanggan());
			item.setId_karyawan(penjualan.getId_karyawan());
			item.setNama_karyawan(penjualan.getNama_karyawan());
			item.setDiskon(penjualan.getDiskon());
			item.setDiskon_remark(penjualan.getDiskon_remark());
			item.setMetode_bayar(penjualan.getMetode_bayar());
			item.setBank_name(penjualan.getBank_name());
			item.setNo_rek(penjualan.getNo_rek());
			item.setEkspedisi(penjualan.getEkspedisi());
			item.setOngkir(penjualan.getOngkir());
			item.setRowstatus(1);
			
			for(int i = 0; i < penjualan.getDetailPesananList().size(); i++) {
				DetailPesanan d = new DetailPesanan();
				MasterProduct product = eMasterProductRepository.findByArticle(penjualan.getDetailPesananList().get(i).getArtikel());
				
				d.setTanggal_transaksi(tanggal_transaksi);
				d.setId_transaksi(id_transaksi);
				d.setId_store(penjualan.getDetailPesananList().get(i).getId_store());
				d.setLokasi_store(penjualan.getDetailPesananList().get(i).getLokasi_store());
				d.setArtikel(penjualan.getDetailPesananList().get(i).getArtikel());
				d.setSku_code(penjualan.getDetailPesananList().get(i).getSku_code());
				d.setType(product.getType());
				d.setType_name(product.getType_name());
				d.setKategori(product.getKategori());
				d.setNama_kategori(product.getNama_kategori());
				d.setNama_barang(penjualan.getDetailPesananList().get(i).getNama_barang());
				d.setHarga(product.getHarga_jual());
				d.setHarga_baru(penjualan.getDetailPesananList().get(i).getHarga_baru());
				d.setHarga_baru_remark(penjualan.getDetailPesananList().get(i).getHarga_baru_remark());
				d.setKuantitas(penjualan.getDetailPesananList().get(i).getKuantitas());
				d.setTotal(penjualan.getDetailPesananList().get(i).getTotal());
				d.setRowstatus(1);
				sum_qty += penjualan.getDetailPesananList().get(i).getKuantitas();
				total_harga += (penjualan.getDetailPesananList().get(i).getKuantitas() * penjualan.getDetailPesananList().get(i).getHarga_baru());
				d.setPenjualan(item);
				details.add(d);
				
				StockStore check = new StockStore();
				check = eStockRepo.findById_storeAndArtikel(
						penjualan.getId_store(),
						penjualan.getDetailPesananList().get(i).getArtikel());
				check.setKuantitas(check.getKuantitas() - penjualan.getDetailPesananList().get(i).getKuantitas());
				eStockRepo.save(check);
				
				PenyimpananStoreKeluar store_asal = new PenyimpananStoreKeluar();
				store_asal.setPengiriman_code(id_transaksi);
				store_asal.setLokasi_office("-");
				store_asal.setTanggal_keluar(new Date());
				store_asal.setId_store(penjualan.getId_store());
				store_asal.setLokasi_store(penjualan.getLokasi_store());
				store_asal.setArtikel(penjualan.getDetailPesananList().get(i).getArtikel());
				store_asal.setType(product.getType());
				store_asal.setType_name(product.getType_name());
				store_asal.setKategori(product.getKategori());
				store_asal.setNama_kategori(product.getNama_kategori());
				store_asal.setNama_barang(penjualan.getDetailPesananList().get(i).getNama_barang());
				store_asal.setKuantitas(penjualan.getDetailPesananList().get(i).getKuantitas());
				
				store_asal.setHpp(check.getHpp());
				store_asal.setHarga_jual(penjualan.getDetailPesananList().get(i).getHarga());
				store_asal.setKeterangan("Penjualan " + id_transaksi);
				store_asal.setRowstatus(1);
				ePenyimpananStoreKeluarRepo.save(store_asal);
				
			}
			
			Double diskon = item.getDiskon() == null ? 0.0 : item.getDiskon();
			Double ongkir = item.getOngkir() == null ? 0.0 : item.getOngkir();
			
			Double total_new = 0.0;
			if (diskon > 100.0) {
				total_new = (total_harga - diskon) + ongkir;
			} else if (diskon <= 100.0) {
				total_new = (total_harga - (total_harga * (diskon / 100))) + ongkir;
			} else {
				total_new = (total_harga) + ongkir;
			}
			
			item.setTotal(total_new);
			item.setKembalian(penjualan.getKembalian());
			item.setSum_qty(sum_qty);
			item.setDetailPesananList(details);
			eRepo.save(item);
			
			if (total_new > 1000000.0) {
				List<Pelanggan> pembeli = new ArrayList<>();
				pembeli = ePelangganRepo.findByNoHp(item.getNo_hp_pelanggan());
				for (int i = 0; i < pembeli.size(); i++) {

				    LocalDate now = LocalDate.now();
				    LocalDate firstDay = now.with(firstDayOfYear());
				    
				    if (now != firstDay) {
				    	pembeli.get(i).setPoin(pembeli.get(i).getPoin() + (penjualan.getTotal() * 0.001));
				    	pembeli.get(i).setTotal_pembelian(pembeli.get(i).getTotal_pembelian() + penjualan.getTotal());
				    	pembeli.get(i).setTotal_kunjungan(pembeli.get(i).getTotal_kunjungan() + 1);
						ePelangganRepo.save(pembeli.get(i));
				    } else {
				    	pembeli.get(i).setPoin(penjualan.getTotal() * 0.001);
				    	pembeli.get(i).setTotal_pembelian(pembeli.get(i).getTotal_pembelian() + penjualan.getTotal());
				    	pembeli.get(i).setTotal_kunjungan(pembeli.get(i).getTotal_kunjungan() + 1);
						ePelangganRepo.save(pembeli.get(i));
				    }
				}
			}
			
			return eRepo.findByIdTransaksi(id_transaksi);
		} else {
			return null;
		}
		
	}
	
	public List<Penjualan> getAllPenjualan(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<Penjualan> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<Penjualan> getAllPerStore(int id_store){

		return eRepo.getAllPerStore(id_store);
	}
	
	public List<Penjualan> searchPerStore(int id_store, String keyword){
		return eRepo.searchPerStore(id_store,keyword);
	}
	
	public List<Penjualan> subRiwayatPelanggan(int id_store, String start_date, String end_date, String no_hp_pelanggan){
		return eRepo.subRiwayatPelanggan(id_store,start_date,end_date,no_hp_pelanggan);
	}
	
	public List<Penjualan> riwayatPertanggal(int id_store, String start_date, String end_date){
		return eRepo.riwayatPertanggal(id_store,start_date,end_date);
	}
	
	public List<Penjualan> subRiwayatTerakhir(int id_store, String start_date, String end_date){
		return eRepo.subRiwayatTerakhir(id_store,start_date,end_date);
	}
	
	public Penjualan deletePenjualanById(Long id)
    {
		Penjualan p = new Penjualan();
    	p = eRepo.findById(id).get();
		p.setRowstatus(0);
		
		if (p.getTotal() > 1000000) {
			List<Pelanggan> pembeli = new ArrayList<>();
			pembeli = ePelangganRepo.findByNoHp(p.getNo_hp_pelanggan());
			for (int i = 0; i < pembeli.size(); i++) {
				pembeli.get(i).setPoin(pembeli.get(i).getPoin() - (p.getTotal() * 0.01));
				ePelangganRepo.save(pembeli.get(i));	
			}
		}
		List<PenyimpananStoreKeluar> j = new ArrayList<>();
		j = ePenyimpananStoreKeluarRepo.findByPengiriman_code(p.getId_transaksi());
		for (int y = 0; y < j.size(); y++) {
			j.get(y).setRowstatus(0);
			ePenyimpananStoreKeluarRepo.save(j.get(y));
		}
		
		List<DetailPesanan> details = new ArrayList<>();
		for(int i = 0; i < p.getDetailPesananList().size(); i++) {
			p.getDetailPesananList().get(i).setRowstatus(0);
			p.getDetailPesananList().get(i).setPenjualan(p);
			details.add(p.getDetailPesananList().get(i));
			
			StockStore check = new StockStore();
			check = eStockRepo.findById_storeAndArtikel(
					p.getDetailPesananList().get(i).getId_store(),
					p.getDetailPesananList().get(i).getArtikel()
					);
			check.setKuantitas(check.getKuantitas() + p.getDetailPesananList().get(i).getKuantitas());
			eStockRepo.save(check);
			
			
		}
    	p.setDetailPesananList(details);
    	return eRepo.save(p);
    }
	
	public Penjualan update(Penjualan penjualan) {
		Penjualan p = new Penjualan();
		p = eRepo.findById(penjualan.getId()).get();
		
		ePenyimpananStoreKeluarRepo.deleteStoreKeluar(penjualan.getId_transaksi());
		
		if (p.getTotal() > 1000000) {
			List<Pelanggan> pembeli = new ArrayList<>();
			pembeli = ePelangganRepo.findByNoHp(p.getNo_hp_pelanggan());
			for (int i = 0; i < pembeli.size(); i++) {
				pembeli.get(i).setPoin(pembeli.get(i).getPoin() - (p.getTotal() * 0.01));
				ePelangganRepo.save(pembeli.get(i));	
			}
		}
		
		for(int i = 0; i < p.getDetailPesananList().size(); i++) {
			StockStore check = new StockStore();
			check = eStockRepo.findById_storeAndArtikel(
					p.getDetailPesananList().get(i).getId_store(),
					p.getDetailPesananList().get(i).getArtikel()
					);
			check.setKuantitas(check.getKuantitas() + p.getDetailPesananList().get(i).getKuantitas());
			eStockRepo.save(check);
		}
		
		return saveUpdate(penjualan);
		
	}
	
	private Penjualan saveUpdate( Penjualan penjualan) {
		Penjualan p = new Penjualan();
		Double sum_qty = 0.0;
    	p = eRepo.findById(penjualan.getId()).get();
    	List<DetailPesanan> details = new ArrayList<>();
    	
    	p.setTanggal_transaksi(penjualan.getTanggal_transaksi());
		p.setId_transaksi(penjualan.getId_transaksi());
		p.setId_store(penjualan.getId_store());
		p.setLokasi_store(penjualan.getLokasi_store());
		p.setDiskon(penjualan.getDiskon());
		p.setDiskon_remark(penjualan.getDiskon_remark());
		p.setMetode_bayar(penjualan.getMetode_bayar());
		p.setBank_name(penjualan.getBank_name());
		p.setNo_rek(penjualan.getNo_rek());
		p.setEkspedisi(penjualan.getEkspedisi());
		p.setOngkir(penjualan.getOngkir());
		p.setTotal(penjualan.getTotal());
		p.setKembalian(penjualan.getKembalian());
		p.setNama_pelanggan(penjualan.getNama_pelanggan());
		p.setNama_karyawan(penjualan.getNama_karyawan());
		p.setId_karyawan(penjualan.getId_karyawan());
		p.setRowstatus(1);
		
		if (penjualan.getTotal() > 1000000) {
			List<Pelanggan> pembeli = new ArrayList<>();
			pembeli = ePelangganRepo.findByNoHp(p.getNo_hp_pelanggan());
			for (int i = 0; i < pembeli.size(); i++) {
				
			    LocalDate now = LocalDate.now();
			    LocalDate firstDay = now.with(firstDayOfYear());
			    
			    if (now != firstDay) {
			    	pembeli.get(i).setPoin(pembeli.get(i).getPoin() + (penjualan.getTotal() * 0.001));
					ePelangganRepo.save(pembeli.get(i));
			    } else {
			    	pembeli.get(i).setPoin(penjualan.getTotal() * 0.001);
					ePelangganRepo.save(pembeli.get(i));
			    }
			}
		}
		
		for(int i = 0; i < penjualan.getDetailPesananList().size(); i++) {
			DetailPesanan d = new DetailPesanan();
			MasterProduct product = eMasterProductRepository.findByArticle(penjualan.getDetailPesananList().get(i).getArtikel());
			d = p.getDetailPesananList().get(i);
			d.setTanggal_transaksi(penjualan.getTanggal_transaksi());
			d.setId_transaksi(penjualan.getId_transaksi());
			d.setId_store(penjualan.getDetailPesananList().get(i).getId_store());
			d.setLokasi_store(penjualan.getDetailPesananList().get(i).getLokasi_store());
			d.setArtikel(penjualan.getDetailPesananList().get(i).getArtikel());
			d.setKategori(penjualan.getDetailPesananList().get(i).getKategori());
			d.setNama_kategori(penjualan.getDetailPesananList().get(i).getNama_kategori());
			d.setType(penjualan.getDetailPesananList().get(i).getType());
			d.setType_name(penjualan.getDetailPesananList().get(i).getType_name());
			d.setNama_barang(penjualan.getDetailPesananList().get(i).getNama_barang());
			d.setHarga(product.getHarga_jual());
			d.setHarga_baru(penjualan.getDetailPesananList().get(i).getHarga_baru());
			d.setHarga_baru_remark(penjualan.getDetailPesananList().get(i).getHarga_baru_remark());
			d.setKuantitas(penjualan.getDetailPesananList().get(i).getKuantitas());
			d.setTotal(penjualan.getDetailPesananList().get(i).getTotal());
			d.setRowstatus(penjualan.getDetailPesananList().get(i).getRowstatus());
			sum_qty += penjualan.getDetailPesananList().get(i).getKuantitas();
			
			if (penjualan.getDetailPesananList().get(i).getRowstatus() == 1) {
				StockStore check = new StockStore();
				check = eStockRepo.findById_storeAndArtikel(
						penjualan.getDetailPesananList().get(i).getId_store(),
						penjualan.getDetailPesananList().get(i).getArtikel()
						);
				check.setKuantitas((check.getKuantitas() + p.getDetailPesananList().get(i).getKuantitas()) - penjualan.getDetailPesananList().get(i).getKuantitas());
			
				PenyimpananStoreKeluar store_asal = new PenyimpananStoreKeluar();
				store_asal.setPengiriman_code(penjualan.getId_transaksi());
				store_asal.setLokasi_office("-");
				store_asal.setTanggal_keluar(penjualan.getTanggal_transaksi());
				store_asal.setId_store(penjualan.getId_store());
				store_asal.setLokasi_store(penjualan.getLokasi_store());
				store_asal.setArtikel(penjualan.getDetailPesananList().get(i).getArtikel());
				store_asal.setKategori(penjualan.getDetailPesananList().get(i).getKategori());
				store_asal.setNama_kategori(penjualan.getDetailPesananList().get(i).getNama_kategori());
				store_asal.setType(penjualan.getDetailPesananList().get(i).getType());
				store_asal.setType_name(penjualan.getDetailPesananList().get(i).getType_name());
				store_asal.setNama_barang(penjualan.getDetailPesananList().get(i).getNama_barang());
				store_asal.setKuantitas(penjualan.getDetailPesananList().get(i).getKuantitas());
				store_asal.setHpp(check.getHpp());
				store_asal.setHarga_jual(penjualan.getDetailPesananList().get(i).getHarga());
				store_asal.setKeterangan("Penjualan " + penjualan.getId_transaksi());
				store_asal.setRowstatus(1);
				ePenyimpananStoreKeluarRepo.save(store_asal);
				eStockRepo.save(check);
			}
			d.setPenjualan(p);
			details.add(d);
		}
		p.setSum_qty(sum_qty);
		p.setDetailPesananList(details);
    	eRepo.save(p);
    	
    	return penjualan;
	}

	
	public Double countingPenjualanMobile(int id_store, String start_date, String end_date){
		
		Double jmlTransaksi = eRepo.countingMobile(id_store, start_date, end_date) == null? 0.00 : eRepo.countingMobile(id_store, start_date, end_date);
		
		return jmlTransaksi;
	}
	
	public Double pendapatanMobile(int id_store, String start_date, String end_date){
		
		Double omset = eRepo.totalMobile(id_store, start_date, end_date) == null? 0.00 : eRepo.totalMobile(id_store, start_date, end_date);
		
		return omset;
	}
	
	
	public Double countingArtikel(int id_store, String start_date, String end_date){
		
		Double jmlTransaksi = eRekapPenjualanPerArtikelRepository.countingArtikel(id_store, start_date, end_date) == null? 0.00 : eRekapPenjualanPerArtikelRepository.countingArtikel(id_store, start_date, end_date);
		
		return jmlTransaksi;
	}
	
	public Double totalPerArtikel(int id_store, String start_date, String end_date){
		
		Double omset = eRekapPenjualanPerArtikelRepository.totalPerArtikel(id_store, start_date, end_date) == null? 0.00 : eRekapPenjualanPerArtikelRepository.totalPerArtikel(id_store, start_date, end_date);
		
		return omset;
	}
	
	public Double countingTipe(int id_store, String start_date, String end_date){
		
		Double jmlTransaksi = eRekapPenjualanPerTipeRepository.countingTipe(id_store, start_date, end_date) == null? 0.00 : eRekapPenjualanPerArtikelRepository.countingArtikel(id_store, start_date, end_date);
		
		return jmlTransaksi;
	}
	
	public Double totalPerTipe(int id_store, String start_date, String end_date){
		
		Double omset = eRekapPenjualanPerTipeRepository.totalPerTipe(id_store, start_date, end_date) == null? 0.00 : eRekapPenjualanPerArtikelRepository.totalPerArtikel(id_store, start_date, end_date);
		
		return omset;
	}

	public List<RekapPenjualanPerKaryawan> rekapKaryawan(int id_store, String start_date, String end_date) {
		
		List<RekapPenjualanPerKaryawan> rekapKaryawan = eRekapPenjualanPerKaryawanRepository.rekapKaryawan(id_store, start_date, end_date);
		
		return rekapKaryawan;
	}
	
	public List<RekapPenjualanPerArtikel> rekapArtikel(int id_store, String start_date, String end_date) {
		
		List<RekapPenjualanPerArtikel> rekapArtikel = eRekapPenjualanPerArtikelRepository.rekapArtikel(id_store, start_date, end_date);
		
		return rekapArtikel;
	}
	
	public List<RekapPenjualanPerTipe> rekapTipe(int id_store, String start_date, String end_date) {
		
		List<RekapPenjualanPerTipe> rekapTipe = eRekapPenjualanPerTipeRepository.rekapTipe(id_store, start_date, end_date);
		
		return rekapTipe;
	}
	
	public List<RekapPenjualanPerTipe> rekapTipeTerlaris(int id_store, String start_date, String end_date) {
		
		List<RekapPenjualanPerTipe> rekapTipeTerlaris = eRekapPenjualanPerTipeRepository.rekapTipeTerlaris(id_store, start_date, end_date);
		
		return rekapTipeTerlaris;
	}
	
	public List<RekapPenjualanPerKategori> rekapkategori(int id_store, String start_date, String end_date) {
		
		List<RekapPenjualanPerKategori> rekapkategori = eRekapPenjualanPerKategoriRepository.rekapkategori(id_store, start_date, end_date);
		
		return rekapkategori;
	}
	
	public List<RekapPenjualanPerKategori> rekapkategoriTerlaris(int id_store, String start_date, String end_date) {
		
		List<RekapPenjualanPerKategori> rekapkategoriTerlaris = eRekapPenjualanPerKategoriRepository.rekapkategoriTerlaris(id_store, start_date, end_date);
		
		return rekapkategoriTerlaris;
	}
	
	public List<RekapPenjualanPerProduk> rekapProduk(int id_store, String start_date, String end_date) {
		
		List<RekapPenjualanPerProduk> rekapProduk = eRekapPenjualanPerProdukRepository.rekapProduk(id_store, start_date, end_date );
		
		return rekapProduk;
	}
	
	public List<RekapPenjualanPerProduk> rekapProdukShorted(int id_store, String start_date, String end_date, String orderBy, String sortDir) {
		String data = orderBy.replace("%28", "(");
		String last_value = data.replace("%29", ")");
		List<RekapPenjualanPerProduk> rekapProdukShorted = eRekapPenjualanPerProdukRepository.rekapProdukShorted(id_store, start_date, end_date, last_value, sortDir );
		
		return rekapProdukShorted;
	}
	
	public List<RekapPenjualanPerProduk> rekapProdukTerlaris(int id_store, String start_date, String end_date) {
		
		List<RekapPenjualanPerProduk> rekapProdukTerlaris = eRekapPenjualanPerProdukRepository.rekapProdukTerlaris(id_store, start_date, end_date);
		
		return rekapProdukTerlaris;
	}
	
	public List<RekapPenjualanPerPelanggan> rekapPelanggan(int id_store, String start_date, String end_date) {
		
		List<RekapPenjualanPerPelanggan> rekapPelanggan = eRekapPenjualanPerPelangganRepository.rekapPelanggan(id_store, start_date, end_date);
		
		return rekapPelanggan;
	}
	
	public List<Penjualan> rekapPelangganPerTanggal(int id_store, String start_date, String end_date, String no_hp_pelanggan) {
		
		List<Penjualan> rekapPelangganPerTanggal = eRepo.rekapPelangganPerTanggal(id_store, start_date, end_date, no_hp_pelanggan);
		
		return rekapPelangganPerTanggal;
	}
	
	public List<Penjualan> penjualanPerKaryawan(int id_karyawan, String start_date, String end_date) {
		
		List<Penjualan> penjualanPerKaryawan = eRepo.penjualanPerKaryawan(id_karyawan,start_date,end_date);
		
		return penjualanPerKaryawan;
	}
	
	public List<DetailPesanan> findByKaryawanId(int id_store, int id_karyawan, String start_date, String end_date) {
		
		List<DetailPesanan> rekapKaryawan = eDetailRepo.findByKaryawanId(id_store,id_karyawan, start_date, end_date);
		
		return rekapKaryawan;
	}
}
