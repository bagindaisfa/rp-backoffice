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
import com.gbsystem.rpbackoffice.repository.PenjualanOfficeRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananKeluarRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;

@Service
public class PenjualanOfficeService {
	@Autowired
	private PenjualanOfficeRepository eRepo;
	
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	
	@Autowired
	private PenyimpananKeluarRepository ePenyimpananRepo;
	
	public PenjualanOffice savePenjualanOffice(PenjualanOffice penjualanOffice) {
		
		String id_transaksi = "INV-" + new SimpleDateFormat("yyMM").format(new Date()) + "-O" + (eRepo.count() + 1);
		Date tanggal_transaksi = penjualanOffice.getTanggal_transaksi();
		
		PenjualanOffice p = new PenjualanOffice();
		List<DetailPenjualanOffice> details = new ArrayList<>();
		
		p.setTanggal_transaksi(tanggal_transaksi);
		p.setId_transaksi(id_transaksi);
		p.setId_office(penjualanOffice.getId_office());
		p.setLokasi_office(penjualanOffice.getLokasi_office());
		p.setRowstatus(1);
		for(int i = 0; i < penjualanOffice.getDetail_penjualan().size(); i++) {
			DetailPenjualanOffice d = new DetailPenjualanOffice();
			d.setTanggal_transaksi(tanggal_transaksi);
			d.setId_transaksi(id_transaksi);
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
			details.add(d);
			
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndArtikel(
					penjualanOffice.getId_office(),
					penjualanOffice.getDetail_penjualan().get(i).getArtikel(),
					penjualanOffice.getDetail_penjualan().get(i).getUkuran());
			g.setKuantitas(g.getKuantitas() - penjualanOffice.getDetail_penjualan().get(i).getKuantitas());
			eStockOfficeRepo.save(g);
			
			PenyimpananKeluar f = new PenyimpananKeluar();
			f.setPengiriman_code(id_transaksi);
			f.setTanggal_keluar(tanggal_transaksi);
			f.setId_office(penjualanOffice.getId_office());
			f.setLokasi_office(penjualanOffice.getLokasi_office());
			f.setLokasi_store("-");
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
			f.setKeterangan("Penjualan Office");
			f.setRowstatus(1);
			ePenyimpananRepo.save(f);
		}
		
		p.setDetail_penjualan(details);
		return eRepo.save(p);
	}
	
	public List<PenjualanOffice> getAllPenjualan(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<PenjualanOffice> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public PenjualanOffice deletePenjualanById(Long id)
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
					p.getDetail_penjualan().get(i).getArtikel(),
					p.getDetail_penjualan().get(i).getUkuran());
    		e.setKuantitas(e.getKuantitas() + p.getDetail_penjualan().get(i).getKuantitas());
    		eStockOfficeRepo.save(e);
    		
    	}
    	
    	p.setDetail_penjualan(details);
    	return eRepo.save(p);    
    }
	
	public PenjualanOffice update(PenjualanOffice penjualanOffice) {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(penjualanOffice.getId()).get();
    	
    	ePenyimpananRepo.deleteStockKeluar(p.getId_transaksi());
    	
		for(int i = 0; i < p.getDetail_penjualan().size(); i++) {
			
			StockOffice a = new StockOffice();
			a = eStockOfficeRepo.findById_officeAndArtikel(
					p.getId_office(),
					p.getDetail_penjualan().get(i).getArtikel(),
					p.getDetail_penjualan().get(i).getUkuran());
			a.setKuantitas(a.getKuantitas() + p.getDetail_penjualan().get(i).getKuantitas());
			eStockOfficeRepo.save(a);
				
		}
		
		return saveUpdate(penjualanOffice);
	}
	
	private PenjualanOffice saveUpdate(PenjualanOffice penjualanOffice) {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(penjualanOffice.getId()).get();
    	p.setTanggal_transaksi(penjualanOffice.getTanggal_transaksi());
		p.setId_transaksi(penjualanOffice.getId_transaksi());
		p.setId_office(penjualanOffice.getId_office());
		p.setLokasi_office(penjualanOffice.getLokasi_office());
		p.setRowstatus(1);
		
		List<DetailPenjualanOffice> details = new ArrayList<>();
		for(int k = 0; k < penjualanOffice.getDetail_penjualan().size(); k++) {
			DetailPenjualanOffice d = new DetailPenjualanOffice();
			d.setTanggal_transaksi(penjualanOffice.getTanggal_transaksi());
			d.setId_transaksi(penjualanOffice.getId_transaksi());
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
				StockOffice l = new StockOffice();
				l = eStockOfficeRepo.findById_officeAndArtikel(
						penjualanOffice.getId_office(),
						penjualanOffice.getDetail_penjualan().get(k).getArtikel(),
						penjualanOffice.getDetail_penjualan().get(k).getUkuran());
				l.setKuantitas(l.getKuantitas() - penjualanOffice.getDetail_penjualan().get(k).getKuantitas());
				eStockOfficeRepo.save(l);
			
				PenyimpananKeluar f = new PenyimpananKeluar();
				f.setPengiriman_code(penjualanOffice.getId_transaksi());
				f.setTanggal_keluar(penjualanOffice.getTanggal_transaksi());
				f.setId_office(penjualanOffice.getId_office());
				f.setLokasi_office(penjualanOffice.getLokasi_office());
				f.setLokasi_store("-");
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
				f.setKeterangan("Penjualan Office");
				f.setRowstatus(1);
				ePenyimpananRepo.save(f);
			}
		}
    	p.setDetail_penjualan(details);
    	return eRepo.save(p);
	}
}
