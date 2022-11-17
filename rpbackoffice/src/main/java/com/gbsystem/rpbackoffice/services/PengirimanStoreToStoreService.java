package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPengirimanStoreToStore;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.PenerimaanStoreFromStore;
import com.gbsystem.rpbackoffice.entities.PengirimanStoreToStore;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.DetailPengirimanStoreToStoreRepository;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PenerimaanStoreFromStoreRepository;
import com.gbsystem.rpbackoffice.repository.PengirimanStoreToStoreRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PengirimanStoreToStoreService {
	
	@Autowired
	private PengirimanStoreToStoreRepository eRepo;
	
	@Autowired
	private MasterProductRepository eMasterProductRepository;
	
	@Autowired
	private PenerimaanStoreFromStoreRepository ePenerimaanStoreFromStoreRepo;
	
	@Autowired
	private DetailPengirimanStoreToStoreRepository eDetailRepo;

	@Autowired
	private StockStoreRepository eStockRepo;
	
	public PengirimanStoreToStore savePengirimanStore(PengirimanStoreToStore pengirimanStoreToStore) {
		
		PengirimanStoreToStore p = new PengirimanStoreToStore();
		List<DetailPengirimanStoreToStore> details = new ArrayList<>();
		String code_pengiriman = "PSTS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		Double total_pindah = 0.0;
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
			StockStore checker = new StockStore();
			checker = eStockRepo.findById_storeAndArtikel(
					pengirimanStoreToStore.getId_store_asal(),
					pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
			if (checker != null && checker.getKuantitas() > 0.0) {
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
				total_pindah += pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas();
			}
		}
		p.setTotal_pindah(total_pindah);
		p.setDetailPengirimanList(details);
		return eRepo.save(p);
	}

	public List<PengirimanStoreToStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PengirimanStoreToStore> getAllPengirimanStore(){

		return eRepo.findByRowstatus(1);
	}
	
	public String deletePengirimanStoreById(Long id)
    {
		String result = "";
		PengirimanStoreToStore p = new PengirimanStoreToStore();
		p = eRepo.findById(id).get();
		
		PenerimaanStoreFromStore penerimaan = new PenerimaanStoreFromStore();
		penerimaan = ePenerimaanStoreFromStoreRepo.findByPengirimanCode(p.getPengiriman_code());
		
		if	(penerimaan != null) {
			result = "Pengiriman ini sudah diterima, tidak bisa didelete!";
		} else {
			result = "Berhasil didelete!";

			List<DetailPengirimanStoreToStore> details = new ArrayList<>();
	    	p.setRowstatus(0);
	    	
	    	for(int i = 0; i < p.getDetailPengirimanList().size(); i++) {
	    		p.getDetailPengirimanList().get(i).setRowstatus(0);
	    		p.getDetailPengirimanList().get(i).setPengirimanStoreToStore(p);
				details.add(p.getDetailPengirimanList().get(i));
			}
	    	p.setDetailPengirimanList(details);
	    	eRepo.save(p);
		}
		
		return result;
    }
	
	public void delete(Long id) {
		eDetailRepo.deleteById(id);
	}
	
	public String update(PengirimanStoreToStore pengirimanStoreToStore) {
		String result = "";
		Double total_pindah = 0.0;
		PenerimaanStoreFromStore penerimaan = new PenerimaanStoreFromStore();
		penerimaan = ePenerimaanStoreFromStoreRepo.findByPengirimanCode(pengirimanStoreToStore.getPengiriman_code());
		if	(penerimaan != null) {
			result = "Pengiriman ini sudah diterima, tidak bisa diupdate!";
		} else {
			PengirimanStoreToStore p = new PengirimanStoreToStore();
	    	p = eRepo.findById(pengirimanStoreToStore.getId()).get();
	    	
	    	List<DetailPengirimanStoreToStore> details = new ArrayList<>();
	    	
			p.setTanggal_pengiriman(pengirimanStoreToStore.getTanggal_pengiriman());
			p.setId_store_asal(pengirimanStoreToStore.getId_store_asal());
			p.setLokasi_store_asal(pengirimanStoreToStore.getLokasi_store_asal());
			p.setId_store_tujuan(pengirimanStoreToStore.getId_store_tujuan());
			p.setLokasi_store_tujuan(pengirimanStoreToStore.getLokasi_store_tujuan());
			p.setId_karyawan(pengirimanStoreToStore.getId_karyawan());
			p.setNama_karyawan(pengirimanStoreToStore.getNama_karyawan());
			p.setKeterangan(pengirimanStoreToStore.getKeterangan());
			p.setRowstatus(1);
			
			for(int i = 0; i < pengirimanStoreToStore.getDetailPengirimanList().size(); i++) {
				StockStore checker = new StockStore();
				checker = eStockRepo.findById_storeAndArtikel(
						pengirimanStoreToStore.getId_store_asal(),
						pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
				DetailPengirimanStoreToStore detail = new DetailPengirimanStoreToStore();
				
				if (pengirimanStoreToStore.getDetailPengirimanList().get(i).getId() == null) {
					detail = null;
				} else {
					detail = eDetailRepo.findById(pengirimanStoreToStore.getDetailPengirimanList().get(i).getId()).orElse(null);
					
				}
				
				if (detail != null) {
					MasterProduct prod = new MasterProduct();
					prod = eMasterProductRepository.findByArticle(pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
					
					detail.setTanggal_pengiriman(pengirimanStoreToStore.getTanggal_pengiriman());
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
					detail.setRowstatus(pengirimanStoreToStore.getDetailPengirimanList().get(i).getRowstatus());
					detail.setPengirimanStoreToStore(p);
					details.add(detail);
					total_pindah += pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas();
				} else {
					if (checker != null && checker.getKuantitas() > 0.0) {
						DetailPengirimanStoreToStore new_detail = new DetailPengirimanStoreToStore();
						MasterProduct prod = new MasterProduct();
						prod = eMasterProductRepository.findByArticle(pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
						
						new_detail.setTanggal_pengiriman(pengirimanStoreToStore.getTanggal_pengiriman());
						new_detail.setPengiriman_code(p.getPengiriman_code());
						new_detail.setSku_code(pengirimanStoreToStore.getDetailPengirimanList().get(i).getSku_code());
						new_detail.setArtikel(pengirimanStoreToStore.getDetailPengirimanList().get(i).getArtikel());
						new_detail.setKategori(prod == null ? "" : prod.getKategori());
						new_detail.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
						new_detail.setType(prod == null ? 0 : prod.getType());
						new_detail.setType_name(prod == null ? "" : prod.getType_name());
						new_detail.setNama_barang(pengirimanStoreToStore.getDetailPengirimanList().get(i).getNama_barang());
						new_detail.setKuantitas(pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas());
						new_detail.setUkuran(prod == null ? "" : prod.getUkuran());
						new_detail.setHpp(prod == null ? 0 : prod.getHpp());
						new_detail.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
						new_detail.setRowstatus(1);
						new_detail.setPengirimanStoreToStore(p);
						details.add(new_detail);
						total_pindah += pengirimanStoreToStore.getDetailPengirimanList().get(i).getKuantitas();
					}
					
				}
				
				
			}
			p.setTotal_pindah(total_pindah);
			p.setDetailPengirimanList(details);
			eRepo.save(p);
			result = "Berhasil diupdate!";
		}
		return result;
		
	}
	
}
