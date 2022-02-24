package com.gbsystem.rpbackoffice.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PenerimaanStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.entities.ReturGudang;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.PenerimaanStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.ReturGudangRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;

@Service
public class PenerimaanStoreService {
	
	@Autowired
	private PenerimaanStoreRepository eRepo;
	
	@Autowired
	private StockOfficeRepository eStockRepo;
	
	@Autowired
	private PenyimpananMasukRepository ePenyimpananRepo;
	
	@Autowired
	private ReturGudangRepository eReturRepo;
	
	public String savePenerimaanStore(String penerimaan_code) {
		
		ReturGudang get_retur = new ReturGudang();
		get_retur = eReturRepo.findByPengiriman_code(penerimaan_code);
		for (int i = 0; i < get_retur.getDetail_pengiriman().size(); i++) {
			PenerimaanStore p = new PenerimaanStore();
			
			StockOffice d = new StockOffice();
			d = eStockRepo.findById_officeAndSku_code(
					get_retur.getId_office_tujuan(),
					get_retur.getDetail_pengiriman().get(i).getSku_code());
			d.setKuantitas(d.getKuantitas() + get_retur.getDetail_pengiriman().get(i).getKuantitas());
			eStockRepo.save(d);
			
			PenyimpananMasuk f = new PenyimpananMasuk();
			f.setPenerimaan_code(penerimaan_code);
			f.setTanggal_masuk(new Date());
			f.setId_office(get_retur.getId_office_tujuan());
			f.setLokasi_office(get_retur.getLokasi_office_tujuan());
			f.setArtikel(get_retur.getDetail_pengiriman().get(i).getArtikel());
			f.setKategori(get_retur.getDetail_pengiriman().get(i).getKategori());
			f.setNama_kategori(get_retur.getDetail_pengiriman().get(i).getNama_kategori());
			f.setType(get_retur.getDetail_pengiriman().get(i).getType());
			f.setType_name(get_retur.getDetail_pengiriman().get(i).getType_name());
			f.setNama_barang(get_retur.getDetail_pengiriman().get(i).getNama_barang());
			f.setKuantitas(get_retur.getDetail_pengiriman().get(i).getKuantitas());
			f.setUkuran(get_retur.getDetail_pengiriman().get(i).getUkuran());
			f.setHpp(get_retur.getDetail_pengiriman().get(i).getHpp());
			f.setHarga_jual(get_retur.getDetail_pengiriman().get(i).getHarga_jual());
			f.setKeterangan("Pengembalian Barang Dari Store");
			f.setRowstatus(1);
			ePenyimpananRepo.save(f);
			
			p.setPenerimaan_code(penerimaan_code);
			p.setTanggal_retur(get_retur.getTanggal_retur());
			p.setTanggal_penerimaan(new Date());
			p.setId_office(get_retur.getId_office_tujuan());
			p.setLokasi_office(get_retur.getLokasi_office_tujuan());
			p.setId_store(get_retur.getId_store_asal());
			p.setLokasi_store(get_retur.getLokasi_store_asal());
			p.setArtikel(get_retur.getDetail_pengiriman().get(i).getArtikel());
			p.setKategori(get_retur.getDetail_pengiriman().get(i).getKategori());
			p.setNama_kategori(get_retur.getDetail_pengiriman().get(i).getNama_kategori());
			p.setType(get_retur.getDetail_pengiriman().get(i).getType());
			p.setType_name(get_retur.getDetail_pengiriman().get(i).getType_name());
			p.setNama_barang(get_retur.getDetail_pengiriman().get(i).getNama_barang());
			p.setKuantitas(get_retur.getDetail_pengiriman().get(i).getKuantitas());
			p.setUkuran(get_retur.getDetail_pengiriman().get(i).getUkuran());
			p.setHpp(get_retur.getDetail_pengiriman().get(i).getHpp());
			p.setHarga_jual(get_retur.getDetail_pengiriman().get(i).getHarga_jual());
			p.setRowstatus(1);
			eRepo.save(p);
		}
		return penerimaan_code;
	}

	public List<PenerimaanStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PenerimaanStore> getAllPenerimaanStore(){

		return eRepo.findByRowstatus(1);
	}
	
	public String deletePenerimaanStoreById(String penerimaan_code)
    {
		List<PenerimaanStore> get_retur = new ArrayList<>();
		get_retur = eRepo.findByPenerimaan_code(penerimaan_code);
    	for(int i = 0; i < get_retur.size(); i++) {
    		get_retur.get(i).setRowstatus(0);
	    	
	    	List<PenyimpananMasuk> f = new ArrayList<>();
			f = ePenyimpananRepo.findByPenerimaan_code(get_retur.get(i).getPenerimaan_code());
			for(int j = 0; j < f.size(); j++) {
				f.get(j).setRowstatus(0);
				ePenyimpananRepo.save(f.get(j));
			}
			
	    	StockOffice d = new StockOffice();
			d = eStockRepo.findById_officeAndSku_code(
					get_retur.get(i).getId_office(),
					get_retur.get(i).getSku_code());
	    	d.setKuantitas(d.getKuantitas() - get_retur.get(i).getKuantitas());
	    	eStockRepo.save(d);
	    	
	    	eRepo.save(get_retur.get(i));
    	}
    	return penerimaan_code;    
    }
	
