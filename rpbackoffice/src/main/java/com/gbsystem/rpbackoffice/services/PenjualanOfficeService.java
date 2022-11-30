package com.gbsystem.rpbackoffice.services;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPenjualanOffice;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.Pelanggan;
import com.gbsystem.rpbackoffice.entities.PenjualanOffice;
import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.DetailPenjualanOfficeRepository;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PelangganRepository;
import com.gbsystem.rpbackoffice.repository.PenjualanOfficeRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananKeluarRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;

@Service
public class PenjualanOfficeService {
	@Autowired
	private PenjualanOfficeRepository eRepo;
	
	@Autowired
	private DetailPenjualanOfficeRepository eDetailRepo;
	
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	
	@Autowired
	private PenyimpananKeluarRepository ePenyimpananRepo;
	
	@Autowired
	private MasterProductRepository eMasterProductRepository;
	
	@Autowired
	private PelangganRepository ePelangganRepo;
	
	public PenjualanOffice savePenjualanOffice(PenjualanOffice penjualanOffice) {
		
		String id_transaksi = "INV-" + new SimpleDateFormat("yyMM").format(new Date()) + "-O" + (eRepo.count() + 1);
		Date tanggal_transaksi = penjualanOffice.getTanggal_transaksi();
		double total = 0;
		PenjualanOffice p = new PenjualanOffice();
		List<DetailPenjualanOffice> details = new ArrayList<>();
		
		p.setTanggal_transaksi(tanggal_transaksi);
		p.setId_transaksi(id_transaksi);
		p.setId_office(penjualanOffice.getId_office());
		p.setLokasi_office(penjualanOffice.getLokasi_office());
		p.setNo_hp_pelanggan(penjualanOffice.getNo_hp_pelanggan());
		p.setNama_pelanggan(penjualanOffice.getNama_pelanggan());
		p.setId_karyawan(penjualanOffice.getId_karyawan());
		p.setNama_karyawan(penjualanOffice.getNama_karyawan());
		p.setDiskon((penjualanOffice.getDiskon() == null ? 0.0 : penjualanOffice.getDiskon()));
		p.setDiskon_remark(penjualanOffice.getDiskon_remark());
		p.setOngkos_kirim((penjualanOffice.getOngkos_kirim() == null ? 0.0 : penjualanOffice.getOngkos_kirim()));
		p.setEkspedisi(penjualanOffice.getEkspedisi());
		p.setPajak_biaya(penjualanOffice.getPajak_biaya() == null ? 0.0 : penjualanOffice.getPajak_biaya());
		p.setMetode_pembayaran(penjualanOffice.getMetode_pembayaran());
		p.setBank_name(penjualanOffice.getBank_name());
		p.setNo_rek(penjualanOffice.getNo_rek());
		
		for(int i = 0; i < penjualanOffice.getDetail_penjualan().size(); i++) {
			DetailPenjualanOffice d = new DetailPenjualanOffice();
			
			MasterProduct prod = new MasterProduct();
			prod = eMasterProductRepository.findByArticle(penjualanOffice.getDetail_penjualan().get(i).getArtikel());
			
			d.setTanggal_transaksi(tanggal_transaksi);
			d.setId_transaksi(id_transaksi);
			d.setSku_code(penjualanOffice.getDetail_penjualan().get(i).getSku_code());
			d.setArtikel(penjualanOffice.getDetail_penjualan().get(i).getArtikel());
			d.setKategori(prod == null ? "" : prod.getKategori());
			d.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
			d.setType(prod == null ? 0 : prod.getType());
			d.setType_name(prod == null ? "" : prod.getType_name());
			d.setNama_barang(prod == null ? "" : prod.getNama_product());
			d.setKuantitas(penjualanOffice.getDetail_penjualan().get(i).getKuantitas());
			d.setHarga_satuan_barang(prod == null ? 0 : prod.getHarga_jual());
			d.setTotal(penjualanOffice.getDetail_penjualan().get(i).getKuantitas() * (prod == null ? 0 : prod.getHarga_jual()));
			d.setRowstatus(1);
			d.setPenjualanOffice(p);
			total += (penjualanOffice.getDetail_penjualan().get(i).getKuantitas() * (prod == null ? 0 : prod.getHarga_jual()));
			details.add(d);
			
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndArtikel(
					penjualanOffice.getId_office(),
					penjualanOffice.getDetail_penjualan().get(i).getArtikel());
			g.setKuantitas(g.getKuantitas() - penjualanOffice.getDetail_penjualan().get(i).getKuantitas());
			eStockOfficeRepo.save(g);
			
			PenyimpananKeluar f = new PenyimpananKeluar();
			f.setPengiriman_code(id_transaksi);
			f.setTanggal_keluar(tanggal_transaksi);
			f.setId_office(penjualanOffice.getId_office());
			f.setLokasi_office(penjualanOffice.getLokasi_office());
			f.setLokasi_store("-");
			f.setSku_code(penjualanOffice.getDetail_penjualan().get(i).getSku_code());
			f.setArtikel(penjualanOffice.getDetail_penjualan().get(i).getArtikel());
			f.setKategori(prod == null ? "" : prod.getKategori());
			f.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
			f.setType(prod == null ? 0 : prod.getType());
			f.setType_name(prod == null ? "" : prod.getType_name());
			f.setNama_barang(prod == null ? "" : prod.getNama_product());
			f.setKuantitas(penjualanOffice.getDetail_penjualan().get(i).getKuantitas());
			f.setUkuran(prod == null ? "" : prod.getUkuran());
			f.setHpp(prod == null ? 0 : prod.getHpp());
			f.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
			f.setKeterangan("Penjualan Office ke pelanggan " + penjualanOffice.getNama_pelanggan());
			f.setRowstatus(1);
			ePenyimpananRepo.save(f);
		}
		Double pajak = penjualanOffice.getPajak_biaya() == null ? 0.0 : penjualanOffice.getPajak_biaya();
		Double diskon = penjualanOffice.getDiskon() == null ? 0.0 : penjualanOffice.getDiskon();
		Double total_penjualan = 0.0;
		
		if (diskon > 100.0) {
			if (diskon > 0.0 && pajak > 0.0) {
				Double total_new = total - diskon;
				total_penjualan =total_new + (total_new * (pajak / 100));
				
			} else if (diskon > 0.0 && pajak <= 0.0) {
				total_penjualan =total - diskon;
			} else if (diskon <= 0.0 && pajak > 0.0) {
				total_penjualan =total + (total * (pajak / 100));
			} else {
				total_penjualan =total;
			}
		} else if (diskon <= 100.0 && diskon > 0.0) {
			if (diskon > 0.0 && pajak > 0.0) {
				Double total_new = total - (total * (diskon / 100));
				total_penjualan =total_new + (total_new * (pajak / 100));
				
			} else if (diskon > 0.0 && pajak <= 0.0) {
				total_penjualan =total - (total * (diskon / 100));
			} else if (diskon <= 0.0 && pajak > 0.0) {
				total_penjualan =total + (total * (pajak / 100));
			} else {
				total_penjualan =total;
			}
		}
		
		p.setTotal_penjualan(total_penjualan + (penjualanOffice.getOngkos_kirim() == null ? 0.0 : penjualanOffice.getOngkos_kirim()));
		
		p.setRowstatus(1);
		p.setDetail_penjualan(details);
		
		if (total_penjualan > 1000000) {
			List<Pelanggan> pembeli = new ArrayList<>();
			pembeli = ePelangganRepo.findByNoHp(p.getNo_hp_pelanggan());
			for (int i = 0; i < pembeli.size(); i++) {

			    LocalDate now = LocalDate.now();
			    LocalDate firstDay = now.with(firstDayOfYear());
			    
			    if (now != firstDay) {
			    	pembeli.get(i).setPoin(pembeli.get(i).getPoin() + (total * 0.001));
			    	pembeli.get(i).setTotal_pembelian(pembeli.get(i).getTotal_pembelian() + total);
			    	pembeli.get(i).setTotal_kunjungan(pembeli.get(i).getTotal_kunjungan() + 1);
					ePelangganRepo.save(pembeli.get(i));
			    } else {
			    	pembeli.get(i).setPoin(total * 0.001);
			    	pembeli.get(i).setTotal_pembelian(pembeli.get(i).getTotal_pembelian() + total);
			    	pembeli.get(i).setTotal_kunjungan(pembeli.get(i).getTotal_kunjungan() + 1);
					ePelangganRepo.save(pembeli.get(i));
			    }
			}
		}
		
		return eRepo.save(p);
	}
	
