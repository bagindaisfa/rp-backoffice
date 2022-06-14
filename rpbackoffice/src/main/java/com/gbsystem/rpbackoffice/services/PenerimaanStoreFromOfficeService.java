package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPenerimaanStoreFromOffice;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.PenerimaanStoreFromOffice;
import com.gbsystem.rpbackoffice.entities.PengirimanOfficeToStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.DetailPenerimaanStoreFromOfficeRepository;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PenerimaanStoreFromOfficeRepository;
import com.gbsystem.rpbackoffice.repository.PengirimanOfficeToStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananKeluarRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PenerimaanStoreFromOfficeService {
	
	@Autowired
	private PenerimaanStoreFromOfficeRepository eRepo;
	
	@Autowired
	private DetailPenerimaanStoreFromOfficeRepository eDetailRepo;

	@Autowired
	private StockStoreRepository eStockRepo;
	
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	
	@Autowired
	private PenyimpananKeluarRepository ePenyimpananRepo;
	
	@Autowired
	private PenyimpananStoreMasukRepository ePenyimpananStoreRepo;
	
	@Autowired
	private MasterProductRepository eMasterProductRepo;
	
	@Autowired
	private PengirimanOfficeToStoreRepository ePengirimanRepo;
	
	public PenerimaanStoreFromOffice savePenerimaanStore(PenerimaanStoreFromOffice penerimaanStoreFromOffice) {
		
		String code_penerimaan = "PSFO-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		
		PenerimaanStoreFromOffice p = new PenerimaanStoreFromOffice();
		List<DetailPenerimaanStoreFromOffice> details = new ArrayList<>();
		
		PengirimanOfficeToStore pengiriman = new PengirimanOfficeToStore();
		pengiriman = ePengirimanRepo.getByPengirimanCode(penerimaanStoreFromOffice.getPengiriman_code());
		
		p.setPenerimaan_code(code_penerimaan);
		p.setPengiriman_code(penerimaanStoreFromOffice.getPengiriman_code());
		p.setTanggal_penerimaan(penerimaanStoreFromOffice.getTanggal_penerimaan());
		p.setId_office(penerimaanStoreFromOffice.getId_office());
		p.setLokasi_office(penerimaanStoreFromOffice.getLokasi_office());
		p.setId_store(penerimaanStoreFromOffice.getId_store());
		p.setLokasi_store(penerimaanStoreFromOffice.getLokasi_store());
		p.setRowstatus(1);
		for(int i = 0; i < penerimaanStoreFromOffice.getDetailPenerimaanList().size(); i++) {
			DetailPenerimaanStoreFromOffice detail = new DetailPenerimaanStoreFromOffice();
			
			detail.setPenerimaan_code(code_penerimaan);
			detail.setTanggal_penerimaan(penerimaanStoreFromOffice.getTanggal_penerimaan());
			detail.setSku_code(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getSku_code());
			detail.setArtikel(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getArtikel());
			detail.setKategori(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKategori());
			detail.setNama_kategori(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getNama_kategori());
			detail.setType(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getType());
			detail.setType_name(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getType_name());
			detail.setNama_barang(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getNama_barang());
			detail.setKuantitas(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKuantitas());
			detail.setKeterangan(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKeterangan());
			detail.setHarga_jual(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getHarga_jual());
			detail.setHpp(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getHpp());
			detail.setRowstatus(1);
			detail.setPenerimaanStoreFromOffice(p);
			details.add(detail);
			
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndArtikel(
					penerimaanStoreFromOffice.getId_store(),
					penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getArtikel());
			if (d == null) {
				StockStore new_insert = new StockStore();
				
				MasterProduct prod = new MasterProduct();
				prod = eMasterProductRepo.findByArticle(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getArtikel());
				
				new_insert.setId_store(penerimaanStoreFromOffice.getId_store());
				new_insert.setLokasi_store(penerimaanStoreFromOffice.getLokasi_store());
				new_insert.setFoto_barang(prod.getImage());
				new_insert.setSku_code(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getSku_code());
				new_insert.setArtikel(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getArtikel());
				new_insert.setKategori(prod.getKategori());
				new_insert.setNama_kategori(prod.getNama_kategori());
				new_insert.setType(prod.getType());
				new_insert.setType_name(prod.getType_name());
				new_insert.setNama_barang(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getNama_barang());
				new_insert.setKuantitas(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKuantitas());
				new_insert.setHarga_jual(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getHarga_jual());
				new_insert.setHpp(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getHpp());
				new_insert.setRowstatus(1);
				eStockRepo.save(new_insert);
			} else {
				d.setKuantitas(d.getKuantitas() + penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKuantitas());
				eStockRepo.save(d);
			}
			
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndArtikel(
					penerimaanStoreFromOffice.getId_office(),
					penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getArtikel());
			g.setKuantitas(g.getKuantitas() - penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKuantitas());
			eStockOfficeRepo.save(g);
			
			PenyimpananKeluar f = new PenyimpananKeluar();
			f.setId_office(penerimaanStoreFromOffice.getId_office());
			f.setLokasi_office(penerimaanStoreFromOffice.getLokasi_office());
			f.setPengiriman_code(penerimaanStoreFromOffice.getPengiriman_code());
			f.setTanggal_keluar(pengiriman.getTanggal_pengiriman());
			f.setId_store(penerimaanStoreFromOffice.getId_store());
			f.setLokasi_store(penerimaanStoreFromOffice.getLokasi_store());
			f.setSku_code(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getSku_code());
			f.setArtikel(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getArtikel());
			f.setKategori(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKategori());
			f.setNama_kategori(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getNama_kategori());
			f.setType(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getType());
			f.setType_name(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getType_name());
			f.setNama_barang(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getNama_barang());
			f.setKuantitas(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKuantitas());
			f.setKeterangan("Barang Dikirim Ke Store");
			f.setRowstatus(1);
			ePenyimpananRepo.save(f);
			
			PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
			h.setId_office(pengiriman.getId_office());
			h.setLokasi_office(pengiriman.getLokasi_office());
			h.setPenerimaan_code(code_penerimaan);
			h.setTanggal_masuk(penerimaanStoreFromOffice.getTanggal_penerimaan());
			h.setId_store(pengiriman.getId_store());
			h.setLokasi_store(pengiriman.getLokasi_store());
			h.setSku_code(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getSku_code());
			h.setArtikel(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getArtikel());
			h.setKategori(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKategori());
			h.setNama_kategori(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getNama_kategori());
			h.setType(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getType());
			h.setType_name(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getType_name());
			h.setNama_barang(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getNama_barang());
			h.setKuantitas(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKuantitas());
			h.setRowstatus(1);
			ePenyimpananStoreRepo.save(h);
			
		}
		
		p.setDetailPenerimaanList(details);
		return eRepo.save(p);
	}

	public List<PenerimaanStoreFromOffice> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PenerimaanStoreFromOffice> getAllPenerimaanFromOffice(){

		return eRepo.findByRowstatus(1);
	}
	
	public PenerimaanStoreFromOffice deletePenerimaanFromOfficeById(Long id)
    {
		PenerimaanStoreFromOffice p = new PenerimaanStoreFromOffice();
		p = eRepo.findById(id).get();
		List<DetailPenerimaanStoreFromOffice> details = new ArrayList<>();
		p.setRowstatus(0);
		
		List<PenyimpananKeluar> f = new ArrayList<>();
		f = ePenyimpananRepo.findByPengiriman_code(p.getPengiriman_code());
		for(int s = 0; s < f.size(); s++) {
			f.get(s).setRowstatus(0);
			ePenyimpananRepo.save(f.get(s));
		}
		
		List<PenyimpananStoreMasuk> h = new ArrayList<>();
		h = ePenyimpananStoreRepo.findByPenerimaan_code(p.getPenerimaan_code());
		for(int t = 0; t < h.size(); t++) {
			h.get(t).setRowstatus(0);
			ePenyimpananStoreRepo.save(h.get(t));
		}
		for(int i = 0; i < p.getDetailPenerimaanList().size(); i++) {
			p.getDetailPenerimaanList().get(i).setRowstatus(0);
			p.getDetailPenerimaanList().get(i).setPenerimaanStoreFromOffice(p);
			details.add(p.getDetailPenerimaanList().get(i));
			
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndArtikel(
					p.getId_store(),
					p.getDetailPenerimaanList().get(i).getArtikel());
			d.setKuantitas(d.getKuantitas() - p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndArtikel(
					p.getId_office(),
					p.getDetailPenerimaanList().get(i).getArtikel());
			g.setKuantitas(g.getKuantitas() + p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockOfficeRepo.save(g);
			
		}
		
		p.setDetailPenerimaanList(details);
    	return eRepo.save(p);
    }
	
	public PenerimaanStoreFromOffice update(PenerimaanStoreFromOffice penerimaanStoreFromOfficeNew) {
		PenerimaanStoreFromOffice p = new PenerimaanStoreFromOffice();
    	p = eRepo.findById(penerimaanStoreFromOfficeNew.getId()).get();
    	
    	PengirimanOfficeToStore pengiriman = new PengirimanOfficeToStore();
		pengiriman = ePengirimanRepo.getByPengirimanCode(penerimaanStoreFromOfficeNew.getPengiriman_code());
		
    	List<DetailPenerimaanStoreFromOffice> details = new ArrayList<>();
		
		p.setPengiriman_code(penerimaanStoreFromOfficeNew.getPengiriman_code());
		p.setTanggal_penerimaan(penerimaanStoreFromOfficeNew.getTanggal_penerimaan());
		p.setId_office(penerimaanStoreFromOfficeNew.getId_office());
		p.setLokasi_office(penerimaanStoreFromOfficeNew.getLokasi_office());
		p.setId_store(penerimaanStoreFromOfficeNew.getId_store());
		p.setLokasi_store(penerimaanStoreFromOfficeNew.getLokasi_store());
		p.setRowstatus(penerimaanStoreFromOfficeNew.getRowstatus());
		
		for(int i = 0; i < penerimaanStoreFromOfficeNew.getDetailPenerimaanList().size(); i++) {
			
			DetailPenerimaanStoreFromOffice detail_update = new DetailPenerimaanStoreFromOffice();
			
			if (penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getId() == null) {
				detail_update = null;
			} else {
				detail_update = eDetailRepo.findById(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getId()).orElse(null);
			}
			
			if (detail_update != null) {
				detail_update.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
				detail_update.setArtikel(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
				detail_update.setKategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKategori());
				detail_update.setNama_kategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_kategori());
				detail_update.setType(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType());
				detail_update.setType_name(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType_name());
				detail_update.setNama_barang(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_barang());
				detail_update.setKuantitas(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
				detail_update.setKeterangan(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKeterangan());
				detail_update.setHarga_jual(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getHarga_jual());
				detail_update.setHpp(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getHpp());
				detail_update.setRowstatus(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getRowstatus());
				
				if (penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getRowstatus() == 1) {
					
					StockStore d = new StockStore();
					d = eStockRepo.findById_storeAndArtikel(
							penerimaanStoreFromOfficeNew.getId_store(),
							penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
					d.setKuantitas((d.getKuantitas() - detail_update.getKuantitas()) + penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
					eStockRepo.save(d);
					
					StockOffice g = new StockOffice();
					g = eStockOfficeRepo.findById_officeAndArtikel(
							penerimaanStoreFromOfficeNew.getId_office(),
							penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
					g.setKuantitas((g.getKuantitas() + detail_update.getKuantitas()) - penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
					eStockOfficeRepo.save(g);
				
					PenyimpananKeluar f = new PenyimpananKeluar();
					f = ePenyimpananRepo.getPenyimpananByPengirimanCodeandArtikel(
							penerimaanStoreFromOfficeNew.getPengiriman_code(),
							penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel()
							);
					f.setId_office(penerimaanStoreFromOfficeNew.getId_office());
					f.setLokasi_office(penerimaanStoreFromOfficeNew.getLokasi_office());
					f.setPengiriman_code(p.getPengiriman_code());
					f.setTanggal_keluar(pengiriman.getTanggal_pengiriman());
					f.setId_store(penerimaanStoreFromOfficeNew.getId_store());
					f.setLokasi_store(penerimaanStoreFromOfficeNew.getLokasi_store());
					f.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
					f.setArtikel(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
					f.setKategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKategori());
					f.setNama_kategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_kategori());
					f.setType(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType());
					f.setType_name(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType_name());
					f.setNama_barang(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_barang());
					f.setKuantitas(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
					f.setKeterangan("Barang Dikirim Ke Store");
					f.setRowstatus(1);
					ePenyimpananRepo.save(f);
					
					PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
					h = ePenyimpananStoreRepo.getByPenerimaanCodeandArtikel(
							penerimaanStoreFromOfficeNew.getPenerimaan_code(),
							penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel()
							);
					h.setId_office(penerimaanStoreFromOfficeNew.getId_office());
					h.setLokasi_office(penerimaanStoreFromOfficeNew.getLokasi_office());
					h.setPenerimaan_code(p.getPenerimaan_code());
					h.setTanggal_masuk(penerimaanStoreFromOfficeNew.getTanggal_penerimaan());
					h.setId_store(penerimaanStoreFromOfficeNew.getId_store());
					h.setLokasi_store(penerimaanStoreFromOfficeNew.getLokasi_store());
					h.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
					h.setArtikel(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
					h.setKategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKategori());
					h.setNama_kategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_kategori());
					h.setType(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType());
					h.setType_name(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType_name());
					h.setNama_barang(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_barang());
					h.setKuantitas(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
					h.setRowstatus(1);
					ePenyimpananStoreRepo.save(h);
				} else {
					StockStore d = new StockStore();
					d = eStockRepo.findById_storeAndArtikel(
							penerimaanStoreFromOfficeNew.getId_store(),
							penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
					d.setKuantitas(d.getKuantitas() - penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
					eStockRepo.save(d);
					
					StockOffice g = new StockOffice();
					g = eStockOfficeRepo.findById_officeAndArtikel(
							penerimaanStoreFromOfficeNew.getId_office(),
							penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
					g.setKuantitas(g.getKuantitas() + penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
					eStockOfficeRepo.save(g);
				
					PenyimpananKeluar f = new PenyimpananKeluar();
					f = ePenyimpananRepo.getPenyimpananByPengirimanCodeandArtikel(
							penerimaanStoreFromOfficeNew.getPengiriman_code(),
							penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel()
							);
					f.setRowstatus(0);
					ePenyimpananRepo.save(f);
					
					PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
					h = ePenyimpananStoreRepo.getByPenerimaanCodeandArtikel(
							penerimaanStoreFromOfficeNew.getPenerimaan_code(),
							penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel()
							);
					h.setRowstatus(0);
					ePenyimpananStoreRepo.save(h);
				}
				detail_update.setPenerimaanStoreFromOffice(p);
				details.add(detail_update);	
			} else {
				DetailPenerimaanStoreFromOffice detail = new DetailPenerimaanStoreFromOffice();
				detail.setPenerimaan_code(p.getPenerimaan_code());
				detail.setTanggal_penerimaan(penerimaanStoreFromOfficeNew.getTanggal_penerimaan());
				detail.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
				detail.setArtikel(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
				detail.setKategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKategori());
				detail.setNama_kategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_kategori());
				detail.setType(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType());
				detail.setType_name(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType_name());
				detail.setNama_barang(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_barang());
				detail.setKuantitas(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
				detail.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
				detail.setKeterangan(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKeterangan());
				detail.setHarga_jual(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getHarga_jual());
				detail.setHpp(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getHpp());
				detail.setRowstatus(1);
				detail.setPenerimaanStoreFromOffice(p);
				details.add(detail);
				
				StockStore d = new StockStore();
				d = eStockRepo.findById_storeAndArtikel(
						penerimaanStoreFromOfficeNew.getId_store(),
						penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
				if (d == null) {
					StockStore new_insert = new StockStore();
					
					MasterProduct prod = new MasterProduct();
					prod = eMasterProductRepo.findByArticle(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
					
					new_insert.setId_store(penerimaanStoreFromOfficeNew.getId_store());
					new_insert.setLokasi_store(penerimaanStoreFromOfficeNew.getLokasi_store());
					new_insert.setFoto_barang(prod.getImage());
					new_insert.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
					new_insert.setArtikel(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
					new_insert.setKategori(prod.getKategori());
					new_insert.setNama_kategori(prod.getNama_kategori());
					new_insert.setType(prod.getType());
					new_insert.setType_name(prod.getType_name());
					new_insert.setNama_barang(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_barang());
					new_insert.setKuantitas(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
					new_insert.setHarga_jual(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getHarga_jual());
					new_insert.setHpp(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getHpp());
					new_insert.setRowstatus(1);
					eStockRepo.save(new_insert);
				} else {
					d.setKuantitas(d.getKuantitas() + penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
					eStockRepo.save(d);
				}
				
				StockOffice g = new StockOffice();
				g = eStockOfficeRepo.findById_officeAndArtikel(
						penerimaanStoreFromOfficeNew.getId_office(),
						penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
				g.setKuantitas(g.getKuantitas() - penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
				eStockOfficeRepo.save(g);
				
				PenyimpananKeluar f = new PenyimpananKeluar();
				f.setId_office(penerimaanStoreFromOfficeNew.getId_office());
				f.setLokasi_office(penerimaanStoreFromOfficeNew.getLokasi_office());
				f.setPengiriman_code(p.getPengiriman_code());
				f.setTanggal_keluar(penerimaanStoreFromOfficeNew.getTanggal_penerimaan());
				f.setId_store(penerimaanStoreFromOfficeNew.getId_store());
				f.setLokasi_store(penerimaanStoreFromOfficeNew.getLokasi_store());
				f.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
				f.setArtikel(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
				f.setKategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKategori());
				f.setNama_kategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_kategori());
				f.setType(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType());
				f.setType_name(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType_name());
				f.setNama_barang(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_barang());
				f.setKuantitas(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
				f.setKeterangan("Barang Dikirim Ke Store");
				f.setRowstatus(1);
				ePenyimpananRepo.save(f);
				
				PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
				h.setId_office(penerimaanStoreFromOfficeNew.getId_office());
				h.setLokasi_office(penerimaanStoreFromOfficeNew.getLokasi_office());
				h.setPenerimaan_code(p.getPenerimaan_code());
				h.setTanggal_masuk(penerimaanStoreFromOfficeNew.getTanggal_penerimaan());
				h.setId_store(penerimaanStoreFromOfficeNew.getId_store());
				h.setLokasi_store(penerimaanStoreFromOfficeNew.getLokasi_store());
				h.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
				h.setArtikel(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
				h.setKategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKategori());
				h.setNama_kategori(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_kategori());
				h.setType(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType());
				h.setType_name(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getType_name());
				h.setNama_barang(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_barang());
				h.setKuantitas(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
				h.setRowstatus(1);
				ePenyimpananStoreRepo.save(h);
			}
			
		}
		p.setDetailPenerimaanList(details);
		return eRepo.save(p);
	}

}
