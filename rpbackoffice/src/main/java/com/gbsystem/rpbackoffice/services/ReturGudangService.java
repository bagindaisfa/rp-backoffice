package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.ReturGudang;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.entities.DetailReturGudang;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.PenerimaanOfficeFromStore;
import com.gbsystem.rpbackoffice.repository.DetailReturGudangRepository;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PenerimaanOfficeFromStoreRepository;
import com.gbsystem.rpbackoffice.repository.ReturGudangRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class ReturGudangService {
	
	@Autowired
	private ReturGudangRepository eRepo;
	
	@Autowired
	private DetailReturGudangRepository eDetailRepo;
	
	@Autowired
	private PenerimaanOfficeFromStoreRepository ePenerimaanRepo;
	
	@Autowired
	private MasterProductRepository eMasterProductRepository;
	
	@Autowired
	private StockStoreRepository eStockStoreRepo;
	
	public ReturGudang saveReturGudang(ReturGudang returGudang) {
		
		ReturGudang p = new ReturGudang();
		List<DetailReturGudang> details = new ArrayList<>();
		String code_retur = "RT-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		
		Date tanggal_pengiriman = returGudang.getTanggal_retur();
		
		p.setPengiriman_code(code_retur);
		p.setTanggal_retur(tanggal_pengiriman);
		p.setId_store_asal(returGudang.getId_store_asal());
		p.setLokasi_store_asal(returGudang.getLokasi_store_asal());
		p.setId_office_tujuan(returGudang.getId_office_tujuan());
		p.setLokasi_office_tujuan(returGudang.getLokasi_office_tujuan());
		p.setRowstatus(1);
		
		for(int i = 0; i < returGudang.getDetail_pengiriman().size(); i++) {
			StockStore stock = new StockStore();
			stock = eStockStoreRepo.findById_storeAndArtikel(
					returGudang.getId_store_asal(),
					returGudang.getDetail_pengiriman().get(i).getArtikel());
			if (stock != null && stock.getKuantitas() > 0.0) {
				DetailReturGudang d = new DetailReturGudang();
				d.setPengiriman_code(code_retur);
				d.setTanggal_retur(tanggal_pengiriman);
				d.setSku_code(returGudang.getDetail_pengiriman().get(i).getSku_code());
				d.setArtikel(returGudang.getDetail_pengiriman().get(i).getArtikel());
				d.setKategori(returGudang.getDetail_pengiriman().get(i).getKategori());
				d.setNama_kategori(returGudang.getDetail_pengiriman().get(i).getNama_kategori());
				d.setType(returGudang.getDetail_pengiriman().get(i).getType());
				d.setType_name(returGudang.getDetail_pengiriman().get(i).getType_name());
				d.setNama_barang(returGudang.getDetail_pengiriman().get(i).getNama_barang());
				d.setKuantitas(returGudang.getDetail_pengiriman().get(i).getKuantitas());
				d.setUkuran(returGudang.getDetail_pengiriman().get(i).getUkuran());
				d.setHpp(returGudang.getDetail_pengiriman().get(i).getHpp());
				d.setHarga_jual(returGudang.getDetail_pengiriman().get(i).getHarga_jual());
				d.setRowstatus(1);
				d.setReturGudang(p);
				details.add(d);	
			}
		}
		p.setDetail_pengiriman(details);
		return eRepo.save(p);
	}

	public List<ReturGudang> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<ReturGudang> getAllReturGudang(){

		return eRepo.findByRowstatus(1);
	}
	
	public String deleteReturGudangById(Long id)
    {
		String result = "";
		
		ReturGudang p = new ReturGudang();
    	p = eRepo.findById(id).get();
    	
    	PenerimaanOfficeFromStore penerimaan = new PenerimaanOfficeFromStore();
    	penerimaan = ePenerimaanRepo.getByRetur_code(p.getPengiriman_code());
    	
    	if (penerimaan != null) {
    		result = "Pengiriman ini sudah diterima, tidak bisa didelete!";
    	} else {
    		result = "Berhasil didelete!";
    		List<DetailReturGudang> details = new ArrayList<>();
        	p.setRowstatus(0);
        	
        	for(int i = 0; i < p.getDetail_pengiriman().size(); i++) {
	    		p.getDetail_pengiriman().get(i).setRowstatus(0);
	    		p.getDetail_pengiriman().get(i).setReturGudang(p);
				details.add(p.getDetail_pengiriman().get(i));
			}
	    	p.setDetail_pengiriman(details);
	    	eRepo.save(p);
    	}
    	return result;    
    }
	
	public String update(ReturGudang returGudang) {
		String result = "";
    	
    	PenerimaanOfficeFromStore penerimaan = new PenerimaanOfficeFromStore();
    	penerimaan = ePenerimaanRepo.getByRetur_code(returGudang.getPengiriman_code());
		
    	if	(penerimaan != null) {
			result = "Pengiriman ini sudah diterima, tidak bisa diedit!";
		} else {
			ReturGudang p = new ReturGudang();
	    	p = eRepo.findById(returGudang.getId()).get();
	    	
	    	List<DetailReturGudang> details = new ArrayList<>();
	    	
			p.setTanggal_retur(returGudang.getTanggal_retur());
			p.setId_store_asal(returGudang.getId_store_asal());
			p.setLokasi_store_asal(returGudang.getLokasi_store_asal());
			p.setId_office_tujuan(returGudang.getId_office_tujuan());
			p.setLokasi_office_tujuan(returGudang.getLokasi_office_tujuan());
			p.setRowstatus(1);
			
			for(int i = 0; i < returGudang.getDetail_pengiriman().size(); i++) {
				StockStore stock = new StockStore();
				stock = eStockStoreRepo.findById_storeAndArtikel(
						returGudang.getId_store_asal(),
						returGudang.getDetail_pengiriman().get(i).getArtikel());
				DetailReturGudang detail = new DetailReturGudang();
				if (returGudang.getDetail_pengiriman().get(i).getId() == null) {
					detail = null;
				} else {
					detail = eDetailRepo.findById(returGudang.getDetail_pengiriman().get(i).getId()).orElse(null);	
				}
				
				if (detail != null) {
					MasterProduct prod = new MasterProduct();
					prod = eMasterProductRepository.findByArticle(returGudang.getDetail_pengiriman().get(i).getArtikel());
					detail.setTanggal_retur(returGudang.getDetail_pengiriman().get(i).getTanggal_retur());
					detail.setSku_code(returGudang.getDetail_pengiriman().get(i).getSku_code());
					detail.setArtikel(returGudang.getDetail_pengiriman().get(i).getArtikel());
					detail.setKategori(prod == null ? "" : prod.getKategori());
					detail.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
					detail.setType(prod == null ? 0 : prod.getType());
					detail.setType_name(prod == null ? "" : prod.getType_name());
					detail.setNama_barang(returGudang.getDetail_pengiriman().get(i).getNama_barang());
					detail.setKuantitas(returGudang.getDetail_pengiriman().get(i).getKuantitas());
					detail.setUkuran(prod == null ? "" : prod.getUkuran());
					detail.setHpp(prod == null ? 0 : prod.getHpp());
					detail.setHarga_jual(prod == null ? 0 : prod.getHarga_jual());
					detail.setRowstatus(returGudang.getDetail_pengiriman().get(i).getRowstatus());
					detail.setReturGudang(p);
					details.add(detail);
				} else {
					if (stock != null && stock.getKuantitas() > 0.0) {
						DetailReturGudang d = new DetailReturGudang();
						d.setTanggal_retur(returGudang.getDetail_pengiriman().get(i).getTanggal_retur());
						d.setPengiriman_code(p.getPengiriman_code());
						d.setSku_code(returGudang.getDetail_pengiriman().get(i).getSku_code());
						d.setArtikel(returGudang.getDetail_pengiriman().get(i).getArtikel());
						d.setKategori(returGudang.getDetail_pengiriman().get(i).getKategori());
						d.setNama_kategori(returGudang.getDetail_pengiriman().get(i).getNama_kategori());
						d.setType(returGudang.getDetail_pengiriman().get(i).getType());
						d.setType_name(returGudang.getDetail_pengiriman().get(i).getType_name());
						d.setNama_barang(returGudang.getDetail_pengiriman().get(i).getNama_barang());
						d.setKuantitas(returGudang.getDetail_pengiriman().get(i).getKuantitas());
						d.setUkuran(returGudang.getDetail_pengiriman().get(i).getUkuran());
						d.setHpp(returGudang.getDetail_pengiriman().get(i).getHpp());
						d.setHarga_jual(returGudang.getDetail_pengiriman().get(i).getHarga_jual());
						d.setRowstatus(1);
						d.setReturGudang(p);
						details.add(d);
					}
				}
			}
			p.setDetail_pengiriman(details);
			eRepo.save(p);
			result = "Berhasil diupdate!";
		}
		
		return result;
	}
}