	public List<PenjualanOffice> getAllPenjualan(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<PenjualanOffice> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public String deletePenjualanById(Long id)
    {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	
    	List<PenyimpananKeluar> f = new ArrayList<>();
		f = ePenyimpananRepo.findByPengiriman_code(p.getId_transaksi());
		for(int j = 0; j < f.size(); j++) {
			f.get(j).setRowstatus(0);
        	ePenyimpananRepo.save(f.get(j));
		} 
		
		List<DetailPenjualanOffice> details = new ArrayList<>();
    	for(int i = 0; i < p.getDetail_penjualan().size(); i++) {
    		p.getDetail_penjualan().get(i).setRowstatus(0);
    		p.getDetail_penjualan().get(i).setPenjualanOffice(p);
			details.add(p.getDetail_penjualan().get(i));
    		
    		StockOffice e = new StockOffice();
    		eStockOfficeRepo.findById_officeAndArtikel(
    				p.getId_office(),
					p.getDetail_penjualan().get(i).getArtikel());
    		e.setKuantitas(e.getKuantitas() + p.getDetail_penjualan().get(i).getKuantitas());
    		eStockOfficeRepo.save(e);
    		
    	}
    	
    	p.setDetail_penjualan(details);
    	eRepo.save(p);
    	
    	if (p.getTotal_penjualan() > 1000000) {
			List<Pelanggan> pembeli = new ArrayList<>();
			pembeli = ePelangganRepo.findByNoHp(p.getNo_hp_pelanggan());
			for (int i = 0; i < pembeli.size(); i++) {
				pembeli.get(i).setPoin(pembeli.get(i).getPoin() - (p.getTotal_penjualan() * 0.01));
				ePelangganRepo.save(pembeli.get(i));	
			}
		}
    	
    	return "Berhasil didelete!";
    }
	
	public PenjualanOffice update(PenjualanOffice penjualanOffice) {
		double total = 0;
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(penjualanOffice.getId()).get();
    	
    	p.setTanggal_transaksi(penjualanOffice.getTanggal_transaksi());
    	p.setId_office(penjualanOffice.getId_office());
		p.setLokasi_office(penjualanOffice.getLokasi_office());
		p.setNo_hp_pelanggan(penjualanOffice.getNo_hp_pelanggan());
		p.setNama_pelanggan(penjualanOffice.getNama_pelanggan());
		p.setId_karyawan(penjualanOffice.getId_karyawan());
		p.setNama_karyawan(penjualanOffice.getNama_karyawan());
		p.setDiskon((penjualanOffice.getDiskon() == null ? 0.0 : penjualanOffice.getDiskon()));
		p.setDiskon_remark(penjualanOffice.getDiskon_remark());
		p.setOngkos_kirim((penjualanOffice.getOngkos_kirim() == null ? 0.0 : penjualanOffice.getOngkos_kirim()));
		p.setEkspedisi(penjualanOffice.getEkspedisi());
		p.setPajak_biaya((penjualanOffice.getPajak_biaya() == null ? 0.0 : penjualanOffice.getPajak_biaya()));
		p.setMetode_pembayaran(penjualanOffice.getMetode_pembayaran());
		p.setBank_name(penjualanOffice.getBank_name());
		p.setNo_rek(penjualanOffice.getNo_rek());
		p.setRowstatus(penjualanOffice.getRowstatus());
		
		List<DetailPenjualanOffice> details = new ArrayList<>();
		for(int k = 0; k < penjualanOffice.getDetail_penjualan().size(); k++) {
			DetailPenjualanOffice d = new DetailPenjualanOffice();
			
			MasterProduct prod = new MasterProduct();
			prod = eMasterProductRepository.findByArticle(penjualanOffice.getDetail_penjualan().get(k).getArtikel());
			
			if (penjualanOffice.getDetail_penjualan().get(k).getId() == null) {
				d = null;
			} else {
				d = eDetailRepo.findById(penjualanOffice.getDetail_penjualan().get(k).getId()).orElse(null);
			}
			
			if (d != null) {
				d.setTanggal_transaksi(penjualanOffice.getTanggal_transaksi());
				d.setArtikel(penjualanOffice.getDetail_penjualan().get(k).getArtikel());
				d.setSku_code(penjualanOffice.getDetail_penjualan().get(k).getSku_code());
				d.setKategori(prod == null ? "" : prod.getKategori());
				d.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
				d.setType(prod == null ? 0 : prod.getType());
				d.setType_name(prod == null ? "" : prod.getType_name());
				d.setNama_barang(prod == null ? "" : prod.getNama_product());
				d.setKuantitas(penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
				d.setHarga_satuan_barang(penjualanOffice.getDetail_penjualan().get(k).getHarga_satuan_barang());
				d.setTotal(penjualanOffice.getDetail_penjualan().get(k).getTotal());
				d.setRowstatus(penjualanOffice.getDetail_penjualan().get(k).getRowstatus());
				d.setPenjualanOffice(p);
				details.add(d);
				if (penjualanOffice.getDetail_penjualan().get(k).getRowstatus() == 1) {
					total += (penjualanOffice.getDetail_penjualan().get(k).getKuantitas() * penjualanOffice.getDetail_penjualan().get(k).getHarga_satuan_barang());
					
					StockOffice l = new StockOffice();
					l = eStockOfficeRepo.findById_officeAndArtikel(
							penjualanOffice.getId_office(),
							penjualanOffice.getDetail_penjualan().get(k).getArtikel());
					l.setKuantitas(l.getKuantitas() - penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
					eStockOfficeRepo.save(l);
				
					PenyimpananKeluar f = new PenyimpananKeluar();
					f = ePenyimpananRepo.getPenyimpananByPengirimanCodeandArtikel(
							penjualanOffice.getId_transaksi(),
							penjualanOffice.getDetail_penjualan().get(k).getArtikel()
							);
					if (f != null) {
						f.setPengiriman_code(p.getId_transaksi());
						f.setTanggal_keluar(penjualanOffice.getTanggal_transaksi());
						f.setId_office(penjualanOffice.getId_office());
						f.setLokasi_office(penjualanOffice.getLokasi_office());
						f.setLokasi_store("-");
						f.setSku_code(penjualanOffice.getDetail_penjualan().get(k).getSku_code());
						f.setArtikel(penjualanOffice.getDetail_penjualan().get(k).getArtikel());
						f.setKategori(prod == null ? "" : prod.getKategori());
						f.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
						f.setType(prod == null ? 0 : prod.getType());
						f.setType_name(prod == null ? "" : prod.getType_name());
						f.setNama_barang(prod == null ? "" : prod.getNama_product());
						f.setKuantitas(penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
						f.setUkuran(prod == null ? "" : prod.getUkuran());
						f.setHpp(prod == null ? 0 : prod.getHpp());
						f.setHarga_jual(penjualanOffice.getDetail_penjualan().get(k).getHarga_satuan_barang());
						f.setKeterangan("Penjualan Office ke pelanggan" + penjualanOffice.getNama_pelanggan());
						f.setRowstatus(1);
						ePenyimpananRepo.save(f);
					} else {
						PenyimpananKeluar newitem = new PenyimpananKeluar();
						newitem.setPengiriman_code(p.getId_transaksi());
						newitem.setTanggal_keluar(penjualanOffice.getTanggal_transaksi());
						newitem.setId_office(penjualanOffice.getId_office());
						newitem.setLokasi_office(penjualanOffice.getLokasi_office());
						newitem.setLokasi_store("-");
						newitem.setSku_code(penjualanOffice.getDetail_penjualan().get(k).getSku_code());
						newitem.setArtikel(penjualanOffice.getDetail_penjualan().get(k).getArtikel());
						newitem.setKategori(prod == null ? "" : prod.getKategori());
						newitem.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
						newitem.setType(prod == null ? 0 : prod.getType());
						newitem.setType_name(prod == null ? "" : prod.getType_name());
						newitem.setNama_barang(prod == null ? "" : prod.getNama_product());
						newitem.setKuantitas(penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
						newitem.setUkuran(prod == null ? "" : prod.getUkuran());
						newitem.setHpp(prod == null ? 0 : prod.getHpp());
						newitem.setHarga_jual(penjualanOffice.getDetail_penjualan().get(k).getHarga_satuan_barang());
						newitem.setKeterangan("Penjualan Office ke pelanggan" + penjualanOffice.getNama_pelanggan());
						newitem.setRowstatus(1);
						ePenyimpananRepo.save(newitem);
					}
					
				} else {
					total -= (penjualanOffice.getDetail_penjualan().get(k).getKuantitas() * penjualanOffice.getDetail_penjualan().get(k).getHarga_satuan_barang());
					
					StockOffice l = new StockOffice();
					l = eStockOfficeRepo.findById_officeAndArtikel(
							penjualanOffice.getId_office(),
							penjualanOffice.getDetail_penjualan().get(k).getArtikel());
					l.setKuantitas(l.getKuantitas() + penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
					eStockOfficeRepo.save(l);
				
					PenyimpananKeluar f = new PenyimpananKeluar();
					f = ePenyimpananRepo.getPenyimpananByPengirimanCodeandArtikel(
							penjualanOffice.getId_transaksi(),
							penjualanOffice.getDetail_penjualan().get(k).getArtikel()
							);
					f.setRowstatus(0);
					ePenyimpananRepo.save(f);
				}
			} else {
				DetailPenjualanOffice new_insert = new DetailPenjualanOffice();
				new_insert.setId_transaksi(p.getId_transaksi());
				new_insert.setTanggal_transaksi(penjualanOffice.getTanggal_transaksi());
				new_insert.setSku_code(penjualanOffice.getDetail_penjualan().get(k).getSku_code());
				new_insert.setArtikel(penjualanOffice.getDetail_penjualan().get(k).getArtikel());
				new_insert.setKategori(prod == null ? "" : prod.getKategori());
				new_insert.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
				new_insert.setType(prod == null ? 0 : prod.getType());
				new_insert.setType_name(prod == null ? "" : prod.getType_name());
				new_insert.setNama_barang(prod == null ? "" : prod.getNama_product());
				new_insert.setKuantitas(penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
				new_insert.setHarga_satuan_barang(penjualanOffice.getDetail_penjualan().get(k).getHarga_satuan_barang());
				new_insert.setTotal(penjualanOffice.getDetail_penjualan().get(k).getTotal());
				new_insert.setRowstatus(1);
				new_insert.setPenjualanOffice(p);
				total += (penjualanOffice.getDetail_penjualan().get(k).getKuantitas() * penjualanOffice.getDetail_penjualan().get(k).getHarga_satuan_barang());
				details.add(new_insert);
				
				StockOffice g = new StockOffice();
				g = eStockOfficeRepo.findById_officeAndArtikel(
						penjualanOffice.getId_office(),
						penjualanOffice.getDetail_penjualan().get(k).getArtikel());
				g.setKuantitas(g.getKuantitas() - penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
				eStockOfficeRepo.save(g);
				
				PenyimpananKeluar f = new PenyimpananKeluar();
				f.setPengiriman_code(p.getId_transaksi());
				f.setTanggal_keluar(penjualanOffice.getTanggal_transaksi());
				f.setId_office(penjualanOffice.getId_office());
				f.setLokasi_office(penjualanOffice.getLokasi_office());
				f.setLokasi_store("-");
				f.setSku_code(penjualanOffice.getDetail_penjualan().get(k).getSku_code());
				f.setArtikel(penjualanOffice.getDetail_penjualan().get(k).getArtikel());
				f.setKategori(prod == null ? "" : prod.getKategori());
				f.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
				f.setType(prod == null ? 0 : prod.getType());
				f.setType_name(prod == null ? "" : prod.getType_name());
				f.setNama_barang(prod == null ? "" : prod.getNama_product());
				f.setKuantitas(penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
				f.setUkuran(prod == null ? "" : prod.getUkuran());
				f.setHpp(prod == null ? 0 : prod.getHpp());
				f.setHarga_jual(penjualanOffice.getDetail_penjualan().get(k).getHarga_satuan_barang());
				f.setKeterangan("Penjualan Office ke pelanggan " + penjualanOffice.getNama_pelanggan());
				f.setRowstatus(1);
				ePenyimpananRepo.save(f);
			}
			
		}
		Double pajak = penjualanOffice.getPajak_biaya() == null ? 0.0 : penjualanOffice.getPajak_biaya();
		Double diskon = penjualanOffice.getDiskon() == null ? 0.0 : penjualanOffice.getDiskon();
		Double total_penjualan = 0.0;
		
		if (diskon > 100.0) {
			if (diskon > 0.0 && pajak > 0.0) {
				Double total_new = total - diskon;
				total_penjualan =total_new + (total_new * (pajak / 100));
				
			} else if (diskon > 0.0 && pajak <= 0.0) {
				total_penjualan =total - diskon;
			} else if (diskon <= 0.0 && pajak > 0.0) {
				total_penjualan =total + (total * (pajak / 100));
			} else {
				total_penjualan =total;
			}
		} else if (diskon <= 100.0 && diskon > 0.0) {
			if (diskon > 0.0 && pajak > 0.0) {
				Double total_new = total - (total * (diskon / 100));
				total_penjualan =total_new + (total_new * (pajak / 100));
				
			} else if (diskon > 0.0 && pajak <= 0.0) {
				total_penjualan =total - (total * (diskon / 100));
			} else if (diskon <= 0.0 && pajak > 0.0) {
				total_penjualan =total + (total * (pajak / 100));
			} else {
				total_penjualan =total;
			}
		}
		
		
		p.setTotal_penjualan(total_penjualan + (penjualanOffice.getOngkos_kirim() == null ? 0.0 : penjualanOffice.getOngkos_kirim()));
		p.setRowstatus(1);
    	p.setDetail_penjualan(details);
    	return eRepo.save(p);
	}
	
}
