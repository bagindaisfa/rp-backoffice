package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPenerimaanStoreFromOffice;
import com.gbsystem.rpbackoffice.entities.PenerimaanStoreFromOffice;
import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.PenerimaanStoreFromOfficeRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananKeluarRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PenerimaanStoreFromOfficeService {
	
	@Autowired
	private PenerimaanStoreFromOfficeRepository eRepo;

	@Autowired
	private StockStoreRepository eStockRepo;
	
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	
	@Autowired
	private PenyimpananKeluarRepository ePenyimpananRepo;
	
	@Autowired
	private PenyimpananStoreMasukRepository ePenyimpananStoreRepo;
	
	public PenerimaanStoreFromOffice savePenerimaanStore(PenerimaanStoreFromOffice penerimaanStoreFromOffice) {
		
		String code_penerimaan = "POS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		String code_penerimaan_store = code_penerimaan + "S";
		
		PenerimaanStoreFromOffice p = new PenerimaanStoreFromOffice();
		List<DetailPenerimaanStoreFromOffice> details = new ArrayList<>();
		
		p.setPenerimaan_code(code_penerimaan);
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
			detail.setNama_barang(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getNama_barang());
			detail.setKuantitas(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKuantitas());
			detail.setSku_code(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getSku_code());
			detail.setKeterangan(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKeterangan());
			detail.setRowstatus(1);
			detail.setPenerimaanStoreFromOffice(p);
			details.add(detail);
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndSku_code(
					penerimaanStoreFromOffice.getId_store(),
					penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getSku_code());
			if (d == null) {
				StockStore new_insert = new StockStore();
				new_insert.setId_store(penerimaanStoreFromOffice.getId_store());
				new_insert.setLokasi_store(penerimaanStoreFromOffice.getLokasi_store());
				new_insert.setSku_code(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getSku_code());
				new_insert.setArtikel(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getArtikel());
				new_insert.setNama_barang(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getNama_barang());
				new_insert.setKuantitas(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKuantitas());
				new_insert.setRowstatus(1);
				eStockRepo.save(new_insert);
			} else {
				d.setKuantitas(d.getKuantitas() + penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKuantitas());
				eStockRepo.save(d);
			}
			
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndSku_code(
					penerimaanStoreFromOffice.getId_office(),
					penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getSku_code());
			g.setKuantitas(g.getKuantitas() - penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKuantitas());
			eStockOfficeRepo.save(g);
			
			PenyimpananKeluar f = new PenyimpananKeluar();
			f.setId_office(penerimaanStoreFromOffice.getId_office());
			f.setLokasi_office(penerimaanStoreFromOffice.getLokasi_office());
			f.setPengiriman_code(code_penerimaan);
			f.setTanggal_keluar(new Date());
			f.setId_store(penerimaanStoreFromOffice.getId_store());
			f.setLokasi_store(penerimaanStoreFromOffice.getLokasi_store());
			f.setSku_code(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getSku_code());
			f.setArtikel(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getArtikel());
			f.setNama_barang(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getNama_barang());
			f.setKuantitas(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getKuantitas());
			f.setKeterangan("Barang Dikirim Ke Store");
			f.setRowstatus(1);
			ePenyimpananRepo.save(f);
			
			PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
			h.setId_office(penerimaanStoreFromOffice.getId_office());
			h.setLokasi_office(penerimaanStoreFromOffice.getLokasi_office());
			h.setPenerimaan_code(code_penerimaan_store);
			h.setTanggal_masuk(new Date());
			h.setId_store(penerimaanStoreFromOffice.getId_store());
			h.setLokasi_store(penerimaanStoreFromOffice.getLokasi_store());
			h.setSku_code(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getSku_code());
			h.setArtikel(penerimaanStoreFromOffice.getDetailPenerimaanList().get(i).getArtikel());
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
		f = ePenyimpananRepo.findByPengiriman_code(p.getPenerimaan_code());
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
			d = eStockRepo.findById_storeAndSku_code(
					p.getId_store(),
					p.getDetailPenerimaanList().get(i).getSku_code());
			d.setKuantitas(d.getKuantitas() - p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndSku_code(
					p.getId_office(),
					p.getDetailPenerimaanList().get(i).getSku_code());
			g.setKuantitas(g.getKuantitas() + p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockOfficeRepo.save(g);
			
		}
		
		p.setDetailPenerimaanList(details);
    	return eRepo.save(p);
    }
	
	public PenerimaanStoreFromOffice update(PenerimaanStoreFromOffice penerimaanStoreFromOffice) {
		PenerimaanStoreFromOffice p = new PenerimaanStoreFromOffice();
    	p = eRepo.findById(penerimaanStoreFromOffice.getId()).get();
    	
		ePenyimpananRepo.deleteStockKeluar(penerimaanStoreFromOffice.getPenerimaan_code());
		ePenyimpananStoreRepo.deleteStoreMasuk(penerimaanStoreFromOffice.getPenerimaan_code());
		
		for(int i = 0; i < p.getDetailPenerimaanList().size(); i++) {
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndSku_code(
					p.getId_store(),
					p.getDetailPenerimaanList().get(i).getSku_code());
			d.setKuantitas(d.getKuantitas() - p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndSku_code(
					p.getId_office(),
					p.getDetailPenerimaanList().get(i).getSku_code());
			g.setKuantitas(g.getKuantitas() + p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockOfficeRepo.save(g);
		}
		
		return saveUpdate(penerimaanStoreFromOffice);
	}

	private PenerimaanStoreFromOffice saveUpdate(PenerimaanStoreFromOffice penerimaanStoreFromOfficeNew) {
		PenerimaanStoreFromOffice p = new PenerimaanStoreFromOffice();
    	p = eRepo.findById(penerimaanStoreFromOfficeNew.getId()).get();
    	
    	List<DetailPenerimaanStoreFromOffice> details = new ArrayList<>();
		
		p.setPenerimaan_code(penerimaanStoreFromOfficeNew.getPenerimaan_code());
		p.setTanggal_penerimaan(penerimaanStoreFromOfficeNew.getTanggal_penerimaan());
		p.setId_office(penerimaanStoreFromOfficeNew.getId_office());
		p.setLokasi_office(penerimaanStoreFromOfficeNew.getLokasi_office());
		p.setId_store(penerimaanStoreFromOfficeNew.getId_store());
		p.setLokasi_store(penerimaanStoreFromOfficeNew.getLokasi_store());
		p.setRowstatus(penerimaanStoreFromOfficeNew.getRowstatus());
		
		for(int i = 0; i < penerimaanStoreFromOfficeNew.getDetailPenerimaanList().size(); i++) {
			DetailPenerimaanStoreFromOffice detail_update = new DetailPenerimaanStoreFromOffice();
			detail_update.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
			detail_update.setArtikel(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
			detail_update.setNama_barang(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_barang());
			detail_update.setKuantitas(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
			detail_update.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
			detail_update.setKeterangan(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKeterangan());
			detail_update.setRowstatus(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getRowstatus());
			
			if (penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getRowstatus() == 1) {
				
				StockStore d = new StockStore();
				d = eStockRepo.findById_storeAndSku_code(
						penerimaanStoreFromOfficeNew.getId_store(),
						penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
				d.setKuantitas(d.getKuantitas() + penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
				eStockRepo.save(d);
				
				StockOffice g = new StockOffice();
				g = eStockOfficeRepo.findById_officeAndSku_code(
						penerimaanStoreFromOfficeNew.getId_office(),
						penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
				g.setKuantitas(g.getKuantitas() - penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
				eStockOfficeRepo.save(g);
			
				PenyimpananKeluar f = new PenyimpananKeluar();
				f.setId_office(penerimaanStoreFromOfficeNew.getId_office());
				f.setLokasi_office(penerimaanStoreFromOfficeNew.getLokasi_office());
				f.setPengiriman_code(penerimaanStoreFromOfficeNew.getPenerimaan_code());
				f.setTanggal_keluar(new Date());
				f.setId_store(penerimaanStoreFromOfficeNew.getId_store());
				f.setLokasi_store(penerimaanStoreFromOfficeNew.getLokasi_store());
				f.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
				f.setArtikel(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
				f.setNama_barang(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_barang());
				f.setKuantitas(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
				f.setKeterangan("Barang Dikirim Ke Store");
				f.setRowstatus(1);
				ePenyimpananRepo.save(f);
				
				PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
				h.setId_office(penerimaanStoreFromOfficeNew.getId_office());
				h.setLokasi_office(penerimaanStoreFromOfficeNew.getLokasi_office());
				h.setPenerimaan_code(penerimaanStoreFromOfficeNew.getPenerimaan_code());
				h.setTanggal_masuk(new Date());
				h.setId_store(penerimaanStoreFromOfficeNew.getId_store());
				h.setLokasi_store(penerimaanStoreFromOfficeNew.getLokasi_store());
				h.setSku_code(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getSku_code());
				h.setArtikel(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getArtikel());
				h.setNama_barang(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getNama_barang());
				h.setKuantitas(penerimaanStoreFromOfficeNew.getDetailPenerimaanList().get(i).getKuantitas());
				h.setRowstatus(1);
				ePenyimpananStoreRepo.save(h);
			}
			detail_update.setPenerimaanStoreFromOffice(p);
			details.add(detail_update);	
		}
		p.setDetailPenerimaanList(details);
		return eRepo.save(p);
	}

}
