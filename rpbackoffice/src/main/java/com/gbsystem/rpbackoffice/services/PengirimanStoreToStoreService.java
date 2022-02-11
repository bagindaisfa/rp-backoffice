package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPengirimanStoreToStore;
import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStore;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.repository.PengirimanStoreToStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreKeluarRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PengirimanStoreToStoreService {
	
	@Autowired
	private PengirimanStoreToStoreRepository eRepo;
	
	@Autowired
	private StockStoreRepository eStockRepo;
	
	@Autowired
	private PenyimpananStoreKeluarRepository ePenyimpananStoreKeluarRepo;
	
	@Autowired
	private PenyimpananStoreMasukRepository ePenyimpananStoreMasukRepo;
	
	public PengirimanStoreToStore savePengirimanStore(PengirimanStoreToStore pengirimanStoreToStore) {
		
		PengirimanStoreToStore p = new PengirimanStoreToStore();
		List<DetailPengirimanStoreToStore> details = new ArrayList<>();
		String code_pengiriman = "PSS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		String code_pengiriman_store = code_pengiriman + "S";
		Date tanggal_pengiriman = pengirimanStoreToStore.getTanggal_pengiriman();
		
		p.setPengiriman_code(code_pengiriman);
		p.setTanggal_pengiriman(tanggal_pengiriman);
		p.setId_store_asal(pengirimanStoreToStore.getId_store_asal());
		p.setLokasi_store_asal(pengirimanStoreToStore.getLokasi_store_asal());
		p.setId_store_tujuan(pengirimanStoreToStore.getId_store_tujuan());
		p.setLokasi_store_tujuan(pengirimanStoreToStore.getLokasi_store_tujuan());
		p.setRowstatus(1);
		
		for(int i = 0; i < pengirimanStoreToStore.getDetailPengirimanList().size(); i++) {
			DetailPengirimanStoreToStore detail = new DetailPengirimanStoreToStore();
			detail.setTanggal_pengiriman(tanggal_pengiriman);
			detail.setPengiriman_code(code_pengiriman);
			detail.setArtikel(pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
			detail.setKategori(pengirimanStoreToStore.getDetailPengirimanList().get(i).getKategori());
			detail.setNama_kategori(pengirimanStoreToStore.getDetailPengirimanList().get(i).getNama_kategori());
			detail.setType(pengirimanStoreToStore.getDetailPengirimanList().get(i).getType());
			detail.setType_name(pengirimanStoreToStore.getDetailPengirimanList().get(i).getType_name());
			detail.setNama_barang(pengirimanStoreToStore.getDetailPengirimanList().get(i).getNama_barang());
			detail.setKuantitas(pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas());
			detail.setUkuran(pengirimanStoreToStore.getDetailPengirimanList().get(i).getUkuran());
			detail.setHpp(pengirimanStoreToStore.getDetailPengirimanList().get(i).getHpp());
			detail.setHarga_jual(pengirimanStoreToStore.getDetailPengirimanList().get(i).getHarga_jual());
			detail.setRowstatus(1);
			detail.setPengirimanStoreToStore(p);
			details.add(detail);
			
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndArtikel(
					pengirimanStoreToStore.getId_store_asal(),
	    			pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
			d.setKuantitas(d.getKuantitas() - pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
	    	StockStore prevStoreTujuan = new StockStore();
	    	prevStoreTujuan = eStockRepo.findById_storeAndArtikel(
	    			pengirimanStoreToStore.getId_store_tujuan(),
	    			pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
	    	if (prevStoreTujuan == null) {
	    		StockStore new_insert = new StockStore();
	    		new_insert.setKuantitas(pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas());
	    		new_insert.setId_store(pengirimanStoreToStore.getId_store_tujuan());
	    		new_insert.setLokasi_store(pengirimanStoreToStore.getLokasi_store_tujuan());
	    		new_insert.setArtikel(pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
	    		new_insert.setKategori(pengirimanStoreToStore.getDetailPengirimanList().get(i).getKategori());
	    		new_insert.setNama_kategori(pengirimanStoreToStore.getDetailPengirimanList().get(i).getNama_kategori());
	    		new_insert.setType(pengirimanStoreToStore.getDetailPengirimanList().get(i).getType());
	    		new_insert.setType_name(pengirimanStoreToStore.getDetailPengirimanList().get(i).getType_name());
	    		new_insert.setNama_barang(pengirimanStoreToStore.getDetailPengirimanList().get(i).getNama_barang());
	    		new_insert.setKuantitas(pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas());
	    		new_insert.setUkuran(pengirimanStoreToStore.getDetailPengirimanList().get(i).getUkuran());
	    		new_insert.setHpp(pengirimanStoreToStore.getDetailPengirimanList().get(i).getHpp());
	    		new_insert.setHarga_jual(pengirimanStoreToStore.getDetailPengirimanList().get(i).getHarga_jual());
	    		new_insert.setRowstatus(1);
	    		eStockRepo.save(new_insert);
			} else {
				prevStoreTujuan.setKuantitas(prevStoreTujuan.getKuantitas() + pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas());
				eStockRepo.save(prevStoreTujuan);
			}
			
			PenyimpananStoreKeluar store_asal = new PenyimpananStoreKeluar();
			store_asal.setPengiriman_code(code_pengiriman_store);
			store_asal.setLokasi_office("-");
			store_asal.setTanggal_keluar(tanggal_pengiriman);
			store_asal.setId_store(pengirimanStoreToStore.getId_store_asal());
			store_asal.setLokasi_store(pengirimanStoreToStore.getLokasi_store_asal());
			store_asal.setArtikel(pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
			store_asal.setKategori(pengirimanStoreToStore.getDetailPengirimanList().get(i).getKategori());
			store_asal.setNama_kategori(pengirimanStoreToStore.getDetailPengirimanList().get(i).getNama_kategori());
			store_asal.setType(pengirimanStoreToStore.getDetailPengirimanList().get(i).getType());
			store_asal.setType_name(pengirimanStoreToStore.getDetailPengirimanList().get(i).getType_name());
			store_asal.setNama_barang(pengirimanStoreToStore.getDetailPengirimanList().get(i).getNama_barang());
			store_asal.setKuantitas(pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas());
			store_asal.setUkuran(pengirimanStoreToStore.getDetailPengirimanList().get(i).getUkuran());
			store_asal.setHpp(pengirimanStoreToStore.getDetailPengirimanList().get(i).getHpp());
			store_asal.setHarga_jual(pengirimanStoreToStore.getDetailPengirimanList().get(i).getHarga_jual());
			store_asal.setRowstatus(1);
			ePenyimpananStoreKeluarRepo.save(store_asal);
			
			PenyimpananStoreMasuk store_tujuan = new PenyimpananStoreMasuk();
			store_tujuan.setPenerimaan_code(code_pengiriman_store);
			store_tujuan.setLokasi_office("-");
			store_tujuan.setTanggal_masuk(tanggal_pengiriman);
			store_tujuan.setId_store(pengirimanStoreToStore.getId_store_tujuan());
			store_tujuan.setLokasi_store(pengirimanStoreToStore.getLokasi_store_tujuan());
			store_tujuan.setArtikel(pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
			store_tujuan.setKategori(pengirimanStoreToStore.getDetailPengirimanList().get(i).getKategori());
			store_tujuan.setNama_kategori(pengirimanStoreToStore.getDetailPengirimanList().get(i).getNama_kategori());
			store_tujuan.setType(pengirimanStoreToStore.getDetailPengirimanList().get(i).getType());
			store_tujuan.setType_name(pengirimanStoreToStore.getDetailPengirimanList().get(i).getType_name());
			store_tujuan.setNama_barang(pengirimanStoreToStore.getDetailPengirimanList().get(i).getNama_barang());
			store_tujuan.setKuantitas(pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas());
			store_tujuan.setUkuran(pengirimanStoreToStore.getDetailPengirimanList().get(i).getUkuran());
			store_tujuan.setHpp(pengirimanStoreToStore.getDetailPengirimanList().get(i).getHpp());
			store_tujuan.setHarga_jual(pengirimanStoreToStore.getDetailPengirimanList().get(i).getHarga_jual());
			store_tujuan.setRowstatus(1);
			ePenyimpananStoreMasukRepo.save(store_tujuan);
			
		}

		p.setDetailPengirimanList(details);
		return eRepo.save(p);
	}

	public List<PengirimanStoreToStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PengirimanStoreToStore> getAllPengirimanStore(){

		return eRepo.findByRowstatus(1);
	}
	
	public PengirimanStoreToStore deletePengirimanStoreById(Long id)
    {
		PengirimanStoreToStore p = new PengirimanStoreToStore();
		p = eRepo.findById(id).get();
		List<DetailPengirimanStoreToStore> details = new ArrayList<>();
    	p.setRowstatus(0);
    	
    	List<PenyimpananStoreKeluar> j = new ArrayList<>();
		j = ePenyimpananStoreKeluarRepo.findByPengiriman_code(p.getPengiriman_code()+"S");
		for (int y = 0; y < j.size(); y++) {
			j.get(y).setRowstatus(0);
			ePenyimpananStoreKeluarRepo.save(j.get(y));
		}
		
		List<PenyimpananStoreMasuk> h = new ArrayList<>();
		h = ePenyimpananStoreMasukRepo.findByPenerimaan_code(p.getPengiriman_code()+"S");
		for (int x = 0; x < h.size(); x++) {
			h.get(x).setRowstatus(0);
			ePenyimpananStoreMasukRepo.save(h.get(x));	
		}
		
    	for(int i = 0; i < p.getDetailPengirimanList().size(); i++) {
    		p.getDetailPengirimanList().get(i).setRowstatus(0);
    		p.getDetailPengirimanList().get(i).setPengirimanStoreToStore(p);
			details.add(p.getDetailPengirimanList().get(i));
			
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndArtikel(
					p.getId_store_asal(),
	    			p.getDetailPengirimanList().get(i).getArtikel());
			d.setKuantitas(d.getKuantitas() + p.getDetailPengirimanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
			StockStore g = new StockStore();
			g = eStockRepo.findById_storeAndArtikel(
					p.getId_store_tujuan(),
	    			p.getDetailPengirimanList().get(i).getArtikel());
			 
			g.setKuantitas(g.getKuantitas() - p.getDetailPengirimanList().get(i).getKuantitas());
			eStockRepo.save(g);
		}
    	p.setDetailPengirimanList(details);
		return eRepo.save(p);  
    }
	
	public PengirimanStoreToStore update(PengirimanStoreToStore pengirimanStoreToStore) {
		PengirimanStoreToStore p = new PengirimanStoreToStore();
		p = eRepo.findById(pengirimanStoreToStore.getId()).get();
		
		ePenyimpananStoreKeluarRepo.deleteStoreKeluar(pengirimanStoreToStore.getPengiriman_code()+"S");
		ePenyimpananStoreMasukRepo.deleteStoreMasuk(pengirimanStoreToStore.getPengiriman_code()+"S");
		
		for(int i = 0; i < p.getDetailPengirimanList().size(); i++) {
			
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndArtikel(
					p.getId_store_asal(),
	    			p.getDetailPengirimanList().get(i).getArtikel());
			d.setKuantitas(d.getKuantitas() + p.getDetailPengirimanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
			StockStore g = new StockStore();
			g = eStockRepo.findById_storeAndArtikel(
					p.getId_store_tujuan(),
	    			p.getDetailPengirimanList().get(i).getArtikel());
			 
			g.setKuantitas(g.getKuantitas() - p.getDetailPengirimanList().get(i).getKuantitas());
			eStockRepo.save(g);
		}
		
		return saveUpdate(pengirimanStoreToStore);
		
	}

	private PengirimanStoreToStore saveUpdate(PengirimanStoreToStore pengirimanStoreToStoreNew) {

		PengirimanStoreToStore p = new PengirimanStoreToStore();
    	p = eRepo.findById(pengirimanStoreToStoreNew.getId()).get();
    	List<DetailPengirimanStoreToStore> details = new ArrayList<>();
    	
    	p.setPengiriman_code(p.getPengiriman_code());
		p.setTanggal_pengiriman(pengirimanStoreToStoreNew.getTanggal_pengiriman());
		p.setId_store_asal(pengirimanStoreToStoreNew.getId_store_asal());
		p.setLokasi_store_asal(pengirimanStoreToStoreNew.getLokasi_store_asal());
		p.setId_store_tujuan(pengirimanStoreToStoreNew.getId_store_tujuan());
		p.setLokasi_store_tujuan(pengirimanStoreToStoreNew.getLokasi_store_tujuan());
		p.setRowstatus(1);
		for(int i = 0; i < pengirimanStoreToStoreNew.getDetailPengirimanList().size(); i++) {
			DetailPengirimanStoreToStore detail_update = new DetailPengirimanStoreToStore();
			
			detail_update.setTanggal_pengiriman(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getTanggal_pengiriman());
			detail_update.setPengiriman_code(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getPengiriman_code());
			detail_update.setArtikel(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getArtikel());
			detail_update.setKategori(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getKategori());
			detail_update.setNama_kategori(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getNama_kategori());
			detail_update.setType(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getType());
			detail_update.setType_name(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getType_name());
			detail_update.setNama_barang(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getNama_barang());
			detail_update.setKuantitas(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
			detail_update.setUkuran(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getUkuran());
			detail_update.setHpp(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getHpp());
			detail_update.setHarga_jual(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getHarga_jual());
			detail_update.setRowstatus(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getRowstatus());
			
			if (pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getRowstatus() == 1) {
				StockStore e = new StockStore();
				e = eStockRepo.findById_storeAndArtikel(
						pengirimanStoreToStoreNew.getId_store_asal(),
						pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getArtikel());
				
				e.setKuantitas(e.getKuantitas() - pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
				eStockRepo.save(e);
		
				StockStore f = new StockStore();
				f = eStockRepo.findById_storeAndArtikel(
						pengirimanStoreToStoreNew.getId_store_tujuan(),
						pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getArtikel());
				f.setKuantitas(f.getKuantitas() + pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
				eStockRepo.save(f);
			
				PenyimpananStoreKeluar store_asal = new PenyimpananStoreKeluar();
				store_asal.setPengiriman_code(pengirimanStoreToStoreNew.getPengiriman_code());
				store_asal.setLokasi_office("-");
				store_asal.setTanggal_keluar(pengirimanStoreToStoreNew.getTanggal_pengiriman());
				store_asal.setId_store(pengirimanStoreToStoreNew.getId_store_asal());
				store_asal.setLokasi_store(pengirimanStoreToStoreNew.getLokasi_store_asal());
				store_asal.setArtikel(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getArtikel());
				store_asal.setKategori(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getKategori());
				store_asal.setNama_kategori(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getNama_kategori());
				store_asal.setType(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getType());
				store_asal.setType_name(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getType_name());
				store_asal.setNama_barang(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getNama_barang());
				store_asal.setKuantitas(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
				store_asal.setUkuran(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getUkuran());
				store_asal.setHpp(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getHpp());
				store_asal.setHarga_jual(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getHarga_jual());
				store_asal.setRowstatus(1);
				ePenyimpananStoreKeluarRepo.save(store_asal);
				
				PenyimpananStoreMasuk store_tujuan = new PenyimpananStoreMasuk();
				store_tujuan.setPenerimaan_code(pengirimanStoreToStoreNew.getPengiriman_code());
				store_tujuan.setLokasi_office("-");
				store_tujuan.setTanggal_masuk(pengirimanStoreToStoreNew.getTanggal_pengiriman());
				store_tujuan.setId_store(pengirimanStoreToStoreNew.getId_store_tujuan());
				store_tujuan.setLokasi_store(pengirimanStoreToStoreNew.getLokasi_store_tujuan());
				store_tujuan.setArtikel(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getArtikel());
				store_tujuan.setKategori(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getKategori());
				store_tujuan.setNama_kategori(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getNama_kategori());
				store_tujuan.setType(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getType());
				store_tujuan.setType_name(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getType_name());
				store_tujuan.setNama_barang(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getNama_barang());
				store_tujuan.setKuantitas(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
				store_tujuan.setUkuran(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getUkuran());
				store_tujuan.setHpp(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getHpp());
				store_tujuan.setHarga_jual(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getHarga_jual());
				store_tujuan.setRowstatus(1);
				ePenyimpananStoreMasukRepo.save(store_tujuan);
			}
			detail_update.setPengirimanStoreToStore(p);
			details.add(detail_update);
		}
		p.setDetailPengirimanList(details);
		return eRepo.save(p);
	}
}
