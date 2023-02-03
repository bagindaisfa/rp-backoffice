package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPengirimanOfficeToStore;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.PenerimaanStoreFromOffice;
import com.gbsystem.rpbackoffice.entities.PengirimanOfficeToStore;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.DetailPengirimanOfficeToStoreRepository;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PenerimaanStoreFromOfficeRepository;
import com.gbsystem.rpbackoffice.repository.PengirimanOfficeToStoreRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;

@Service
public class PengirimanOfficeToStoreService {
	
	@Autowired
	private PengirimanOfficeToStoreRepository eRepo;
	
	@Autowired
	private DetailPengirimanOfficeToStoreRepository eDetailRepo;
	
	@Autowired
	private PenerimaanStoreFromOfficeRepository ePenerimaanRepo;

	@Autowired
	private MasterProductRepository eMasterProductRepository;
	
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	
	public PengirimanOfficeToStore savePengirimanOffice(PengirimanOfficeToStore pengirimanOfficeToStore) {
		
		String code_pengiriman = "POTS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		
		PengirimanOfficeToStore p = new PengirimanOfficeToStore();
		List<DetailPengirimanOfficeToStore> details = new ArrayList<>();
		
		p.setPengiriman_code(code_pengiriman);
		p.setTanggal_pengiriman(pengirimanOfficeToStore.getTanggal_pengiriman());
		p.setId_office(pengirimanOfficeToStore.getId_office());
		p.setLokasi_office(pengirimanOfficeToStore.getLokasi_office());
		p.setId_store(pengirimanOfficeToStore.getId_store());
		p.setLokasi_store(pengirimanOfficeToStore.getLokasi_store());
		p.setKeterangan(pengirimanOfficeToStore.getKeterangan());
		p.setRowstatus(1);
		for(int i = 0; i < pengirimanOfficeToStore.getDetailPengirimanList().size(); i++) {
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndArtikel(
					pengirimanOfficeToStore.getId_office(),
					pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
			if (g != null && g.getKuantitas() > 0.0) {
				DetailPengirimanOfficeToStore detail = new DetailPengirimanOfficeToStore();
				
				MasterProduct prod = new MasterProduct();
				prod = eMasterProductRepository.findByArticle(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
				
				detail.setPengiriman_code(code_pengiriman);
				detail.setTanggal_pengiriman(pengirimanOfficeToStore.getTanggal_pengiriman());
				detail.setSku_code(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getSku_code());
				detail.setArtikel(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
				detail.setKategori(prod == null ? "" : prod.getKategori());
				detail.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
				detail.setType(prod == null ? 0 : prod.getType());
				detail.setType_name(prod == null ? "" : prod.getType_name());
				detail.setNama_barang(prod == null ? "" : prod.getNama_product());
				detail.setKuantitas(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKuantitas());
				detail.setUkuran(prod == null ? "" : prod.getUkuran());
				detail.setHpp(prod == null ? 0 : prod.getHpp());
				detail.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
				detail.setRowstatus(1);
				detail.setPengirimanOfficeToStore(p);
				details.add(detail);
			}
			
		}
		
		p.setDetailPengirimanList(details);
		return eRepo.save(p);
	}

	public List<PengirimanOfficeToStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PengirimanOfficeToStore> getAllPengirimanGudang(){

		return eRepo.findByRowstatus(1);
	}
	
	public String deletePengirimanGudangById(Long id)
    {
		String result = "";
		PengirimanOfficeToStore p = new PengirimanOfficeToStore();
		p = eRepo.findById(id).get();
		
		PenerimaanStoreFromOffice penerimaan = new PenerimaanStoreFromOffice();
		penerimaan = ePenerimaanRepo.getByPengirimanCodeandArtikel(p.getPengiriman_code());
		
		if	(penerimaan != null) {
			result = "Pengiriman ini sudah diterima, tidak bisa didelete!";
		} else {
			result = "Berhasil didelete!";

			List<DetailPengirimanOfficeToStore> details = new ArrayList<>();
			p.setRowstatus(0);
			for(int i = 0; i < p.getDetailPengirimanList().size(); i++) {
				p.getDetailPengirimanList().get(i).setRowstatus(0);
				p.getDetailPengirimanList().get(i).setPengirimanOfficeToStore(p);
				details.add(p.getDetailPengirimanList().get(i));
				
			}
			p.setDetailPengirimanList(details);
			eRepo.save(p);
		}

    	return result;
    }
	
	public String update(PengirimanOfficeToStore pengirimanOfficeToStore) {
		String result = "";
		PenerimaanStoreFromOffice penerimaan = new PenerimaanStoreFromOffice();
		penerimaan = ePenerimaanRepo.getByPengirimanCodeandArtikel(pengirimanOfficeToStore.getPengiriman_code());
		
		if	(penerimaan != null) {
			result = "Pengiriman ini sudah diterima, tidak bisa diupdate!";
		} else {
			PengirimanOfficeToStore p = new PengirimanOfficeToStore();
	    	p = eRepo.findById(pengirimanOfficeToStore.getId()).get();
	    	
	    	List<DetailPengirimanOfficeToStore> details = new ArrayList<>();
			
			p.setTanggal_pengiriman(pengirimanOfficeToStore.getTanggal_pengiriman());
			p.setId_office(pengirimanOfficeToStore.getId_office());
			p.setLokasi_office(pengirimanOfficeToStore.getLokasi_office());
			p.setId_store(pengirimanOfficeToStore.getId_store());
			p.setLokasi_store(pengirimanOfficeToStore.getLokasi_store());
			p.setKeterangan(pengirimanOfficeToStore.getKeterangan());
			p.setRowstatus(pengirimanOfficeToStore.getRowstatus());
			
			for(int i = 0; i < pengirimanOfficeToStore.getDetailPengirimanList().size(); i++) {
				StockOffice checker = new StockOffice();
				checker = eStockOfficeRepo.findById_officeAndArtikel(
						pengirimanOfficeToStore.getId_office(),
						pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());	
				DetailPengirimanOfficeToStore detail_update = new DetailPengirimanOfficeToStore();
				if (pengirimanOfficeToStore.getDetailPengirimanList().get(i).getId() == null) {
					detail_update = null;
				} else {
					detail_update = eDetailRepo.findById(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getId()).orElse(null);	
				}
				
				if (detail_update != null) {
					MasterProduct prod = new MasterProduct();
					prod = eMasterProductRepository.findByArticle(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
					
					detail_update.setSku_code(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getSku_code());
					detail_update.setArtikel(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
					detail_update.setKategori(prod == null ? "" : prod.getKategori());
					detail_update.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
					detail_update.setType(prod == null ? 0 : prod.getType());
					detail_update.setType_name(prod == null ? "" : prod.getType_name());
					detail_update.setNama_barang(prod == null ? "" : prod.getNama_product());
					detail_update.setKuantitas(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKuantitas());
					detail_update.setUkuran(prod == null ? "" : prod.getUkuran());
					detail_update.setHpp(prod == null ? 0 : prod.getHpp());
					detail_update.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
					detail_update.setRowstatus(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getRowstatus());
					
					detail_update.setPengirimanOfficeToStore(p);
					details.add(detail_update);	
					
				} else {
					if (checker != null && checker.getKuantitas() > 0.0) {
						DetailPengirimanOfficeToStore detail = new DetailPengirimanOfficeToStore();
						
						MasterProduct prod = new MasterProduct();
						prod = eMasterProductRepository.findByArticle(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
						
						detail.setPengiriman_code(p.getPengiriman_code());
						detail.setTanggal_pengiriman(pengirimanOfficeToStore.getTanggal_pengiriman());
						detail.setSku_code(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getSku_code());
						detail.setArtikel(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
						detail.setKategori(prod == null ? "" : prod.getKategori());
						detail.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
						detail.setType(prod == null ? 0 : prod.getType());
						detail.setType_name(prod == null ? "" : prod.getType_name());
						detail.setNama_barang(prod == null ? "" : prod.getNama_product());
						detail.setKuantitas(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKuantitas());
						detail.setUkuran(prod == null ? "" : prod.getUkuran());
						detail.setHpp(prod == null ? 0 : prod.getHpp());
						detail.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
						detail.setRowstatus(1);
						detail.setPengirimanOfficeToStore(p);
						details.add(detail);
					} else {
						result = "Berhasil diupdate, namun ada stock yang tidak tersedia!";
					}
				}
			}
			p.setDetailPengirimanList(details);
			eRepo.save(p);
			result = "Berhasil diupdate!";
		}
		
		return result;
	}
}
