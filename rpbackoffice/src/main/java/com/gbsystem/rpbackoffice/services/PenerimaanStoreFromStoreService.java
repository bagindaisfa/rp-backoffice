package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPenerimaanStoreFromStore;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.PenerimaanStoreFromStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PenerimaanStoreFromStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreKeluarRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PenerimaanStoreFromStoreService {
	
	@Autowired
	private PenerimaanStoreFromStoreRepository eRepo;

	@Autowired
	private StockStoreRepository eStockRepo;
	
	@Autowired
	private StockStoreRepository eStockStoreRepo;
	
	@Autowired
	private PenyimpananStoreKeluarRepository ePenyimpananStoreKeluarRepo;
	
	@Autowired
	private PenyimpananStoreMasukRepository ePenyimpananStoreRepo;
	
	@Autowired
	private MasterProductRepository eMasterProductRepository;
	
	public PenerimaanStoreFromStore savePenerimaanStore(PenerimaanStoreFromStore penerimaanStoreFromStore) {
		
		String code_penerimaan = "POS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		String code_penerimaan_store = code_penerimaan + "S";
		
		PenerimaanStoreFromStore p = new PenerimaanStoreFromStore();
		List<DetailPenerimaanStoreFromStore> details = new ArrayList<>();
		
		p.setPenerimaan_code(code_penerimaan);
		p.setTanggal_penerimaan(penerimaanStoreFromStore.getTanggal_penerimaan());
		p.setId_store_asal(penerimaanStoreFromStore.getId_store_asal());
		p.setLokasi_store_asal(penerimaanStoreFromStore.getLokasi_store_asal());
		p.setId_store_tujuan(penerimaanStoreFromStore.getId_store_tujuan());
		p.setLokasi_store_tujuan(penerimaanStoreFromStore.getLokasi_store_tujuan());
		p.setRowstatus(1);
		for(int i = 0; i < penerimaanStoreFromStore.getDetailPenerimaanList().size(); i++) {
			DetailPenerimaanStoreFromStore detail = new DetailPenerimaanStoreFromStore();
			MasterProduct prod = new MasterProduct();
			prod = eMasterProductRepository.findBySkuCode(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getSku_code());
			
			detail.setPenerimaan_code(code_penerimaan);
			detail.setTanggal_penerimaan(penerimaanStoreFromStore.getTanggal_penerimaan());
			detail.setArtikel(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getArtikel());
			detail.setNama_barang(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getNama_barang());
			detail.setKuantitas(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getKuantitas());
			detail.setSku_code(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getSku_code());
			detail.setKeterangan(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getKeterangan());
			detail.setRowstatus(1);
			detail.setPenerimaanStoreFromStore(p);
			details.add(detail);
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndSku_code(
					penerimaanStoreFromStore.getId_store_tujuan(),
					penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getSku_code());
			if (d == null) {
				StockStore new_insert = new StockStore();
				new_insert.setKuantitas(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getKuantitas());
	    		new_insert.setId_store(penerimaanStoreFromStore.getId_store_tujuan());
	    		new_insert.setLokasi_store(penerimaanStoreFromStore.getLokasi_store_tujuan());
	    		new_insert.setSku_code(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getSku_code());
	    		new_insert.setArtikel(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getArtikel());
	    		new_insert.setKategori(prod == null ? "" : prod.getKategori());
	    		new_insert.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
	    		new_insert.setType(prod == null ? 0 : prod.getType());
	    		new_insert.setType_name(prod == null ? "" : prod.getType_name());
	    		new_insert.setNama_barang(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getNama_barang());
	    		new_insert.setKuantitas(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getKuantitas());
	    		new_insert.setUkuran(prod == null ? "" : prod.getUkuran());
	    		new_insert.setHpp(prod == null ? 0 : prod.getHpp());
	    		new_insert.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
	    		new_insert.setRowstatus(1);
				eStockRepo.save(new_insert);
			} else {
				d.setKuantitas(d.getKuantitas() + penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getKuantitas());
				eStockRepo.save(d);
			}
			
			StockStore g = new StockStore();
			g = eStockStoreRepo.findById_storeAndSku_code(
					penerimaanStoreFromStore.getId_store_asal(),
					penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getSku_code());
			g.setKuantitas(g.getKuantitas() - penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getKuantitas());
			eStockStoreRepo.save(g);
			
			PenyimpananStoreKeluar f = new PenyimpananStoreKeluar();
			f.setId_store(penerimaanStoreFromStore.getId_store_tujuan());
			f.setLokasi_store(penerimaanStoreFromStore.getLokasi_store_tujuan());
			f.setPengiriman_code(code_penerimaan);
			f.setTanggal_keluar(new Date());
			f.setSku_code(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getSku_code());
			f.setArtikel(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getArtikel());
			f.setKategori(prod == null ? "" : prod.getKategori());
			f.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
			f.setType(prod == null ? 0 : prod.getType());
			f.setType_name(prod == null ? "" : prod.getType_name());
			f.setNama_barang(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getNama_barang());
			f.setKuantitas(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getKuantitas());
			f.setUkuran(prod == null ? "" : prod.getUkuran());
			f.setHpp(prod == null ? 0 : prod.getHpp());
			f.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
			f.setRowstatus(1);
			ePenyimpananStoreKeluarRepo.save(f);
			
			PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
			h.setPenerimaan_code(code_penerimaan_store);
			h.setTanggal_masuk(new Date());
			h.setSku_code(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getSku_code());
			h.setArtikel(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getArtikel());
			h.setKategori(prod == null ? "" : prod.getKategori());
			h.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
			h.setType(prod == null ? 0 : prod.getType());
			h.setType_name(prod == null ? "" : prod.getType_name());
			h.setNama_barang(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getNama_barang());
			h.setKuantitas(penerimaanStoreFromStore.getDetailPenerimaanList().get(i).getKuantitas());
			h.setUkuran(prod == null ? "" : prod.getUkuran());
			h.setHpp(prod == null ? 0 : prod.getHpp());
			h.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
			h.setRowstatus(1);
			ePenyimpananStoreRepo.save(h);
			
		}
		
		p.setDetailPenerimaanList(details);
		return eRepo.save(p);
	}

	public List<PenerimaanStoreFromStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PenerimaanStoreFromStore> getAllPenerimaanFromStore(){

		return eRepo.findByRowstatus(1);
	}
	
	public PenerimaanStoreFromStore deletePenerimaanFromStoreById(Long id)
    {
		PenerimaanStoreFromStore p = new PenerimaanStoreFromStore();
		p = eRepo.findById(id).get();
		List<DetailPenerimaanStoreFromStore> details = new ArrayList<>();
		p.setRowstatus(0);
		
		List<PenyimpananStoreKeluar> f = new ArrayList<>();
		f = ePenyimpananStoreKeluarRepo.findByPengiriman_code(p.getPenerimaan_code());
		for(int s = 0; s < f.size(); s++) {
			f.get(s).setRowstatus(0);
			ePenyimpananStoreKeluarRepo.save(f.get(s));
		}
		
		List<PenyimpananStoreMasuk> h = new ArrayList<>();
		h = ePenyimpananStoreRepo.findByPenerimaan_code(p.getPenerimaan_code());
		for(int t = 0; t < h.size(); t++) {
			h.get(t).setRowstatus(0);
			ePenyimpananStoreRepo.save(h.get(t));
		}
		for(int i = 0; i < p.getDetailPenerimaanList().size(); i++) {
			p.getDetailPenerimaanList().get(i).setRowstatus(0);
			p.getDetailPenerimaanList().get(i).setPenerimaanStoreFromStore(p);
			details.add(p.getDetailPenerimaanList().get(i));
			
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndSku_code(
					p.getId_store_asal(),
					p.getDetailPenerimaanList().get(i).getSku_code());
			d.setKuantitas(d.getKuantitas() - p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
			StockStore g = new StockStore();
			g = eStockStoreRepo.findById_storeAndSku_code(
					p.getId_store_tujuan(),
					p.getDetailPenerimaanList().get(i).getSku_code());
			g.setKuantitas(g.getKuantitas() + p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockStoreRepo.save(g);
			
		}
		
		p.setDetailPenerimaanList(details);
    	return eRepo.save(p);
    }
	
	public PenerimaanStoreFromStore update(PenerimaanStoreFromStore penerimaanStoreFromStore) {
		PenerimaanStoreFromStore p = new PenerimaanStoreFromStore();
    	p = eRepo.findById(penerimaanStoreFromStore.getId()).get();
    	
    	ePenyimpananStoreKeluarRepo.deleteStoreKeluar(penerimaanStoreFromStore.getPenerimaan_code());
		ePenyimpananStoreRepo.deleteStoreMasuk(penerimaanStoreFromStore.getPenerimaan_code());
		
		for(int i = 0; i < p.getDetailPenerimaanList().size(); i++) {
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndSku_code(
					p.getId_store_asal(),
					p.getDetailPenerimaanList().get(i).getSku_code());
			d.setKuantitas(d.getKuantitas() - p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
			StockStore g = new StockStore();
			g = eStockStoreRepo.findById_storeAndSku_code(
					p.getId_store_tujuan(),
					p.getDetailPenerimaanList().get(i).getSku_code());
			g.setKuantitas(g.getKuantitas() + p.getDetailPenerimaanList().get(i).getKuantitas());
			eStockStoreRepo.save(g);
		}
		
		return saveUpdate(penerimaanStoreFromStore);
	}

	private PenerimaanStoreFromStore saveUpdate(PenerimaanStoreFromStore penerimaanStoreFromStoreNew) {
		PenerimaanStoreFromStore p = new PenerimaanStoreFromStore();
    	p = eRepo.findById(penerimaanStoreFromStoreNew.getId()).get();
    	
    	List<DetailPenerimaanStoreFromStore> details = new ArrayList<>();
		
		p.setPenerimaan_code(penerimaanStoreFromStoreNew.getPenerimaan_code());
		p.setTanggal_penerimaan(penerimaanStoreFromStoreNew.getTanggal_penerimaan());
		p.setId_store_asal(penerimaanStoreFromStoreNew.getId_store_asal());
		p.setLokasi_store_asal(penerimaanStoreFromStoreNew.getLokasi_store_asal());
		p.setId_store_tujuan(penerimaanStoreFromStoreNew.getId_store_tujuan());
		p.setLokasi_store_tujuan(penerimaanStoreFromStoreNew.getLokasi_store_tujuan());
		p.setRowstatus(penerimaanStoreFromStoreNew.getRowstatus());
		
		for(int i = 0; i < penerimaanStoreFromStoreNew.getDetailPenerimaanList().size(); i++) {
			DetailPenerimaanStoreFromStore detail_update = new DetailPenerimaanStoreFromStore();
			MasterProduct prod = new MasterProduct();
			prod = eMasterProductRepository.findBySkuCode(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getSku_code());
			
			detail_update.setArtikel(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getArtikel());
			detail_update.setNama_barang(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getNama_barang());
			detail_update.setKuantitas(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getKuantitas());
			detail_update.setSku_code(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getSku_code());
			detail_update.setKeterangan(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getKeterangan());
			detail_update.setRowstatus(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getRowstatus());
			
			if (penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getRowstatus() == 1) {
				
				StockStore d = new StockStore();
				d = eStockRepo.findById_storeAndSku_code(
						penerimaanStoreFromStoreNew.getId_store_tujuan(),
						penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getSku_code());
				d.setKuantitas(d.getKuantitas() + penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getKuantitas());
				eStockRepo.save(d);
				
				StockStore g = new StockStore();
				g = eStockStoreRepo.findById_storeAndSku_code(
						penerimaanStoreFromStoreNew.getId_store_asal(),
						penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getSku_code());
				g.setKuantitas(g.getKuantitas() - penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getKuantitas());
				eStockStoreRepo.save(g);
			
				PenyimpananStoreKeluar f = new PenyimpananStoreKeluar();
				f.setId_store(penerimaanStoreFromStoreNew.getId_store_tujuan());
				f.setLokasi_store(penerimaanStoreFromStoreNew.getLokasi_store_tujuan());
				f.setPengiriman_code(penerimaanStoreFromStoreNew.getPenerimaan_code());
				f.setTanggal_keluar(new Date());
				f.setSku_code(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getSku_code());
				f.setArtikel(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getArtikel());
				f.setKategori(prod == null ? "" : prod.getKategori());
				f.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
				f.setType(prod == null ? 0 : prod.getType());
				f.setType_name(prod == null ? "" : prod.getType_name());
				f.setNama_barang(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getNama_barang());
				f.setKuantitas(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getKuantitas());
				f.setUkuran(prod == null ? "" : prod.getUkuran());
				f.setHpp(prod == null ? 0 : prod.getHpp());
				f.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
				f.setRowstatus(1);
				ePenyimpananStoreKeluarRepo.save(f);
				
				PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
				h.setPenerimaan_code(penerimaanStoreFromStoreNew.getPenerimaan_code());
				h.setTanggal_masuk(new Date());
				h.setSku_code(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getSku_code());
				h.setArtikel(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getArtikel());
				h.setKategori(prod == null ? "" : prod.getKategori());
				h.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
				h.setType(prod == null ? 0 : prod.getType());
				h.setType_name(prod == null ? "" : prod.getType_name());
				h.setNama_barang(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getNama_barang());
				h.setKuantitas(penerimaanStoreFromStoreNew.getDetailPenerimaanList().get(i).getKuantitas());
				h.setUkuran(prod == null ? "" : prod.getUkuran());
				h.setHpp(prod == null ? 0 : prod.getHpp());
				h.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
				h.setRowstatus(1);
				ePenyimpananStoreRepo.save(h);
			}
			detail_update.setPenerimaanStoreFromStore(p);
			details.add(detail_update);	
		}
		p.setDetailPenerimaanList(details);
		return eRepo.save(p);
	}

}