	public String update(String penerimaan_code) {
		//reverse stock
		List<PenerimaanStore> get_retur = new ArrayList<>();
		get_retur = eRepo.findByPenerimaan_code(penerimaan_code);
    	for(int i = 0; i < get_retur.size(); i++) {
    		get_retur.get(i).setRowstatus(0);
	    	
	    	List<PenyimpananMasuk> f = new ArrayList<>();
			f = ePenyimpananRepo.findByPenerimaan_code(get_retur.get(i).getPenerimaan_code());
			for(int j = 0; j < f.size(); j++) {
				f.get(j).setRowstatus(0);
				ePenyimpananRepo.save(f.get(j));
			}
			
	    	StockOffice d = new StockOffice();
			d = eStockRepo.findById_officeAndSku_code(
					get_retur.get(i).getId_office(),
					get_retur.get(i).getSku_code());
	    	d.setKuantitas(d.getKuantitas() - get_retur.get(i).getKuantitas());
	    	eStockRepo.save(d);
	    	
	    	eRepo.save(get_retur.get(i));
    	}
    	//end reverse stock
    	
    	ReturGudang get_retur_new = new ReturGudang();
    	get_retur_new = eReturRepo.findByPengiriman_code(penerimaan_code);
		for (int i = 0; i < get_retur_new.getDetail_pengiriman().size(); i++) {
			PenerimaanStore p = new PenerimaanStore();
			
			StockOffice d = new StockOffice();
			d = eStockRepo.findById_officeAndSku_code(
					get_retur_new.getId_office_tujuan(),
					get_retur_new.getDetail_pengiriman().get(i).getSku_code());
			d.setKuantitas(d.getKuantitas() + get_retur_new.getDetail_pengiriman().get(i).getKuantitas());
			eStockRepo.save(d);
			
			PenyimpananMasuk f = new PenyimpananMasuk();
			f.setPenerimaan_code(penerimaan_code);
			f.setTanggal_masuk(new Date());
			f.setId_office(get_retur_new.getId_office_tujuan());
			f.setLokasi_office(get_retur_new.getLokasi_office_tujuan());
			f.setArtikel(get_retur_new.getDetail_pengiriman().get(i).getArtikel());
			f.setKategori(get_retur_new.getDetail_pengiriman().get(i).getKategori());
			f.setNama_kategori(get_retur_new.getDetail_pengiriman().get(i).getNama_kategori());
			f.setType(get_retur_new.getDetail_pengiriman().get(i).getType());
			f.setType_name(get_retur_new.getDetail_pengiriman().get(i).getType_name());
			f.setNama_barang(get_retur_new.getDetail_pengiriman().get(i).getNama_barang());
			f.setKuantitas(get_retur_new.getDetail_pengiriman().get(i).getKuantitas());
			f.setUkuran(get_retur_new.getDetail_pengiriman().get(i).getUkuran());
			f.setHpp(get_retur_new.getDetail_pengiriman().get(i).getHpp());
			f.setHarga_jual(get_retur_new.getDetail_pengiriman().get(i).getHarga_jual());
			f.setKeterangan("Pengembalian Barang Dari Store");
			f.setRowstatus(1);
			ePenyimpananRepo.save(f);
			
			p.setPenerimaan_code(penerimaan_code);
			p.setTanggal_retur(get_retur_new.getTanggal_retur());
			p.setTanggal_penerimaan(new Date());
			p.setId_office(get_retur_new.getId_office_tujuan());
			p.setLokasi_office(get_retur_new.getLokasi_office_tujuan());
			p.setId_store(get_retur_new.getId_store_asal());
			p.setLokasi_store(get_retur_new.getLokasi_store_asal());
			p.setArtikel(get_retur_new.getDetail_pengiriman().get(i).getArtikel());
			p.setKategori(get_retur_new.getDetail_pengiriman().get(i).getKategori());
			p.setNama_kategori(get_retur_new.getDetail_pengiriman().get(i).getNama_kategori());
			p.setType(get_retur_new.getDetail_pengiriman().get(i).getType());
			p.setType_name(get_retur_new.getDetail_pengiriman().get(i).getType_name());
			p.setNama_barang(get_retur_new.getDetail_pengiriman().get(i).getNama_barang());
			p.setKuantitas(get_retur_new.getDetail_pengiriman().get(i).getKuantitas());
			p.setUkuran(get_retur_new.getDetail_pengiriman().get(i).getUkuran());
			p.setHpp(get_retur_new.getDetail_pengiriman().get(i).getHpp());
			p.setHarga_jual(get_retur_new.getDetail_pengiriman().get(i).getHarga_jual());
			p.setRowstatus(1);
			eRepo.save(p);
		}
		return penerimaan_code;
	}

}
