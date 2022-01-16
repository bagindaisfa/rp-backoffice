package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.ReturGudang;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.entities.DetailReturGudang;
import com.gbsystem.rpbackoffice.entities.PenerimaanStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreKeluar;
import com.gbsystem.rpbackoffice.repository.PenerimaanStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreKeluarRepository;
import com.gbsystem.rpbackoffice.repository.ReturGudangRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class ReturGudangService {
	
	@Autowired
	private ReturGudangRepository eRepo;
	
	@Autowired
	private StockStoreRepository eStockRepo;
	
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	
	@Autowired
	private PenyimpananMasukRepository ePenyimpananRepo;
	
	@Autowired
	private PenerimaanStoreRepository ePenerimaanRepo;
	
	@Autowired
	private PenyimpananStoreKeluarRepository ePenyimpananStoreKeluarRepo;
	
	public ReturGudang saveReturGudang(ReturGudang returGudang) {
		
		ReturGudang p = new ReturGudang();
		List<DetailReturGudang> details = new ArrayList<>();
		String code_retur = "RT-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		String code_pengiriman_store = code_retur + "S";
		Date tanggal_pengiriman = returGudang.getTanggal_retur();
		
		p.setPengiriman_code(code_retur);
		p.setTanggal_retur(tanggal_pengiriman);
		p.setId_store_asal(returGudang.getId_store_asal());
		p.setLokasi_store_asal(returGudang.getLokasi_store_asal());
		p.setId_office_tujuan(returGudang.getId_office_tujuan());
		p.setLokasi_office_tujuan(returGudang.getLokasi_office_tujuan());
		p.setRowstatus(1);
		
		for(int i = 0; i < returGudang.getDetail_pengiriman().size(); i++) {
			DetailReturGudang d = new DetailReturGudang();
			d.setPengiriman_code(code_retur);
			d.setTanggal_retur(tanggal_pengiriman);
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
			
			PenyimpananStoreKeluar store_asal = new PenyimpananStoreKeluar();
			store_asal.setPengiriman_code(code_pengiriman_store);
			store_asal.setId_office(returGudang.getId_office_tujuan());
			store_asal.setLokasi_office(returGudang.getLokasi_office_tujuan());
			store_asal.setTanggal_keluar(tanggal_pengiriman);
			store_asal.setId_store(returGudang.getId_store_asal());
			store_asal.setLokasi_store(returGudang.getLokasi_store_asal());
			store_asal.setArtikel(returGudang.getDetail_pengiriman().get(i).getArtikel());
			store_asal.setKategori(returGudang.getDetail_pengiriman().get(i).getKategori());
			store_asal.setNama_kategori(returGudang.getDetail_pengiriman().get(i).getNama_kategori());
			store_asal.setType(returGudang.getDetail_pengiriman().get(i).getType());
			store_asal.setType_name(returGudang.getDetail_pengiriman().get(i).getType_name());
			store_asal.setNama_barang(returGudang.getDetail_pengiriman().get(i).getNama_barang());
			store_asal.setKuantitas(returGudang.getDetail_pengiriman().get(i).getKuantitas());
			store_asal.setUkuran(returGudang.getDetail_pengiriman().get(i).getUkuran());
			store_asal.setHpp(returGudang.getDetail_pengiriman().get(i).getHpp());
			store_asal.setHarga_jual(returGudang.getDetail_pengiriman().get(i).getHarga_jual());
			store_asal.setRowstatus(1);
			ePenyimpananStoreKeluarRepo.save(store_asal);

			StockStore e = new StockStore();
			e = eStockRepo.findById_storeAndArtikel(
	    			returGudang.getId_store_asal(),
	    			returGudang.getDetail_pengiriman().get(i).getArtikel(),
	    			returGudang.getDetail_pengiriman().get(i).getUkuran());
			e.setKuantitas(e.getKuantitas() - returGudang.getDetail_pengiriman().get(i).getKuantitas());
			eStockRepo.save(e);
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
	
	public ReturGudang deleteReturGudangById(Long id)
    {
		ReturGudang p = new ReturGudang();
    	p = eRepo.findById(id).get();
    	List<DetailReturGudang> details = new ArrayList<>();
    	p.setRowstatus(0);
    	
    	// region remove penyimpanan masuk
		List<PenyimpananMasuk> f = new ArrayList<>();
		f = ePenyimpananRepo.findByPenerimaan_code(p.getPengiriman_code());
		for(int a = 0; a < f.size(); a++) {
			f.get(a).setRowstatus(0);
	    	ePenyimpananRepo.save(f.get(a));
    	}
    	// end region remove penyimpanan masuk
    	
    	// region remove penyimpanan store keluar
    	List<PenyimpananStoreKeluar> i = new ArrayList<>();
		i = ePenyimpananStoreKeluarRepo.findByPengiriman_code(p.getPengiriman_code()+"S");
		for(int b = 0; b < i.size(); b++) {
			i.get(b).setRowstatus(0);
			ePenyimpananStoreKeluarRepo.save(i.get(b));
		}
		// region remove penyimpanan store keluar
		
		// region remove penerimaan store
		List<PenerimaanStore> a = new ArrayList<>();
    	a = ePenerimaanRepo.findByPenerimaan_code(p.getPengiriman_code());
    	for(int c = 0; c < a.size(); c++) {
    		a.get(c).setRowstatus(0);
	    	ePenerimaanRepo.save(a.get(c));
    	}
    	// end region remove penerimaan store
    	
    	for(int j = 0; j < p.getDetail_pengiriman().size(); j++) {
    		p.getDetail_pengiriman().get(j).setRowstatus(0);
    		p.getDetail_pengiriman().get(j).setReturGudang(p);
			details.add(p.getDetail_pengiriman().get(j));
			
	    	// region add stock store
	    	
	    	StockStore g = new StockStore();
			g = eStockRepo.findById_storeAndArtikel(p.getId_store_asal(),
	    			p.getDetail_pengiriman().get(j).getArtikel(),
	    			p.getDetail_pengiriman().get(j).getUkuran());
			g.setKuantitas(g.getKuantitas() + p.getDetail_pengiriman().get(j).getKuantitas());
			eStockRepo.save(g);
			// end region add stock store
			
			// region reduce stock office
			StockOffice e = new StockOffice();
			eStockOfficeRepo.findById_officeAndArtikel(
					p.getId_office_tujuan(),
	    			p.getDetail_pengiriman().get(j).getArtikel(),
	    			p.getDetail_pengiriman().get(j).getUkuran());
			e.setKuantitas(e.getKuantitas() - p.getDetail_pengiriman().get(j).getKuantitas());
			eStockOfficeRepo.save(e);
			// end region reduce stock office
    	}
		
    	p.setDetail_pengiriman(details);
    	return eRepo.save(p);    
    }
	
	public ReturGudang update(ReturGudang returGudang) {
		
		ReturGudang p = new ReturGudang();
    	p = eRepo.findById(returGudang.getId()).get();
    	
		
		//region delete
		ePenerimaanRepo.deletePenerimaan(returGudang.getPengiriman_code());
		ePenyimpananRepo.deleteStockMasuk(returGudang.getPengiriman_code());
		ePenyimpananStoreKeluarRepo.deleteStoreKeluar(returGudang.getPengiriman_code()+"S");
		//end region delete
		
		//region reverse stock
		for(int i = 0; i < p.getDetail_pengiriman().size(); i++) {
	    	StockStore e = new StockStore();
			e = eStockRepo.findById_storeAndArtikel(
					p.getId_store_asal(),
	    			p.getDetail_pengiriman().get(i).getArtikel(),
	    			p.getDetail_pengiriman().get(i).getUkuran());
			e.setKuantitas(e.getKuantitas() + p.getDetail_pengiriman().get(i).getKuantitas());
			eStockRepo.save(e);
	    	
			StockOffice g = new StockOffice();
			g = eStockOfficeRepo.findById_officeAndArtikel(
					p.getId_office_tujuan(),
	    			p.getDetail_pengiriman().get(i).getArtikel(),
	    			p.getDetail_pengiriman().get(i).getUkuran());
			g.setKuantitas(g.getKuantitas() - p.getDetail_pengiriman().get(i).getKuantitas());
			eStockOfficeRepo.save(g);
				
		}
		//end region reverse stock
		
		
		return saveUpdate(returGudang);
	}

	private ReturGudang saveUpdate( ReturGudang returGudang) {
		ReturGudang p = new ReturGudang();
    	p = eRepo.findById(returGudang.getId()).get();
    	List<DetailReturGudang> details = new ArrayList<>();
    	
    	p.setPengiriman_code(returGudang.getPengiriman_code());
		p.setTanggal_retur(returGudang.getTanggal_retur());
		p.setId_store_asal(returGudang.getId_store_asal());
		p.setLokasi_store_asal(returGudang.getLokasi_store_asal());
		p.setId_office_tujuan(returGudang.getId_office_tujuan());
		p.setLokasi_office_tujuan(returGudang.getLokasi_office_tujuan());
		p.setRowstatus(1);
		
    	for(int j = 0; j < returGudang.getDetail_pengiriman().size(); j++) {
			DetailReturGudang d = new DetailReturGudang();
			d.setPengiriman_code(returGudang.getPengiriman_code());
			d.setTanggal_retur(returGudang.getTanggal_retur());
			d.setArtikel(returGudang.getDetail_pengiriman().get(j).getArtikel());
			d.setKategori(returGudang.getDetail_pengiriman().get(j).getKategori());
			d.setNama_kategori(returGudang.getDetail_pengiriman().get(j).getNama_kategori());
			d.setType(returGudang.getDetail_pengiriman().get(j).getType());
			d.setType_name(returGudang.getDetail_pengiriman().get(j).getType_name());
			d.setNama_barang(returGudang.getDetail_pengiriman().get(j).getNama_barang());
			d.setKuantitas(returGudang.getDetail_pengiriman().get(j).getKuantitas());
			d.setUkuran(returGudang.getDetail_pengiriman().get(j).getUkuran());
			d.setHpp(returGudang.getDetail_pengiriman().get(j).getHpp());
			d.setHarga_jual(returGudang.getDetail_pengiriman().get(j).getHarga_jual());
			d.setRowstatus(returGudang.getDetail_pengiriman().get(j).getRowstatus());
			d.setReturGudang(p);
			details.add(d);
			
			if (returGudang.getDetail_pengiriman().get(j).getRowstatus() == 1) {
		    	StockStore e = new StockStore();
				e = eStockRepo.findById_storeAndArtikel(
						returGudang.getId_store_asal(),
						returGudang.getDetail_pengiriman().get(j).getArtikel(),
						returGudang.getDetail_pengiriman().get(j).getUkuran());
				e.setKuantitas(e.getKuantitas() - returGudang.getDetail_pengiriman().get(j).getKuantitas());
				eStockRepo.save(e);
		    	
				StockOffice g = new StockOffice();
				g = eStockOfficeRepo.findById_officeAndArtikel(
						returGudang.getId_office_tujuan(),
						returGudang.getDetail_pengiriman().get(j).getArtikel(),
						returGudang.getDetail_pengiriman().get(j).getUkuran());
				g.setKuantitas(g.getKuantitas() + returGudang.getDetail_pengiriman().get(j).getKuantitas());
				eStockOfficeRepo.save(g);
				
				PenyimpananMasuk f = new PenyimpananMasuk();
				f.setPenerimaan_code(returGudang.getPengiriman_code());
				f.setTanggal_masuk(returGudang.getTanggal_retur());
				f.setArtikel(returGudang.getDetail_pengiriman().get(j).getArtikel());
				f.setKategori(returGudang.getDetail_pengiriman().get(j).getKategori());
				f.setNama_kategori(returGudang.getDetail_pengiriman().get(j).getNama_kategori());
				f.setType(returGudang.getDetail_pengiriman().get(j).getType());
				f.setType_name(returGudang.getDetail_pengiriman().get(j).getType_name());
				f.setNama_barang(returGudang.getDetail_pengiriman().get(j).getNama_barang());
				f.setKuantitas(returGudang.getDetail_pengiriman().get(j).getKuantitas());
				f.setUkuran(returGudang.getDetail_pengiriman().get(j).getUkuran());
				f.setHpp(returGudang.getDetail_pengiriman().get(j).getHpp());
				f.setHarga_jual(returGudang.getDetail_pengiriman().get(j).getHarga_jual());
				f.setKeterangan("Pengembalian Barang Dari Store");
				f.setRowstatus(1);
				ePenyimpananRepo.save(f);
				
				PenyimpananStoreKeluar store_asal = new PenyimpananStoreKeluar();
				store_asal.setPengiriman_code(returGudang.getPengiriman_code());
				store_asal.setId_office(returGudang.getId_office_tujuan());
				store_asal.setLokasi_office(returGudang.getLokasi_office_tujuan());
				store_asal.setTanggal_keluar(returGudang.getTanggal_retur());
				store_asal.setId_store(returGudang.getId_store_asal());
				store_asal.setLokasi_store(returGudang.getLokasi_store_asal());
				store_asal.setArtikel(returGudang.getDetail_pengiriman().get(j).getArtikel());
				store_asal.setKategori(returGudang.getDetail_pengiriman().get(j).getKategori());
				store_asal.setNama_kategori(returGudang.getDetail_pengiriman().get(j).getNama_kategori());
				store_asal.setType(returGudang.getDetail_pengiriman().get(j).getType());
				store_asal.setType_name(returGudang.getDetail_pengiriman().get(j).getType_name());
				store_asal.setNama_barang(returGudang.getDetail_pengiriman().get(j).getNama_barang());
				store_asal.setKuantitas(returGudang.getDetail_pengiriman().get(j).getKuantitas());
				store_asal.setUkuran(returGudang.getDetail_pengiriman().get(j).getUkuran());
				store_asal.setHpp(returGudang.getDetail_pengiriman().get(j).getHpp());
				store_asal.setHarga_jual(returGudang.getDetail_pengiriman().get(j).getHarga_jual());
				store_asal.setRowstatus(1);
				ePenyimpananStoreKeluarRepo.save(store_asal);
				
				PenerimaanStore penerimaan = new PenerimaanStore();
	    		penerimaan.setPenerimaan_code(returGudang.getPengiriman_code());
	    		penerimaan.setTanggal_retur(returGudang.getTanggal_retur());
	    		penerimaan.setTanggal_penerimaan(new Date());
	    		penerimaan.setId_office(returGudang.getId_office_tujuan());
	    		penerimaan.setLokasi_office(returGudang.getLokasi_office_tujuan());
	    		penerimaan.setId_store(returGudang.getId_store_asal());
	    		penerimaan.setLokasi_store(returGudang.getLokasi_store_asal());
	    		penerimaan.setArtikel(returGudang.getDetail_pengiriman().get(j).getArtikel());
	    		penerimaan.setKategori(returGudang.getDetail_pengiriman().get(j).getKategori());
	    		penerimaan.setNama_kategori(returGudang.getDetail_pengiriman().get(j).getNama_kategori());
	    		penerimaan.setType(returGudang.getDetail_pengiriman().get(j).getType());
	    		penerimaan.setType_name(returGudang.getDetail_pengiriman().get(j).getType_name());
	    		penerimaan.setNama_barang(returGudang.getDetail_pengiriman().get(j).getNama_barang());
	    		penerimaan.setKuantitas(returGudang.getDetail_pengiriman().get(j).getKuantitas());
	    		penerimaan.setUkuran(returGudang.getDetail_pengiriman().get(j).getUkuran());
	    		penerimaan.setHpp(returGudang.getDetail_pengiriman().get(j).getHpp());
	    		penerimaan.setHarga_jual(returGudang.getDetail_pengiriman().get(j).getHarga_jual());
	    		penerimaan.setRowstatus(1);
		    	ePenerimaanRepo.save(penerimaan);
			}
		}
		p.setDetail_pengiriman(details);
    	
    	return eRepo.save(p);
	}
}
