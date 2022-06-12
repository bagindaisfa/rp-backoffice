package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPenjualanOffice;
import com.gbsystem.rpbackoffice.entities.PenjualanOffice;
import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.DetailPenjualanOfficeRepository;
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
		
		for(int i = 0; i < penjualanOffice.getDetail_penjualan().size(); i++) {
			DetailPenjualanOffice d = new DetailPenjualanOffice();
			d.setTanggal_transaksi(tanggal_transaksi);
			d.setId_transaksi(id_transaksi);
			d.setSku_code(penjualanOffice.getDetail_penjualan().get(i).getSku_code());
			d.setArtikel(penjualanOffice.getDetail_penjualan().get(i).getArtikel());
			d.setKategori(penjualanOffice.getDetail_penjualan().get(i).getKategori());
			d.setNama_kategori(penjualanOffice.getDetail_penjualan().get(i).getNama_kategori());
			d.setType(penjualanOffice.getDetail_penjualan().get(i).getType());
			d.setType_name(penjualanOffice.getDetail_penjualan().get(i).getType_name());
			d.setNama_barang(penjualanOffice.getDetail_penjualan().get(i).getNama_barang());
			d.setKuantitas(penjualanOffice.getDetail_penjualan().get(i).getKuantitas());
			d.setUkuran(penjualanOffice.getDetail_penjualan().get(i).getUkuran());
			d.setMetode_pembayaran(penjualanOffice.getDetail_penjualan().get(i).getMetode_pembayaran());
			d.setHarga_satuan_barang(penjualanOffice.getDetail_penjualan().get(i).getHarga_satuan_barang());
			d.setOngkos_kirim(penjualanOffice.getDetail_penjualan().get(i).getOngkos_kirim());
			d.setPajak_biaya(penjualanOffice.getDetail_penjualan().get(i).getPajak_biaya());
			d.setTotal(penjualanOffice.getDetail_penjualan().get(i).getTotal());
			d.setRowstatus(1);
			d.setPenjualanOffice(p);
			total += penjualanOffice.getDetail_penjualan().get(i).getTotal();
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
			f.setKategori(penjualanOffice.getDetail_penjualan().get(i).getKategori());
			f.setNama_kategori(penjualanOffice.getDetail_penjualan().get(i).getNama_kategori());
			f.setType(penjualanOffice.getDetail_penjualan().get(i).getType());
			f.setType_name(penjualanOffice.getDetail_penjualan().get(i).getType_name());
			f.setNama_barang(penjualanOffice.getDetail_penjualan().get(i).getNama_barang());
			f.setKuantitas(penjualanOffice.getDetail_penjualan().get(i).getKuantitas());
			f.setUkuran(penjualanOffice.getDetail_penjualan().get(i).getUkuran());
			f.setHpp(g.getHpp());
			f.setHarga_jual(g.getHarga_jual());
			f.setKeterangan("Penjualan Office ke pelanggan " + penjualanOffice.getNama_pelanggan());
			f.setRowstatus(1);
			ePenyimpananRepo.save(f);
		}
		p.setTotal_penjualan(total);
		p.setRowstatus(1);
		p.setDetail_penjualan(details);
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
    	return "Berhasil didelete!";
    }
	
	public PenjualanOffice update(PenjualanOffice penjualanOffice) {
		double total = 0;
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(penjualanOffice.getId()).get();
    	p.setTanggal_transaksi(penjualanOffice.getTanggal_transaksi());
		p.setId_transaksi(penjualanOffice.getId_transaksi());
		p.setId_office(penjualanOffice.getId_office());
		p.setLokasi_office(penjualanOffice.getLokasi_office());
		p.setNo_hp_pelanggan(penjualanOffice.getNo_hp_pelanggan());
		p.setNama_pelanggan(penjualanOffice.getNama_pelanggan());
		p.setRowstatus(penjualanOffice.getRowstatus());
		
		List<DetailPenjualanOffice> details = new ArrayList<>();
		for(int k = 0; k < penjualanOffice.getDetail_penjualan().size(); k++) {
			DetailPenjualanOffice d = new DetailPenjualanOffice();
			
			if (penjualanOffice.getDetail_penjualan().get(k).getId() == null) {
				d = null;
			} else {
				d = eDetailRepo.getById(penjualanOffice.getDetail_penjualan().get(k).getId());
			}
			
			if (d != null) {
				d.setTanggal_transaksi(penjualanOffice.getTanggal_transaksi());
				d.setId_transaksi(penjualanOffice.getId_transaksi());
				d.setArtikel(penjualanOffice.getDetail_penjualan().get(k).getArtikel());
				d.setSku_code(penjualanOffice.getDetail_penjualan().get(k).getSku_code());
				d.setKategori(penjualanOffice.getDetail_penjualan().get(k).getKategori());
				d.setNama_kategori(penjualanOffice.getDetail_penjualan().get(k).getNama_kategori());
				d.setType(penjualanOffice.getDetail_penjualan().get(k).getType());
				d.setType_name(penjualanOffice.getDetail_penjualan().get(k).getType_name());
				d.setKategori(penjualanOffice.getDetail_penjualan().get(k).getKategori());
				d.setNama_barang(penjualanOffice.getDetail_penjualan().get(k).getNama_barang());
				d.setKuantitas(penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
				d.setUkuran(penjualanOffice.getDetail_penjualan().get(k).getUkuran());
				d.setMetode_pembayaran(penjualanOffice.getDetail_penjualan().get(k).getMetode_pembayaran());
				d.setHarga_satuan_barang(penjualanOffice.getDetail_penjualan().get(k).getHarga_satuan_barang());
				d.setOngkos_kirim(penjualanOffice.getDetail_penjualan().get(k).getOngkos_kirim());
				d.setPajak_biaya(penjualanOffice.getDetail_penjualan().get(k).getPajak_biaya());
				d.setTotal(penjualanOffice.getDetail_penjualan().get(k).getTotal());
				d.setRowstatus(penjualanOffice.getDetail_penjualan().get(k).getRowstatus());
				d.setPenjualanOffice(p);
				details.add(d);
				if (penjualanOffice.getDetail_penjualan().get(k).getRowstatus() == 1) {
					total += penjualanOffice.getDetail_penjualan().get(k).getTotal();
					
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
					f.setPengiriman_code(penjualanOffice.getId_transaksi());
					f.setTanggal_keluar(penjualanOffice.getTanggal_transaksi());
					f.setId_office(penjualanOffice.getId_office());
					f.setLokasi_office(penjualanOffice.getLokasi_office());
					f.setLokasi_store("-");
					f.setSku_code(penjualanOffice.getDetail_penjualan().get(k).getSku_code());
					f.setArtikel(penjualanOffice.getDetail_penjualan().get(k).getArtikel());
					f.setKategori(penjualanOffice.getDetail_penjualan().get(k).getKategori());
					f.setNama_kategori(penjualanOffice.getDetail_penjualan().get(k).getNama_kategori());
					f.setType(penjualanOffice.getDetail_penjualan().get(k).getType());
					f.setType_name(penjualanOffice.getDetail_penjualan().get(k).getType_name());
					f.setNama_barang(penjualanOffice.getDetail_penjualan().get(k).getNama_barang());
					f.setKuantitas(penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
					f.setUkuran(penjualanOffice.getDetail_penjualan().get(k).getUkuran());
					f.setHpp(l.getHpp());
					f.setHarga_jual(l.getHarga_jual());
					f.setKeterangan("Penjualan Office ke pelanggan" + penjualanOffice.getNama_pelanggan());
					f.setRowstatus(1);
					ePenyimpananRepo.save(f);
				} else {
					total -= penjualanOffice.getDetail_penjualan().get(k).getTotal();
					
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
				new_insert.setId_transaksi(penjualanOffice.getId_transaksi());
				new_insert.setTanggal_transaksi(penjualanOffice.getTanggal_transaksi());
				new_insert.setSku_code(penjualanOffice.getDetail_penjualan().get(k).getSku_code());
				new_insert.setArtikel(penjualanOffice.getDetail_penjualan().get(k).getArtikel());
				new_insert.setKategori(penjualanOffice.getDetail_penjualan().get(k).getKategori());
				new_insert.setNama_kategori(penjualanOffice.getDetail_penjualan().get(k).getNama_kategori());
				new_insert.setType(penjualanOffice.getDetail_penjualan().get(k).getType());
				new_insert.setType_name(penjualanOffice.getDetail_penjualan().get(k).getType_name());
				new_insert.setNama_barang(penjualanOffice.getDetail_penjualan().get(k).getNama_barang());
				new_insert.setKuantitas(penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
				new_insert.setUkuran(penjualanOffice.getDetail_penjualan().get(k).getUkuran());
				new_insert.setMetode_pembayaran(penjualanOffice.getDetail_penjualan().get(k).getMetode_pembayaran());
				new_insert.setHarga_satuan_barang(penjualanOffice.getDetail_penjualan().get(k).getHarga_satuan_barang());
				new_insert.setOngkos_kirim(penjualanOffice.getDetail_penjualan().get(k).getOngkos_kirim());
				new_insert.setPajak_biaya(penjualanOffice.getDetail_penjualan().get(k).getPajak_biaya());
				new_insert.setTotal(penjualanOffice.getDetail_penjualan().get(k).getTotal());
				new_insert.setRowstatus(1);
				new_insert.setPenjualanOffice(p);
				total += penjualanOffice.getDetail_penjualan().get(k).getTotal();
				details.add(d);
				
				StockOffice g = new StockOffice();
				g = eStockOfficeRepo.findById_officeAndArtikel(
						penjualanOffice.getId_office(),
						penjualanOffice.getDetail_penjualan().get(k).getArtikel());
				g.setKuantitas(g.getKuantitas() - penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
				eStockOfficeRepo.save(g);
				
				PenyimpananKeluar f = new PenyimpananKeluar();
				f.setPengiriman_code(penjualanOffice.getId_transaksi());
				f.setTanggal_keluar(penjualanOffice.getTanggal_transaksi());
				f.setId_office(penjualanOffice.getId_office());
				f.setLokasi_office(penjualanOffice.getLokasi_office());
				f.setLokasi_store("-");
				f.setSku_code(penjualanOffice.getDetail_penjualan().get(k).getSku_code());
				f.setArtikel(penjualanOffice.getDetail_penjualan().get(k).getArtikel());
				f.setKategori(penjualanOffice.getDetail_penjualan().get(k).getKategori());
				f.setNama_kategori(penjualanOffice.getDetail_penjualan().get(k).getNama_kategori());
				f.setType(penjualanOffice.getDetail_penjualan().get(k).getType());
				f.setType_name(penjualanOffice.getDetail_penjualan().get(k).getType_name());
				f.setNama_barang(penjualanOffice.getDetail_penjualan().get(k).getNama_barang());
				f.setKuantitas(penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
				f.setUkuran(penjualanOffice.getDetail_penjualan().get(k).getUkuran());
				f.setHpp(g.getHpp());
				f.setHarga_jual(g.getHarga_jual());
				f.setKeterangan("Penjualan Office ke pelanggan " + penjualanOffice.getNama_pelanggan());
				f.setRowstatus(1);
				ePenyimpananRepo.save(f);
			}
			
		}
		p.setTotal_penjualan(total);
		p.setRowstatus(1);
    	p.setDetail_penjualan(details);
    	return eRepo.save(p);
	}
	
}
