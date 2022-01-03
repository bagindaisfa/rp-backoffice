package com.gbsystem.rpbackoffice.services;

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
	
	public PenerimaanStore savePenerimaanStore(String penerimaan_code) {
		
		ReturGudang get_retur = new ReturGudang();
		get_retur = eReturRepo.findByPengiriman_code(penerimaan_code);
		
		PenerimaanStore p = new PenerimaanStore();
		StockOffice prev = new StockOffice();
		prev = eStockRepo.findById_officeAndArtikel(get_retur.getId_office_tujuan(),get_retur.getArtikel()).get(0);
		
		double prev_qty = prev.getKuantitas();
		
		StockOffice d = new StockOffice();
		d = eStockRepo.findById_officeAndArtikel(get_retur.getId_office_tujuan(),get_retur.getArtikel()).get(0);
		d.setKuantitas(prev_qty + get_retur.getKuantitas());
		eStockRepo.save(d);
		
		PenyimpananMasuk f = new PenyimpananMasuk();
		f.setPenerimaan_code(penerimaan_code);
		f.setTanggal_masuk(new Date());
		f.setArtikel(get_retur.getArtikel());
		f.setKategori(get_retur.getKategori());
		f.setTipe(get_retur.getTipe());
		f.setNama_barang(get_retur.getNama_barang());
		f.setKuantitas(get_retur.getKuantitas());
		f.setUkuran(get_retur.getUkuran());
		f.setHpp(get_retur.getHpp());
		f.setHarga_jual(get_retur.getHarga_jual());
		f.setKeterangan("Pengembalian Barang Dari Store");
		f.setRowstatus(1);
		ePenyimpananRepo.save(f);
		
		p.setFoto_barang(get_retur.getFoto_barang());
		p.setPenerimaan_code(penerimaan_code);
		p.setTanggal_retur(get_retur.getTanggal_retur());
		p.setTanggal_penerimaan(new Date());
		p.setId_office(get_retur.getId_office_tujuan());
		p.setLokasi_office(get_retur.getLokasi_office_tujuan());
		p.setId_store(get_retur.getId_store_asal());
		p.setLokasi_store(get_retur.getLokasi_store_asal());
		p.setArtikel(get_retur.getArtikel());
		p.setKategori(get_retur.getKategori());
		p.setTipe(get_retur.getTipe());
		p.setNama_barang(get_retur.getNama_barang());
		p.setKuantitas(get_retur.getKuantitas());
		p.setUkuran(get_retur.getUkuran());
		p.setHpp(get_retur.getHpp());
		p.setHarga_jual(get_retur.getHarga_jual());
		p.setRowstatus(1);
		
		return eRepo.save(p);
	}

	public List<PenerimaanStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PenerimaanStore> getAllPenerimaanStore(){

		return eRepo.findByRowstatus(1);
	}
	
	public PenerimaanStore deletePenerimaanStoreById(Long id, int id_office, String artikel)
    {
		PenerimaanStore p = new PenerimaanStore();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	
    	PenyimpananMasuk f = new PenyimpananMasuk();
		f = ePenyimpananRepo.findByPenerimaan_code(p.getPenerimaan_code()).get(0);
		f.setRowstatus(0);
		ePenyimpananRepo.save(f);
		StockOffice prev = new StockOffice();
		prev = eStockRepo.findById_officeAndArtikel(id_office,artikel).get(0);
		
		double prev_qty = prev.getKuantitas();
		
    	StockOffice d = new StockOffice();
		d = eStockRepo.findById_officeAndArtikel(id_office,artikel).get(0);
    	d.setKuantitas(prev_qty-p.getKuantitas());
    	eStockRepo.save(d);
    	
    	return eRepo.save(p);    
    }
	
	public PenerimaanStore update(Long id,String penerimaan_code) {
		PenerimaanStore p = new PenerimaanStore();
    	p = eRepo.findById(id).get();
    	
    	PenyimpananMasuk f = new PenyimpananMasuk();
		f = ePenyimpananRepo.findByPenerimaan_code(p.getPenerimaan_code()).get(0);
    	
    	ReturGudang get_retur = new ReturGudang();
		get_retur = eReturRepo.findByPengiriman_code(penerimaan_code);
		StockOffice prev = new StockOffice();
		prev = eStockRepo.findById_officeAndArtikel(get_retur.getId_office_tujuan(),get_retur.getArtikel()).get(0);
		
		double prev_qty = prev.getKuantitas();
		
    	StockOffice d = new StockOffice();
		d = eStockRepo.findById_officeAndArtikel(get_retur.getId_office_tujuan(),get_retur.getArtikel()).get(0);
		d.setKuantitas((prev_qty - p.getKuantitas()) + get_retur.getKuantitas());
		eStockRepo.save(d);
    	
		f.setPenerimaan_code(penerimaan_code);
		f.setTanggal_masuk(new Date());
		f.setArtikel(get_retur.getArtikel());
		f.setKategori(get_retur.getKategori());
		f.setTipe(get_retur.getTipe());
		f.setNama_barang(get_retur.getNama_barang());
		f.setKuantitas(get_retur.getKuantitas());
		f.setUkuran(get_retur.getUkuran());
		f.setHpp(get_retur.getHpp());
		f.setHarga_jual(get_retur.getHarga_jual());
		f.setKeterangan("Pengembalian Barang Dari Store");
		f.setRowstatus(1);
		ePenyimpananRepo.save(f);
    	
		p.setFoto_barang(get_retur.getFoto_barang());
		p.setPenerimaan_code(penerimaan_code);
		p.setTanggal_retur(get_retur.getTanggal_retur());
		p.setTanggal_penerimaan(new Date());
		p.setId_office(get_retur.getId_office_tujuan());
		p.setLokasi_office(get_retur.getLokasi_office_tujuan());
		p.setId_store(get_retur.getId_store_asal());
		p.setLokasi_store(get_retur.getLokasi_store_asal());
		p.setArtikel(get_retur.getArtikel());
		p.setKategori(get_retur.getKategori());
		p.setTipe(get_retur.getTipe());
		p.setNama_barang(get_retur.getNama_barang());
		p.setKuantitas(get_retur.getKuantitas());
		p.setUkuran(get_retur.getUkuran());
		p.setHpp(get_retur.getHpp());
		p.setHarga_jual(get_retur.getHarga_jual());
		p.setRowstatus(1);
		
		return eRepo.save(p);
	}

}
