package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbsystem.rpbackoffice.repository.MasterStoreRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.ReturGudangRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;
import com.gbsystem.rpbackoffice.entities.MasterStore;
import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.entities.StockStore;

@Service
public class MasterStoreService {
	@Autowired
	private MasterStoreRepository eRepo;
	
	@Autowired
	private StockOfficeRepository eStockRepo;
	
	@Autowired
	private StockStoreRepository eStockStoreRepo;
	
	@Autowired
	private PenyimpananMasukRepository ePenyimpananRepo;
	
	public MasterStore saveMasterStore( String store_name, String no_tlpn, String alamat, String kepala_store ) {
		
		MasterStore p = new MasterStore();
		p.setStore_name(store_name);
		p.setNo_tlpn(no_tlpn);
		p.setAlamat(alamat);
		p.setKepala_store(kepala_store);
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<MasterStore> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<MasterStore> getAllMasterStore(){

		return eRepo.findByRowstatus();
	}
	
	public void deleteMasterStoreById(int id)
    {
		if (id != 8) {
			MasterStore p = new MasterStore();
	    	p = eRepo.findById(id);
	    	p.setRowstatus(0);
	    	eRepo.save(p);    
	    	
	    	String code_retur = "DEL-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (ePenyimpananRepo.count()+1);
	    	
	    	List<StockStore> store = eStockStoreRepo.stockAvailPerStore(id);
	    	for (int i = 0; i < store.size(); i++) {
	    		StockOffice d = new StockOffice();
	    		d = eStockRepo.findById_officeAndArtikel(1,store.get(i).getArtikel());
	    		if (d != null) {
	        		d.setKuantitas(d.getKuantitas() + store.get(i).getKuantitas());
	        		eStockRepo.save(d);
	    		}
	    		
	    		StockStore e = new StockStore();
				e = eStockStoreRepo.findById_storeAndArtikel(
						id,
						store.get(i).getArtikel());
				e.setRowstatus(0);
				eStockStoreRepo.save(e);
				
				PenyimpananMasuk f = new PenyimpananMasuk();
				f.setPenerimaan_code(code_retur);
				f.setTanggal_masuk(new Date());
				f.setId_office(1);
				f.setLokasi_office("Kantor Pusat");
				f.setSku_code(store.get(i).getSku_code());
				f.setArtikel(store.get(i).getArtikel());
				f.setKategori(store.get(i).getKategori());
				f.setNama_kategori(store.get(i).getNama_kategori());
				f.setType(store.get(i).getType());
				f.setType_name(store.get(i).getType_name());
				f.setNama_barang(store.get(i).getNama_barang());
				f.setKuantitas(store.get(i).getKuantitas());
				f.setHpp(store.get(i).getHpp());
				f.setHarga_jual(store.get(i).getHarga_jual());
				f.setKeterangan("Penghapusan Master Store");
				f.setRowstatus(1);
				ePenyimpananRepo.save(f);
	    	}
		}
		
    }
	
	public void update(Long id, String store_name, String no_tlpn, String alamat, String kepala_store ) {
		MasterStore p = new MasterStore();
    	p = eRepo.findById(id).get();
    	p.setStore_name(store_name);
		p.setNo_tlpn(no_tlpn);
		p.setAlamat(alamat);
		p.setKepala_store(kepala_store);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
}
