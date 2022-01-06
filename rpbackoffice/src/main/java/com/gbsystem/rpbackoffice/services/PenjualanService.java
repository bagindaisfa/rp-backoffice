package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.Penjualan;
import com.gbsystem.rpbackoffice.entities.DetailPesanan;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.DetailPesananRepository;
import com.gbsystem.rpbackoffice.repository.PenjualanRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PenjualanService {
	
	@Autowired
	private PenjualanRepository eRepo;
	
	@Autowired
	private DetailPesananRepository eDetailRepo;
	
	@Autowired
	private StockStoreRepository eStockRepo;
	
	public Penjualan savePenjualan( Penjualan penjualan) {
		Penjualan item = new Penjualan();
		String id_transaksi = "INV-" + new SimpleDateFormat("yyMM").format(new Date()) + "-S" + (eRepo.count() + 1);
		Date tanggal_transaksi = new Date();
		List<DetailPesanan> details = new ArrayList<>();
		item.setTanggal_transaksi(tanggal_transaksi);
		item.setId_transaksi(id_transaksi);
		item.setId_store(penjualan.getId_store());
		item.setLokasi_store(penjualan.getLokasi_store());
		item.setNama_pelanggan(penjualan.getNama_pelanggan());
		item.setNama_karyawan(penjualan.getNama_karyawan());
		item.setDiskon(penjualan.getDiskon());
		item.setMetode_bayar(penjualan.getMetode_bayar());
		item.setEkspedisi(penjualan.getEkspedisi());
		item.setOngkir(penjualan.getOngkir());
		item.setTotal(penjualan.getTotal());
		item.setKembalian(penjualan.getKembalian());
		item.setRowstatus(1);
		
		for(int i = 0; i < penjualan.getDetailPesananList().size(); i++) {
			DetailPesanan d = new DetailPesanan();
			StockStore check = new StockStore();
			
			check = eStockRepo.findById_storeAndArtikel(penjualan.getDetailPesananList().get(i).getId_store(),penjualan.getDetailPesananList().get(i).getArtikel());
			check.setKuantitas(check.getKuantitas() - penjualan.getDetailPesananList().get(i).getKuantitas());
			eStockRepo.save(check);
			
			d.setTanggal_transaksi(tanggal_transaksi);
			d.setId_transaksi(id_transaksi);
			d.setId_store(penjualan.getDetailPesananList().get(i).getId_store());
			d.setLokasi_store(penjualan.getDetailPesananList().get(i).getLokasi_store());
			d.setArtikel(penjualan.getDetailPesananList().get(i).getArtikel());
			d.setNama_barang(penjualan.getDetailPesananList().get(i).getNama_barang());
			d.setHarga(penjualan.getDetailPesananList().get(i).getHarga());
			d.setKuantitas(penjualan.getDetailPesananList().get(i).getKuantitas());
			d.setTotal(penjualan.getDetailPesananList().get(i).getTotal());
			d.setRowstatus(1);
			d.setPenjualan(item);
			details.add(d);
		}
		item.setDetailPesananList(details);
		eRepo.save(item);
		
		return penjualan;
	}
	public List<Penjualan> getAllPenjualan(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<Penjualan> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public Penjualan deletePenjualanById(Long id)
    {
		Penjualan p = new Penjualan();
    	p = eRepo.findById(id).get();
    	List<DetailPesanan> details = new ArrayList<>();
    	
    	p.setTanggal_transaksi(p.getTanggal_transaksi());
		p.setId_transaksi(p.getId_transaksi());
		p.setId_store(p.getId_store());
		p.setLokasi_store(p.getLokasi_store());
		p.setDiskon(p.getDiskon());
		p.setMetode_bayar(p.getMetode_bayar());
		p.setEkspedisi(p.getEkspedisi());
		p.setOngkir(p.getOngkir());
		p.setTotal(p.getTotal());
		p.setKembalian(p.getKembalian());
		p.setNama_pelanggan(p.getNama_pelanggan());
		p.setNama_karyawan(p.getNama_karyawan());
		p.setRowstatus(0);
		for(int i = 0; i < p.getDetailPesananList().size(); i++) {
			DetailPesanan d = new DetailPesanan();
			d = eDetailRepo.getByPenjualan_id(id);
			
			StockStore prevStoreAsal = new StockStore();
	    	prevStoreAsal = eStockRepo.findById_storeAndArtikel(p.getDetailPesananList().get(i).getId_store(),p.getDetailPesananList().get(i).getArtikel());
			double prev_qty_store_asal = prevStoreAsal.getKuantitas();
			StockStore check = new StockStore();
			check = eStockRepo.findById_storeAndArtikel(p.getDetailPesananList().get(i).getId_store(),p.getDetailPesananList().get(i).getArtikel());
			check.setKuantitas(prev_qty_store_asal + p.getDetailPesananList().get(i).getKuantitas());
			eStockRepo.save(check);
			
			d.setTanggal_transaksi(p.getTanggal_transaksi());
			d.setId_transaksi(p.getId_transaksi());
			d.setId_store(p.getDetailPesananList().get(i).getId_store());
			d.setLokasi_store(p.getDetailPesananList().get(i).getLokasi_store());
			d.setArtikel(p.getDetailPesananList().get(i).getArtikel());
			d.setNama_barang(p.getDetailPesananList().get(i).getNama_barang());
			d.setHarga(p.getDetailPesananList().get(i).getHarga());
			d.setKuantitas(p.getDetailPesananList().get(i).getKuantitas());
			d.setTotal(p.getDetailPesananList().get(i).getTotal());
			d.setRowstatus(0);
			d.setPenjualan(p);
			details.add(d);
		}
    	p.setDetailPesananList(details);
    	eRepo.save(p);    
    	return eRepo.getById(id);
    }
	
	public Penjualan update( Penjualan penjualan) {
		Penjualan p = new Penjualan();
    	p = eRepo.findById(penjualan.getId()).get();
    	List<DetailPesanan> details = new ArrayList<>();
    	
    	p.setTanggal_transaksi(penjualan.getTanggal_transaksi());
		p.setId_transaksi(penjualan.getId_transaksi());
		p.setId_store(penjualan.getId_store());
		p.setLokasi_store(penjualan.getLokasi_store());
		p.setDiskon(penjualan.getDiskon());
		p.setMetode_bayar(penjualan.getMetode_bayar());
		p.setEkspedisi(penjualan.getEkspedisi());
		p.setOngkir(penjualan.getOngkir());
		p.setTotal(penjualan.getTotal());
		p.setKembalian(penjualan.getKembalian());
		p.setNama_pelanggan(penjualan.getNama_pelanggan());
		p.setNama_karyawan(penjualan.getNama_karyawan());
		p.setRowstatus(1);
		for(int i = 0; i < penjualan.getDetailPesananList().size(); i++) {
			DetailPesanan d = new DetailPesanan();
			d = p.getDetailPesananList().get(i);
			StockStore prevStoreAsal = new StockStore();
	    	prevStoreAsal = eStockRepo.findById_storeAndArtikel(penjualan.getDetailPesananList().get(i).getId_store(),penjualan.getDetailPesananList().get(i).getArtikel());
			double prev_qty_store_asal = prevStoreAsal.getKuantitas();
			
			StockStore check = new StockStore();
			check = eStockRepo.findById_storeAndArtikel(penjualan.getDetailPesananList().get(i).getId_store(),penjualan.getDetailPesananList().get(i).getArtikel());
			check.setKuantitas((prev_qty_store_asal + p.getDetailPesananList().get(i).getKuantitas()) - penjualan.getDetailPesananList().get(i).getKuantitas());
			eStockRepo.save(check);
			
			d.setTanggal_transaksi(penjualan.getTanggal_transaksi());
			d.setId_transaksi(penjualan.getId_transaksi());
			d.setId_store(penjualan.getDetailPesananList().get(i).getId_store());
			d.setLokasi_store(penjualan.getDetailPesananList().get(i).getLokasi_store());
			d.setArtikel(penjualan.getDetailPesananList().get(i).getArtikel());
			d.setNama_barang(penjualan.getDetailPesananList().get(i).getNama_barang());
			d.setHarga(penjualan.getDetailPesananList().get(i).getHarga());
			d.setKuantitas(penjualan.getDetailPesananList().get(i).getKuantitas());
			d.setTotal(penjualan.getDetailPesananList().get(i).getTotal());
			d.setRowstatus(1);
			d.setPenjualan(p);
			details.add(d);
		}
		p.setDetailPesananList(details);
    	eRepo.save(p);
    	
    	return penjualan;
	}
}
