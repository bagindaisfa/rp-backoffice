package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.Penjualan;
import com.gbsystem.rpbackoffice.entities.PenukaranBarang;
import com.gbsystem.rpbackoffice.entities.PenyimpananStoreMasuk;
import com.gbsystem.rpbackoffice.entities.StockStore;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PenjualanRepository;
import com.gbsystem.rpbackoffice.repository.PenukaranBarangRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananStoreMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockStoreRepository;

@Service
public class PenukaranBarangService {
	@Autowired
	private PenukaranBarangRepository eRepo;
	
	@Autowired
	private MasterProductRepository eMasterProductRepo;
	
	@Autowired
	private PenjualanRepository ePenjualanRepo;
	
	@Autowired
	private StockStoreRepository eStockRepo;

	@Autowired
	private PenyimpananStoreMasukRepository ePenyimpananStoreRepo;
	
	public void savePenyimpananMasuk(List<PenukaranBarang> penukaranBarang ) {
		
		String penerimaan_code = "RTS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		
		for (int i=0; i<penukaranBarang.size(); i++) {
			PenukaranBarang p = new PenukaranBarang();
			MasterProduct prod = new MasterProduct();
			prod = eMasterProductRepo.findBySkuCode(penukaranBarang.get(i).getSku_code());
			
			Penjualan penjualan = new Penjualan();
			penjualan = ePenjualanRepo.getPenjualan(penukaranBarang.get(i).getId_transaksi());
			
			p.setPenerimaan_code(penerimaan_code);
			p.setId_transaksi(penukaranBarang.get(i).getId_transaksi());
			p.setTanggal_masuk(new Date());
			p.setId_store(penukaranBarang.get(i).getId_store());
			p.setLokasi_store(penjualan.getLokasi_store());
			p.setArtikel(prod.getArtikel_product());
			p.setSku_code(penukaranBarang.get(i).getSku_code());
			p.setKategori(prod.getKategori());
			p.setNama_kategori(prod.getNama_kategori());
			p.setType(prod.getType());
			p.setType_name(prod.getType_name());
			p.setNama_barang(prod.getNama_product());
			p.setKuantitas(penukaranBarang.get(i).getKuantitas());
			p.setUkuran(prod.getUkuran());
			p.setNama_pelanggan(penjualan.getNama_pelanggan());
			p.setNo_hp_pelanggan(penjualan.getNo_hp_pelanggan());
			p.setHarga_jual(penukaranBarang.get(i).getHarga_jual());
			p.setKeterangan(penukaranBarang.get(i).getKeterangan());
			p.setRowstatus(1);
			eRepo.save(p);
			
			StockStore d = new StockStore();
			d = eStockRepo.findById_storeAndSku_code(
					penukaranBarang.get(i).getId_store(),
					penukaranBarang.get(i).getSku_code());
			d.setKuantitas(d.getKuantitas() + penukaranBarang.get(i).getKuantitas());
			eStockRepo.save(d);
			
			PenyimpananStoreMasuk h = new PenyimpananStoreMasuk();
			h.setId_office(1);
			h.setLokasi_office("Kantor Pusat");
			h.setPenerimaan_code(penerimaan_code);
			h.setTanggal_masuk(new Date());
			h.setId_store(penukaranBarang.get(i).getId_store());
			h.setLokasi_store(penjualan.getLokasi_store());
			h.setSku_code(penukaranBarang.get(i).getSku_code());
			h.setArtikel(prod.getArtikel_product());
			h.setSku_code(penukaranBarang.get(i).getSku_code());
			h.setKategori(prod.getKategori());
			h.setNama_kategori(prod.getNama_kategori());
			h.setType(prod.getType());
			h.setType_name(prod.getType_name());
			h.setNama_barang(prod.getNama_product());
			h.setKuantitas(penukaranBarang.get(i).getKuantitas());
			h.setUkuran(prod.getUkuran());
			h.setHarga_jual(prod.getHarga_jual());
			h.setRowstatus(1);
			ePenyimpananStoreRepo.save(h);
		}
	}
	
	public List<PenukaranBarang> getAllPerStore(int id_store){

		return eRepo.getAllPerStore(id_store);
	}
	
	public List<PenukaranBarang> searchPerStore(int id_store, String keyword){
		return eRepo.searchPerStore(id_store,keyword);
	}
}
