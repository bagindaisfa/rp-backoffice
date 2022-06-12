package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPenerimaanOfficeFromStore;
import com.gbsystem.rpbackoffice.entities.PenerimaanOfficeFromStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.ReturGudang;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.DetailPenerimaanOfficeFromStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenerimaanOfficeFromStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreKeluarRepository;
import com.gbsystem.rpbackoffice.repository.ReturGudangRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PenerimaanOfficeFromStoreService {
	
	@Autowired
	private PenerimaanOfficeFromStoreRepository eRepo;
	
	@Autowired
	private DetailPenerimaanOfficeFromStoreRepository eDetailRepo;
	
	@Autowired
	private StockOfficeRepository eStockRepo;
	
	@Autowired
	private StockStoreRepository eStockStoreRepo;
	
	@Autowired
	private PenyimpananMasukRepository ePenyimpananRepo;
	
	@Autowired
	private PenyimpananStoreKeluarRepository ePenyimpananStoreRepo;
	
	@Autowired
	private ReturGudangRepository eReturRepo;
	
	public PenerimaanOfficeFromStore savePenerimaanStore(PenerimaanOfficeFromStore penerimaanOfficeFromStore) {
		PenerimaanOfficeFromStore p = new PenerimaanOfficeFromStore();
		List<DetailPenerimaanOfficeFromStore> details = new ArrayList<>();
		
		String code_penerimaan = "POFS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		
		PenerimaanOfficeFromStore checker = new PenerimaanOfficeFromStore();
		checker = eRepo.getByPenerimaan_code(code_penerimaan);
		
		ReturGudang get_retur = new ReturGudang();
    	get_retur = eReturRepo.findByPengiriman_code(penerimaanOfficeFromStore.getRetur_code());
    	
		if (checker != null) {
			code_penerimaan = "POFS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+2);
		}
		
		p.setPenerimaan_code(code_penerimaan);
		p.setRetur_code(penerimaanOfficeFromStore.getRetur_code());
		p.setTanggal_penerimaan(penerimaanOfficeFromStore.getTanggal_penerimaan());
		p.setId_office(penerimaanOfficeFromStore.getId_office());
		p.setLokasi_office(penerimaanOfficeFromStore.getLokasi_office());
		p.setId_store(penerimaanOfficeFromStore.getId_store());
		p.setLokasi_store(penerimaanOfficeFromStore.getLokasi_store());
		p.setRowstatus(1);
		
		for (int i = 0; i < penerimaanOfficeFromStore.getDetailPenerimaanList().size(); i++) {
			DetailPenerimaanOfficeFromStore detail = new DetailPenerimaanOfficeFromStore();
			
			detail.setPenerimaan_code(code_penerimaan);
			detail.setTanggal_penerimaan(penerimaanOfficeFromStore.getTanggal_penerimaan());
			detail.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
			detail.setArtikel(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
			detail.setKategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKategori());
			detail.setNama_kategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_kategori());
			detail.setType(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType());
			detail.setType_name(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType_name());
			detail.setNama_barang(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_barang());
			detail.setKuantitas(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
			detail.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
			detail.setKeterangan(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKeterangan());
			detail.setHarga_jual(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHarga_jual());
			detail.setHpp(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHpp());
			detail.setRowstatus(1);
			detail.setPenerimaanOfficeFromStore(p);
			details.add(detail);
			
			StockOffice d = new StockOffice();
			d = eStockRepo.findById_officeAndArtikel(
					penerimaanOfficeFromStore.getId_office(),
					penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
			d.setKuantitas(d.getKuantitas() + penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
			StockStore e = new StockStore();
			e = eStockStoreRepo.findById_storeAndArtikel(
					penerimaanOfficeFromStore.getId_store(),
					penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
			e.setKuantitas(d.getKuantitas() - penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
			eStockStoreRepo.save(e);
			
			PenyimpananMasuk f = new PenyimpananMasuk();
			f.setPenerimaan_code(code_penerimaan);
			f.setTanggal_masuk(penerimaanOfficeFromStore.getTanggal_penerimaan());
			f.setId_office(penerimaanOfficeFromStore.getId_office());
			f.setLokasi_office(penerimaanOfficeFromStore.getLokasi_office());
			f.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
			f.setArtikel(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
			f.setKategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKategori());
			f.setNama_kategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_kategori());
			f.setType(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType());
			f.setType_name(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType_name());
			f.setNama_barang(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_barang());
			f.setKuantitas(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
			f.setHpp(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHpp());
			f.setHarga_jual(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHarga_jual());
			f.setKeterangan("Pengembalian Barang Dari " + penerimaanOfficeFromStore.getLokasi_store());
			f.setRowstatus(1);
			ePenyimpananRepo.save(f);
			
			PenyimpananStoreKeluar h = new PenyimpananStoreKeluar();
			h.setId_office(penerimaanOfficeFromStore.getId_office());
			h.setLokasi_office(penerimaanOfficeFromStore.getLokasi_office());
			h.setPengiriman_code(penerimaanOfficeFromStore.getRetur_code());
			h.setTanggal_keluar(get_retur.getTanggal_retur());
			h.setId_store(get_retur.getId_store_asal());
			h.setLokasi_store(get_retur.getLokasi_store_asal());
			h.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
			h.setArtikel(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
			h.setKategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKategori());
			h.setNama_kategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_kategori());
			h.setType(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType());
			h.setType_name(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType_name());
			h.setNama_barang(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_barang());
			h.setKuantitas(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
			h.setRowstatus(1);
			ePenyimpananStoreRepo.save(h);
			
		}
		p.setDetailPenerimaanList(details);
		return eRepo.save(p);
	}

	public List<PenerimaanOfficeFromStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PenerimaanOfficeFromStore> getAllPenerimaanStore(){

		return eRepo.findByRowstatus(1);
	}
	
	public String deletePenerimaanStoreById(Long id)
    {
		PenerimaanOfficeFromStore p = new PenerimaanOfficeFromStore();
		p = eRepo.findById(id).get();
		p.setRowstatus(0);
		
		List<DetailPenerimaanOfficeFromStore> details = new ArrayList<>();
		
		List<PenyimpananMasuk> f = new ArrayList<>();
		f = ePenyimpananRepo.findByPenerimaan_code(p.getPenerimaan_code());
		for(int j = 0; j < f.size(); j++) {
			f.get(j).setRowstatus(0);
			ePenyimpananRepo.save(f.get(j));
		}
		
		List<PenyimpananStoreKeluar> h = new ArrayList<>();
		h = ePenyimpananStoreRepo.findByPengiriman_code(p.getRetur_code());
		for(int t = 0; t < h.size(); t++) {
			h.get(t).setRowstatus(0);
			ePenyimpananStoreRepo.save(h.get(t));
		}
		
    	for(int i = 0; i < p.getDetailPenerimaanList().size(); i++) {
    		p.getDetailPenerimaanList().get(i).setRowstatus(0);
    		p.getDetailPenerimaanList().get(i).setPenerimaanOfficeFromStore(p);
    		details.add(p.getDetailPenerimaanList().get(i));
			
    		StockOffice d = new StockOffice();
			d = eStockRepo.findById_officeAndArtikel(
					p.getId_office(),
					p.getDetailPenerimaanList().get(i).getArtikel());
			d.setKuantitas(d.getKuantitas() - p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
			StockStore e = new StockStore();
			e = eStockStoreRepo.findById_storeAndArtikel(
					p.getId_store(),
					p.getDetailPenerimaanList().get(i).getArtikel());
			e.setKuantitas(d.getKuantitas() + p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockStoreRepo.save(e);
    	}
    	p.setDetailPenerimaanList(details);
    	eRepo.save(p);
    	return "Berhasil Didelete!";    
    }
	
	public String update(PenerimaanOfficeFromStore penerimaanOfficeFromStore) {
		PenerimaanOfficeFromStore p = new PenerimaanOfficeFromStore();
    	p = eRepo.findById(penerimaanOfficeFromStore.getId()).get();
    	
    	ReturGudang pengiriman = new ReturGudang();
		pengiriman = eReturRepo.findByPengiriman_code(penerimaanOfficeFromStore.getRetur_code());
		
    	List<DetailPenerimaanOfficeFromStore> details = new ArrayList<>();
		
		p.setPenerimaan_code(penerimaanOfficeFromStore.getPenerimaan_code());
		p.setRetur_code(penerimaanOfficeFromStore.getRetur_code());
		p.setTanggal_penerimaan(penerimaanOfficeFromStore.getTanggal_penerimaan());
		p.setId_office(penerimaanOfficeFromStore.getId_office());
		p.setLokasi_office(penerimaanOfficeFromStore.getLokasi_office());
		p.setId_store(penerimaanOfficeFromStore.getId_store());
		p.setLokasi_store(penerimaanOfficeFromStore.getLokasi_store());
		p.setRowstatus(penerimaanOfficeFromStore.getRowstatus());
		
		for(int i = 0; i < penerimaanOfficeFromStore.getDetailPenerimaanList().size(); i++) {
			
			DetailPenerimaanOfficeFromStore detail_update = new DetailPenerimaanOfficeFromStore();
			
			if (penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getId() == null) {
				detail_update = null;
			} else {
				detail_update = eDetailRepo.getById(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getId());
			}
			
			if (detail_update != null) {
				detail_update.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
				detail_update.setArtikel(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
				detail_update.setKategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKategori());
				detail_update.setNama_kategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_kategori());
				detail_update.setType(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType());
				detail_update.setType_name(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType_name());
				detail_update.setNama_barang(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_barang());
				detail_update.setKuantitas(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
				detail_update.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
				detail_update.setKeterangan(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKeterangan());
				detail_update.setHarga_jual(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHarga_jual());
				detail_update.setHpp(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHpp());
				detail_update.setRowstatus(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getRowstatus());
				
				if (penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getRowstatus() == 1) {
					
					StockStore d = new StockStore();
					d = eStockStoreRepo.findById_storeAndArtikel(
							penerimaanOfficeFromStore.getId_store(),
							penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
					d.setKuantitas((d.getKuantitas() - detail_update.getKuantitas()) + penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
					eStockStoreRepo.save(d);
					
					StockOffice g = new StockOffice();
					g = eStockRepo.findById_officeAndArtikel(
							penerimaanOfficeFromStore.getId_office(),
							penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
					g.setKuantitas((g.getKuantitas() + detail_update.getKuantitas()) - penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
					eStockRepo.save(g);
				
					PenyimpananMasuk f = new PenyimpananMasuk();
					f = ePenyimpananRepo.getPenyimpananByPenerimanCodeandArtikel(
							penerimaanOfficeFromStore.getPenerimaan_code(),
							penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel()
							);
					f.setPenerimaan_code(penerimaanOfficeFromStore.getPenerimaan_code());
					f.setTanggal_masuk(penerimaanOfficeFromStore.getTanggal_penerimaan());
					f.setId_office(penerimaanOfficeFromStore.getId_office());
					f.setLokasi_office(penerimaanOfficeFromStore.getLokasi_office());
					f.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
					f.setArtikel(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
					f.setKategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKategori());
					f.setNama_kategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_kategori());
					f.setType(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType());
					f.setType_name(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType_name());
					f.setNama_barang(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_barang());
					f.setKuantitas(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
					f.setHpp(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHpp());
					f.setHarga_jual(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHarga_jual());
					f.setKeterangan("Pengembalian Barang Dari " + penerimaanOfficeFromStore.getLokasi_store());
					f.setRowstatus(1);
					ePenyimpananRepo.save(f);
					
					PenyimpananStoreKeluar h = new PenyimpananStoreKeluar();
					h = ePenyimpananStoreRepo.getByPengirimanCodeandArtikel(
							penerimaanOfficeFromStore.getPenerimaan_code(),
							penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel()
							);
					h.setId_office(penerimaanOfficeFromStore.getId_office());
					h.setLokasi_office(penerimaanOfficeFromStore.getLokasi_office());
					h.setPengiriman_code(penerimaanOfficeFromStore.getRetur_code());
					h.setTanggal_keluar(pengiriman.getTanggal_retur());
					h.setId_store(pengiriman.getId_store_asal());
					h.setLokasi_store(pengiriman.getLokasi_store_asal());
					h.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
					h.setArtikel(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
					h.setKategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKategori());
					h.setNama_kategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_kategori());
					h.setType(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType());
					h.setType_name(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType_name());
					h.setNama_barang(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_barang());
					h.setKuantitas(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
					h.setRowstatus(1);
					ePenyimpananStoreRepo.save(h);
				} else {
					StockStore d = new StockStore();
					d = eStockStoreRepo.findById_storeAndArtikel(
							penerimaanOfficeFromStore.getId_store(),
							penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
					d.setKuantitas(d.getKuantitas() + penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
					eStockStoreRepo.save(d);
					
					StockOffice g = new StockOffice();
					g = eStockRepo.findById_officeAndArtikel(
							penerimaanOfficeFromStore.getId_office(),
							penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
					g.setKuantitas(g.getKuantitas() - penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
					eStockRepo.save(g);
				
					PenyimpananMasuk f = new PenyimpananMasuk();
					f = ePenyimpananRepo.getPenyimpananByPenerimanCodeandArtikel(
							penerimaanOfficeFromStore.getPenerimaan_code(),
							penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel()
							);
					f.setRowstatus(0);
					ePenyimpananRepo.save(f);
					
					PenyimpananStoreKeluar h = new PenyimpananStoreKeluar();
					h = ePenyimpananStoreRepo.getByPengirimanCodeandArtikel(
							penerimaanOfficeFromStore.getPenerimaan_code(),
							penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel()
							);
					h.setRowstatus(0);
					ePenyimpananStoreRepo.save(h);
				}
				detail_update.setPenerimaanOfficeFromStore(p);
				details.add(detail_update);	
			} else {
				DetailPenerimaanOfficeFromStore detail = new DetailPenerimaanOfficeFromStore();
				
				detail.setPenerimaan_code(penerimaanOfficeFromStore.getPenerimaan_code());
				detail.setTanggal_penerimaan(penerimaanOfficeFromStore.getTanggal_penerimaan());
				detail.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
				detail.setArtikel(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
				detail.setKategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKategori());
				detail.setNama_kategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_kategori());
				detail.setType(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType());
				detail.setType_name(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType_name());
				detail.setNama_barang(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_barang());
				detail.setKuantitas(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
				detail.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
				detail.setKeterangan(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKeterangan());
				detail.setHarga_jual(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHarga_jual());
				detail.setHpp(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHpp());
				detail.setRowstatus(1);
				detail.setPenerimaanOfficeFromStore(p);
				details.add(detail);
				
				StockOffice d = new StockOffice();
				d = eStockRepo.findById_officeAndArtikel(
						penerimaanOfficeFromStore.getId_office(),
						penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
				d.setKuantitas(d.getKuantitas() + penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
				eStockRepo.save(d);
				
				StockStore e = new StockStore();
				e = eStockStoreRepo.findById_storeAndArtikel(
						penerimaanOfficeFromStore.getId_store(),
						penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
				e.setKuantitas(d.getKuantitas() - penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
				eStockStoreRepo.save(e);
				
				PenyimpananMasuk f = new PenyimpananMasuk();
				f.setPenerimaan_code(penerimaanOfficeFromStore.getPenerimaan_code());
				f.setTanggal_masuk(penerimaanOfficeFromStore.getTanggal_penerimaan());
				f.setId_office(penerimaanOfficeFromStore.getId_office());
				f.setLokasi_office(penerimaanOfficeFromStore.getLokasi_office());
				f.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
				f.setArtikel(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
				f.setKategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKategori());
				f.setNama_kategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_kategori());
				f.setType(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType());
				f.setType_name(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType_name());
				f.setNama_barang(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_barang());
				f.setKuantitas(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
				f.setHpp(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHpp());
				f.setHarga_jual(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getHarga_jual());
				f.setKeterangan("Pengembalian Barang Dari " + penerimaanOfficeFromStore.getLokasi_store());
				f.setRowstatus(1);
				ePenyimpananRepo.save(f);
				
				PenyimpananStoreKeluar h = new PenyimpananStoreKeluar();
				h.setId_office(penerimaanOfficeFromStore.getId_office());
				h.setLokasi_office(penerimaanOfficeFromStore.getLokasi_office());
				h.setPengiriman_code(penerimaanOfficeFromStore.getRetur_code());
				h.setTanggal_keluar(pengiriman.getTanggal_retur());
				h.setId_store(pengiriman.getId_store_asal());
				h.setLokasi_store(pengiriman.getLokasi_store_asal());
				h.setSku_code(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getSku_code());
				h.setArtikel(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getArtikel());
				h.setKategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKategori());
				h.setNama_kategori(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_kategori());
				h.setType(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType());
				h.setType_name(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getType_name());
				h.setNama_barang(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getNama_barang());
				h.setKuantitas(penerimaanOfficeFromStore.getDetailPenerimaanList().get(i).getKuantitas());
				h.setRowstatus(1);
				ePenyimpananStoreRepo.save(h);
			}
			
		}
		p.setDetailPenerimaanList(details);
		eRepo.save(p);
		return "Berhasil Diupdate!";
	}

}
