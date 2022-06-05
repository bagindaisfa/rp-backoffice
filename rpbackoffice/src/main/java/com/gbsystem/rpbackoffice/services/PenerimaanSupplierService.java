package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.Pembelian;
import com.gbsystem.rpbackoffice.entities.PenerimaanSupplier;
import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.PembelianRepository;
import com.gbsystem.rpbackoffice.repository.PenerimaanSupplierRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;

@Service
public class PenerimaanSupplierService {

	@Autowired
	private PenerimaanSupplierRepository eRepo;
	
	@Autowired
	private StockOfficeRepository eStockRepo;
	
	@Autowired
	private PenyimpananMasukRepository ePenyimpananRepo;
	
	@Autowired
	private PembelianRepository ePembelianRepo;
	
	public List<PenerimaanSupplier> savePenerimaanSupplier(String pembelian_code) {
		Pembelian pembelian = new Pembelian();
		pembelian = ePembelianRepo.findByPembelianCode(pembelian_code);
		
		String code_penerimaan = "PS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		
		for(int i = 0; i < pembelian.getDetail_pembelian().size(); i++) {
			PenerimaanSupplier p = new PenerimaanSupplier();
			p.setPembelian_code(pembelian_code);
			p.setPenerimaan_code(code_penerimaan);
			p.setTanggal_penerimaan(new Date());
			p.setId_office(1);
			p.setLokasi_office("Kantor Pusat");
			p.setId_supplier(pembelian.getId_supplier());
			p.setNama_supplier(pembelian.getNama_supplier());
			p.setArtikel(pembelian.getDetail_pembelian().get(i).getArtikel());
			p.setKategori(pembelian.getDetail_pembelian().get(i).getKategori());
			p.setNama_kategori(pembelian.getDetail_pembelian().get(i).getNama_kategori());
			p.setType(pembelian.getDetail_pembelian().get(i).getType());
			p.setType_name(pembelian.getDetail_pembelian().get(i).getType_name());
			p.setNama_barang(pembelian.getDetail_pembelian().get(i).getNama_barang());
			p.setKuantitas(pembelian.getDetail_pembelian().get(i).getKuantitas());
			p.setUkuran(pembelian.getDetail_pembelian().get(i).getUkuran());
			p.setHpp(pembelian.getDetail_pembelian().get(i).getHpp());
			p.setHarga_jual(pembelian.getDetail_pembelian().get(i).getHarga_jual());
			p.setRowstatus(1);
			eRepo.save(p);
			
			StockOffice d = new StockOffice();
			d = eStockRepo.findById_officeAndArtikel(
					1,
					pembelian.getDetail_pembelian().get(i).getArtikel());
			
			d.setKuantitas(d.getKuantitas() + pembelian.getDetail_pembelian().get(i).getKuantitas());
			eStockRepo.save(d);
			
			PenyimpananMasuk f = new PenyimpananMasuk();
			f.setId_office(1);
			f.setLokasi_office("Kantor Pusat");
			f.setPenerimaan_code(code_penerimaan);
			f.setTanggal_masuk(new Date());
			f.setArtikel(pembelian.getDetail_pembelian().get(i).getArtikel());
			f.setKategori(pembelian.getDetail_pembelian().get(i).getKategori());
			f.setNama_kategori(pembelian.getDetail_pembelian().get(i).getNama_kategori());
			f.setType(pembelian.getDetail_pembelian().get(i).getType());
			f.setType_name(pembelian.getDetail_pembelian().get(i).getType_name());
			f.setNama_barang(pembelian.getDetail_pembelian().get(i).getNama_barang());
			f.setKuantitas(pembelian.getDetail_pembelian().get(i).getKuantitas());
			f.setUkuran(pembelian.getDetail_pembelian().get(i).getUkuran());
			f.setHpp(pembelian.getDetail_pembelian().get(i).getHpp());
			f.setHarga_jual(pembelian.getDetail_pembelian().get(i).getHarga_jual());
			f.setKeterangan("Barang Masuk Dari Supplier");
			f.setRowstatus(1);
			ePenyimpananRepo.save(f);
		}
		 
		return eRepo.findByRowstatus(1);
	}

	public List<PenerimaanSupplier> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PenerimaanSupplier> getAllPenerimaanSupplier(){

		return eRepo.findByRowstatus(1);
	}
	
	public PenerimaanSupplier deletePenerimaanSupplierById(Long id, int id_office, String artikel)
    {
		PenerimaanSupplier p = new PenerimaanSupplier();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	
    	StockOffice d = new StockOffice();
		d = eStockRepo.findById_officeAndArtikel(id_office,artikel);
    	d.setKuantitas(d.getKuantitas() - p.getKuantitas());
    	eStockRepo.save(d);
    	
    	List<PenyimpananMasuk> f = new ArrayList<>();
		f = ePenyimpananRepo.findByPenerimaan_code(p.getPenerimaan_code());
		for (int i = 0; i < f.size(); i++) {
			f.get(i).setRowstatus(0);
			ePenyimpananRepo.save(f.get(i));	
		}
		
    	return eRepo.save(p);
    }
	
}
