package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPengirimanOfficeToStore;
import com.gbsystem.rpbackoffice.entities.PengirimanOfficeToStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananKeluar;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.PengirimanOfficeToStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananKeluarRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PengirimanOfficeToStoreService {
	
	@Autowired
	private PengirimanOfficeToStoreRepository eRepo;

	@Autowired
	private StockStoreRepository eStockRepo;
	
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	
	@Autowired
	private PenyimpananKeluarRepository ePenyimpananRepo;
	
	@Autowired
	private PenyimpananStoreMasukRepository ePenyimpananStoreRepo;
	
	public PengirimanOfficeToStore savePengirimanOffice(PengirimanOfficeToStore pengirimanOfficeToStore) {
		
		String code_pengiriman = "POS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		String code_penerimaan_store = code_pengiriman + "S";
		
		PengirimanOfficeToStore p = new PengirimanOfficeToStore();
		List<DetailPengirimanOfficeToStore> details = new ArrayList<>();
		
		p.setPengiriman_code(code_pengiriman);
		p.setTanggal_pengiriman(pengirimanOfficeToStore.getTanggal_pengiriman());
		p.setId_office(pengirimanOfficeToStore.getId_office());
		p.setLokasi_office(pengirimanOfficeToStore.getLokasi_office());
		p.setId_store(pengirimanOfficeToStore.getId_store());
		p.setLokasi_store(pengirimanOfficeToStore.getLokasi_store());
		p.setRowstatus(1);
		for(int i = 0; i < pengirimanOfficeToStore.getDetailPengirimanList().size(); i++) {
			DetailPengirimanOfficeToStore detail = new DetailPengirimanOfficeToStore();
			detail.setPengiriman_code(code_pengiriman);
			detail.setTanggal_pengiriman(pengirimanOfficeToStore.getTanggal_pengiriman());
			detail.setArtikel(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
			detail.setKategori(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKategori());
			detail.setNama_kategori(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getNama_kategori());
			detail.setType(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getType());
			detail.setType_name(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getType_name());
			detail.setNama_barang(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getNama_barang());
			detail.setKuantitas(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKuantitas());
			detail.setUkuran(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getUkuran());
			detail.setHpp(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getHpp());
			detail.setHarga_jual(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getHarga_jual());
			detail.setRowstatus(1);
			detail.setPengirimanOfficeToStore(p);
			details.add(detail);
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndArtikel(
					pengirimanOfficeToStore.getId_store(),
					pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
			if (d == null) {
				StockStore new_insert = new StockStore();
				new_insert.setId_store(pengirimanOfficeToStore.getId_store());
				new_insert.setLokasi_store(pengirimanOfficeToStore.getLokasi_store());
				new_insert.setArtikel(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
				new_insert.setKategori(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKategori());
				new_insert.setNama_kategori(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getNama_kategori());
				new_insert.setType(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getType());
				new_insert.setType_name(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getType_name());
				new_insert.setNama_barang(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getNama_barang());
				new_insert.setKuantitas(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKuantitas());
				new_insert.setUkuran(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getUkuran());
				new_insert.setHpp(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getHpp());
				new_insert.setHarga_jual(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getHarga_jual());
				new_insert.setRowstatus(1);
				eStockRepo.save(new_insert);
			} else {
				d.setKuantitas(d.getKuantitas() + pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKuantitas());
				eStockRepo.save(d);
			}
			
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndArtikel(
					pengirimanOfficeToStore.getId_office(),
					pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
			g.setKuantitas(g.getKuantitas() - pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKuantitas());
			eStockOfficeRepo.save(g);
			
			PenyimpananKeluar f = new PenyimpananKeluar();
			f.setId_office(pengirimanOfficeToStore.getId_office());
			f.setLokasi_office(pengirimanOfficeToStore.getLokasi_office());
			f.setPengiriman_code(code_pengiriman);
			f.setTanggal_keluar(new Date());
			f.setId_store(pengirimanOfficeToStore.getId_store());
			f.setLokasi_store(pengirimanOfficeToStore.getLokasi_store());
			f.setArtikel(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
			f.setKategori(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKategori());
			f.setNama_kategori(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getNama_kategori());
			f.setType(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getType());
			f.setType_name(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getType_name());
			f.setNama_barang(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getNama_barang());
			f.setKuantitas(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKuantitas());
			f.setUkuran(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getUkuran());
			f.setHpp(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getHpp());
			f.setHarga_jual(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getHarga_jual());
			f.setKeterangan("Barang Dikirim Ke Store");
			f.setRowstatus(1);
			ePenyimpananRepo.save(f);
			
			PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
			h.setId_office(pengirimanOfficeToStore.getId_office());
			h.setLokasi_office(pengirimanOfficeToStore.getLokasi_office());
			h.setPenerimaan_code(code_penerimaan_store);
			h.setTanggal_masuk(new Date());
			h.setId_store(pengirimanOfficeToStore.getId_store());
			h.setLokasi_store(pengirimanOfficeToStore.getLokasi_store());
			h.setArtikel(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getArtikel());
			h.setKategori(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKategori());
			h.setNama_kategori(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getNama_kategori());
			h.setType(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getType());
			h.setType_name(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getType_name());
			h.setNama_barang(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getNama_barang());
			h.setKuantitas(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getKuantitas());
			h.setUkuran(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getUkuran());
			h.setHpp(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getHpp());
			h.setHarga_jual(pengirimanOfficeToStore.getDetailPengirimanList().get(i).getHarga_jual());
			h.setRowstatus(1);
			ePenyimpananStoreRepo.save(h);
			
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
	
	public PengirimanOfficeToStore deletePengirimanGudangById(Long id)
    {
		PengirimanOfficeToStore p = new PengirimanOfficeToStore();
		p = eRepo.findById(id).get();
		List<DetailPengirimanOfficeToStore> details = new ArrayList<>();
		p.setRowstatus(0);
		
		List<PenyimpananKeluar> f = new ArrayList<>();
		f = ePenyimpananRepo.findByPengiriman_code(p.getPengiriman_code());
		for(int s = 0; s < f.size(); s++) {
			f.get(s).setRowstatus(0);
			ePenyimpananRepo.save(f.get(s));
		}
		
		List<PenyimpananStoreMasuk> h = new ArrayList<>();
		h = ePenyimpananStoreRepo.findByPenerimaan_code(p.getPengiriman_code());
		for(int t = 0; t < h.size(); t++) {
			h.get(t).setRowstatus(0);
			ePenyimpananStoreRepo.save(h.get(t));
		}
		for(int i = 0; i < p.getDetailPengirimanList().size(); i++) {
			p.getDetailPengirimanList().get(i).setRowstatus(0);
			p.getDetailPengirimanList().get(i).setPengirimanOfficeToStore(p);
			details.add(p.getDetailPengirimanList().get(i));
			
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndArtikel(
					p.getId_store(),
					p.getDetailPengirimanList().get(i).getArtikel());
			d.setKuantitas(d.getKuantitas() - p.getDetailPengirimanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndArtikel(
					p.getId_office(),
					p.getDetailPengirimanList().get(i).getArtikel());
			g.setKuantitas(g.getKuantitas() + p.getDetailPengirimanList().get(i).getKuantitas());
			eStockOfficeRepo.save(g);
			
		}
		
		p.setDetailPengirimanList(details);
    	return eRepo.save(p);
    }
	
	public PengirimanOfficeToStore update(PengirimanOfficeToStore pengirimanOfficeToStore) {
		PengirimanOfficeToStore p = new PengirimanOfficeToStore();
    	p = eRepo.findById(pengirimanOfficeToStore.getId()).get();
    	
		ePenyimpananRepo.deleteStockKeluar(pengirimanOfficeToStore.getPengiriman_code());
		ePenyimpananStoreRepo.deleteStoreMasuk(pengirimanOfficeToStore.getPengiriman_code());
		
		for(int i = 0; i < p.getDetailPengirimanList().size(); i++) {
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndArtikel(
					p.getId_store(),
					p.getDetailPengirimanList().get(i).getArtikel());
			d.setKuantitas(d.getKuantitas() - p.getDetailPengirimanList().get(i).getKuantitas());
			eStockRepo.save(d);
			
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndArtikel(
					p.getId_office(),
					p.getDetailPengirimanList().get(i).getArtikel());
			g.setKuantitas(g.getKuantitas() + p.getDetailPengirimanList().get(i).getKuantitas());
			eStockOfficeRepo.save(g);
		}
		
		return saveUpdate(pengirimanOfficeToStore);
	}

	private PengirimanOfficeToStore saveUpdate(PengirimanOfficeToStore pengirimanOfficeToStoreNew) {
		PengirimanOfficeToStore p = new PengirimanOfficeToStore();
    	p = eRepo.findById(pengirimanOfficeToStoreNew.getId()).get();
    	
    	List<DetailPengirimanOfficeToStore> details = new ArrayList<>();
		
		p.setPengiriman_code(pengirimanOfficeToStoreNew.getPengiriman_code());
		p.setTanggal_pengiriman(pengirimanOfficeToStoreNew.getTanggal_pengiriman());
		p.setId_office(pengirimanOfficeToStoreNew.getId_office());
		p.setLokasi_office(pengirimanOfficeToStoreNew.getLokasi_office());
		p.setId_store(pengirimanOfficeToStoreNew.getId_store());
		p.setLokasi_store(pengirimanOfficeToStoreNew.getLokasi_store());
		p.setRowstatus(pengirimanOfficeToStoreNew.getRowstatus());
		
		for(int i = 0; i < pengirimanOfficeToStoreNew.getDetailPengirimanList().size(); i++) {
			DetailPengirimanOfficeToStore detail_update = new DetailPengirimanOfficeToStore();
			detail_update.setArtikel(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getArtikel());
			detail_update.setKategori(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getKategori());
			detail_update.setNama_kategori(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getNama_kategori());
			detail_update.setType(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getType());
			detail_update.setType_name(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getType_name());
			detail_update.setNama_barang(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getNama_barang());
			detail_update.setKuantitas(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
			detail_update.setUkuran(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getUkuran());
			detail_update.setHpp(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getHpp());
			detail_update.setHarga_jual(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getHarga_jual());
			detail_update.setRowstatus(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getRowstatus());
			
			if (pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getRowstatus() == 1) {
				
				StockStore d = new StockStore();
				d = eStockRepo.findById_storeAndArtikel(
						pengirimanOfficeToStoreNew.getId_store(),
						pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getArtikel());
				d.setKuantitas(d.getKuantitas() + pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
				eStockRepo.save(d);
				
				StockOffice g = new StockOffice();
				g = eStockOfficeRepo.findById_officeAndArtikel(
						pengirimanOfficeToStoreNew.getId_office(),
						pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getArtikel());
				g.setKuantitas(g.getKuantitas() - pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
				eStockOfficeRepo.save(g);
			
				PenyimpananKeluar f = new PenyimpananKeluar();
				f.setId_office(pengirimanOfficeToStoreNew.getId_office());
				f.setLokasi_office(pengirimanOfficeToStoreNew.getLokasi_office());
				f.setPengiriman_code(pengirimanOfficeToStoreNew.getPengiriman_code());
				f.setTanggal_keluar(new Date());
				f.setId_store(pengirimanOfficeToStoreNew.getId_store());
				f.setLokasi_store(pengirimanOfficeToStoreNew.getLokasi_store());
				f.setArtikel(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getArtikel());
				f.setKategori(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getKategori());
				f.setNama_kategori(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getNama_kategori());
				f.setType(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getType());
				f.setType_name(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getType_name());
				f.setNama_barang(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getNama_barang());
				f.setKuantitas(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
				f.setUkuran(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getUkuran());
				f.setHpp(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getHpp());
				f.setHarga_jual(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getHarga_jual());
				f.setKeterangan("Barang Dikirim Ke Store");
				f.setRowstatus(1);
				ePenyimpananRepo.save(f);
				
				PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
				h.setId_office(pengirimanOfficeToStoreNew.getId_office());
				h.setLokasi_office(pengirimanOfficeToStoreNew.getLokasi_office());
				h.setPenerimaan_code(pengirimanOfficeToStoreNew.getPengiriman_code());
				h.setTanggal_masuk(new Date());
				h.setId_store(pengirimanOfficeToStoreNew.getId_store());
				h.setLokasi_store(pengirimanOfficeToStoreNew.getLokasi_store());
				h.setArtikel(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getArtikel());
				h.setKategori(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getKategori());
				h.setNama_kategori(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getNama_kategori());
				h.setType(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getType());
				h.setType_name(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getType_name());
				h.setNama_barang(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getNama_barang());
				h.setKuantitas(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getKuantitas());
				h.setUkuran(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getUkuran());
				h.setHpp(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getHpp());
				h.setHarga_jual(pengirimanOfficeToStoreNew.getDetailPengirimanList().get(i).getHarga_jual());
				h.setRowstatus(1);
				ePenyimpananStoreRepo.save(h);
			}
			detail_update.setPengirimanOfficeToStore(p);
			details.add(detail_update);	
		}
		p.setDetailPengirimanList(details);
		return eRepo.save(p);
	}
}
