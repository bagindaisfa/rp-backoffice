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
import com.gbsystem.rpbackoffice.repository.PenjualanRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PenjualanService {
	
	@Autowired
	private PenjualanRepository eRepo;
	
	@Autowired
	private StockStoreRepository eStockRepo;
	
	public Penjualan savePenjualan( Penjualan penjualan) {
		
		String id_transaksi = "INV-" + new SimpleDateFormat("yyMM").format(new Date()) + "-S" + (eRepo.count() + 1);
		Date tanggal_transaksi = new Date();
		List<DetailPesanan> details = new ArrayList<>();
		penjualan.setTanggal_transaksi(tanggal_transaksi);
		penjualan.setId_transaksi(id_transaksi);
		penjualan.setRowstatus(1);
		
		for(int i = 0; i < penjualan.getDetailPesananList().size(); i++) {
			DetailPesanan d = new DetailPesanan();
			StockStore check = new StockStore();
			
			check = eStockRepo.findById_storeAndArtikel(penjualan.getDetailPesananList().get(i).getId_store(),penjualan.getDetailPesananList().get(i).getArtikel()).get(0);
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
			d.setPenjualan(penjualan);
			details.add(d);
		}
		penjualan.setDetailPesananList(details);
		eRepo.save(penjualan);
		
		return penjualan;
	}
	public List<Penjualan> getAllPenjualan(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<Penjualan> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public void deletePenjualanById(Long id)
    {
		Penjualan p = new Penjualan();
		List<DetailPesanan> details = new ArrayList<>();
    	p = eRepo.findById(id).get();
    	for(int i = 0; i < p.getDetailPesananList().size(); i++) {
			DetailPesanan d = new DetailPesanan();
			
			StockStore prevStoreAsal = new StockStore();
	    	prevStoreAsal = eStockRepo.findById_storeAndArtikel(p.getDetailPesananList().get(i).getId_store(),p.getDetailPesananList().get(i).getArtikel()).get(0);
			double prev_qty_store_asal = prevStoreAsal.getKuantitas();
			StockStore check = new StockStore();
			check = eStockRepo.findById_storeAndArtikel(p.getDetailPesananList().get(i).getId_store(),p.getDetailPesananList().get(i).getArtikel()).get(0);
			check.setKuantitas(prev_qty_store_asal + p.getDetailPesananList().get(i).getKuantitas());
			eStockRepo.save(check);
			
			d.setRowstatus(0);
			details.add(d);
		}
    	p.setRowstatus(0);
    	p.setDetailPesananList(details);
    	eRepo.save(p);    
    }
	
	public Penjualan update( Penjualan penjualan) {
		Penjualan p = new Penjualan();
    	p = eRepo.findById(penjualan.getId()).get();
    	List<DetailPesanan> details = new ArrayList<>();
    	
    	p.setTanggal_transaksi(new Date());
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
			
			StockStore prevStoreAsal = new StockStore();
	    	prevStoreAsal = eStockRepo.findById_storeAndArtikel(penjualan.getDetailPesananList().get(i).getId_store(),penjualan.getDetailPesananList().get(i).getArtikel()).get(0);
			double prev_qty_store_asal = prevStoreAsal.getKuantitas();
			StockStore check = new StockStore();
			check = eStockRepo.findById_storeAndArtikel(penjualan.getDetailPesananList().get(i).getId_store(),penjualan.getDetailPesananList().get(i).getArtikel()).get(0);
			check.setKuantitas((prev_qty_store_asal + p.getDetailPesananList().get(i).getKuantitas()) - penjualan.getDetailPesananList().get(i).getKuantitas());
			eStockRepo.save(check);
			
			d.setTanggal_transaksi(new Date());
			d.setId_transaksi(p.getDetailPesananList().get(i).getId_transaksi());
			d.setId_store(penjualan.getDetailPesananList().get(i).getId_store());
			d.setLokasi_store(penjualan.getDetailPesananList().get(i).getLokasi_store());
			d.setArtikel(penjualan.getDetailPesananList().get(i).getArtikel());
			d.setNama_barang(penjualan.getDetailPesananList().get(i).getNama_barang());
			d.setHarga(penjualan.getDetailPesananList().get(i).getHarga());
			d.setKuantitas(penjualan.getDetailPesananList().get(i).getKuantitas());
			d.setTotal(penjualan.getDetailPesananList().get(i).getTotal());
			d.setRowstatus(1);
			d.setPenjualan(penjualan);
			details.add(d);
		}
		p.setDetailPesananList(details);
    	eRepo.save(p);
    	
    	return penjualan;
	}
}
