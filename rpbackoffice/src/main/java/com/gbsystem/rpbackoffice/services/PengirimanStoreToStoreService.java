package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPengirimanStoreToStore;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStore;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
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
	
	@Autowired
	private MasterProductRepository eMasterProductRepository;
	
	public PengirimanStoreToStore savePengirimanStore(PengirimanStoreToStore pengirimanStoreToStore) {
		
		PengirimanStoreToStore p = new PengirimanStoreToStore();
		List<DetailPengirimanStoreToStore> details = new ArrayList<>();
		String code_pengiriman = "PSS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		
		Date tanggal_pengiriman = new Date();
		
		p.setPengiriman_code(code_pengiriman);
		p.setTanggal_pengiriman(tanggal_pengiriman);
		p.setId_store_asal(pengirimanStoreToStore.getId_store_asal());
		p.setLokasi_store_asal(pengirimanStoreToStore.getLokasi_store_asal());
		p.setId_store_tujuan(pengirimanStoreToStore.getId_store_tujuan());
		p.setLokasi_store_tujuan(pengirimanStoreToStore.getLokasi_store_tujuan());
		p.setId_karyawan(pengirimanStoreToStore.getId_karyawan());
		p.setNama_karyawan(pengirimanStoreToStore.getNama_karyawan());
		p.setKeterangan(pengirimanStoreToStore.getKeterangan());
		p.setRowstatus(1);
		
		for(int i = 0; i < pengirimanStoreToStore.getDetailPengirimanList().size(); i++) {
			DetailPengirimanStoreToStore detail = new DetailPengirimanStoreToStore();
			MasterProduct prod = new MasterProduct();
			prod = eMasterProductRepository.findByArticle(pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
			
			detail.setTanggal_pengiriman(tanggal_pengiriman);
			detail.setPengiriman_code(code_pengiriman);
			detail.setSku_code(pengirimanStoreToStore.getDetailPengirimanList().get(i).getSku_code());
			detail.setArtikel(pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
			detail.setKategori(prod == null ? "" : prod.getKategori());
			detail.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
			detail.setType(prod == null ? 0 : prod.getType());
			detail.setType_name(prod == null ? "" : prod.getType_name());
			detail.setNama_barang(pengirimanStoreToStore.getDetailPengirimanList().get(i).getNama_barang());
			detail.setKuantitas(pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas());
			detail.setUkuran(prod == null ? "" : prod.getUkuran());
			detail.setHpp(prod == null ? 0 : prod.getHpp());
			detail.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
			detail.setRowstatus(1);
			detail.setPengirimanStoreToStore(p);
			details.add(detail);
			
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
		p.setId_karyawan(pengirimanStoreToStoreNew.getId_karyawan());
		p.setNama_karyawan(pengirimanStoreToStoreNew.getNama_karyawan());
		p.setKeterangan(pengirimanStoreToStoreNew.getKeterangan());
		p.setRowstatus(1);
		for(int i = 0; i < pengirimanStoreToStoreNew.getDetailPengirimanList().size(); i++) {
			DetailPengirimanStoreToStore detail_update = new DetailPengirimanStoreToStore();
			MasterProduct prod = new MasterProduct();
			prod = eMasterProductRepository.findByArticle(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getArtikel());
			
			detail_update.setTanggal_pengiriman(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getTanggal_pengiriman());
			detail_update.setPengiriman_code(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getPengiriman_code());
			detail_update.setSku_code(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getSku_code());
			detail_update.setArtikel(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getArtikel());
			detail_update.setKategori(prod.getKategori() == null ? "" : prod.getKategori());
			detail_update.setNama_kategori(prod.getNama_kategori() == null ? "" : prod.getNama_kategori());
			detail_update.setType(prod.getType() == null ? 0 : prod.getType());
			detail_update.setType_name(prod.getType_name() == null ? "" : prod.getType_name());
			detail_update.setNama_barang(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getNama_barang());
			detail_update.setKuantitas(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
			detail_update.setUkuran(prod.getUkuran() == null ? "" : prod.getUkuran());
			detail_update.setHpp(prod.getHpp() == null ? 0 : prod.getHpp());
			detail_update.setHarga_jual(prod.getHarga_jual() == null ? 0 : prod.getHarga_jual());
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
				store_asal.setKategori(prod.getKategori() == null ? "" : prod.getKategori());
				store_asal.setNama_kategori(prod.getNama_kategori() == null ? "" : prod.getNama_kategori());
				store_asal.setType(prod.getType() == null ? 0 : prod.getType());
				store_asal.setType_name(prod.getType_name() == null ? "" : prod.getType_name());
				store_asal.setNama_barang(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getNama_barang());
				store_asal.setKuantitas(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
				store_asal.setUkuran(prod.getUkuran() == null ? "" : prod.getUkuran());
				store_asal.setHpp(prod.getHpp() == null ? 0 : prod.getHpp());
				store_asal.setHarga_jual(prod.getHarga_jual() == null ? 0 : prod.getHarga_jual());
				store_asal.setRowstatus(1);
				ePenyimpananStoreKeluarRepo.save(store_asal);
				
				PenyimpananStoreMasuk store_tujuan = new PenyimpananStoreMasuk();
				store_tujuan.setPenerimaan_code(pengirimanStoreToStoreNew.getPengiriman_code());
				store_tujuan.setLokasi_office("-");
				store_tujuan.setTanggal_masuk(pengirimanStoreToStoreNew.getTanggal_pengiriman());
				store_tujuan.setId_store(pengirimanStoreToStoreNew.getId_store_tujuan());
				store_tujuan.setLokasi_store(pengirimanStoreToStoreNew.getLokasi_store_tujuan());
				store_tujuan.setArtikel(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getArtikel());
				store_tujuan.setKategori(prod.getKategori() == null ? "" : prod.getKategori());
				store_tujuan.setNama_kategori(prod.getNama_kategori() == null ? "" : prod.getNama_kategori());
				store_tujuan.setType(prod.getType() == null ? 0 : prod.getType());
				store_tujuan.setType_name(prod.getType_name() == null ? "" : prod.getType_name());
				store_tujuan.setNama_barang(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getNama_barang());
				store_tujuan.setKuantitas(pengirimanStoreToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
				store_tujuan.setUkuran(prod.getUkuran() == null ? "" : prod.getUkuran());
				store_tujuan.setHpp(prod.getHpp() == null ? 0 : prod.getHpp());
				store_tujuan.setHarga_jual(prod.getHarga_jual() == null ? 0 : prod.getHarga_jual());
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
