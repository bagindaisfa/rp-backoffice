package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailPenerimaanSupplier;
import com.gbsystem.rpbackoffice.entities.PenerimaanSupplier;
import com.gbsystem.rpbackoffice.entities.PenyimpananMasuk;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.DetailPenerimaanSupplierRepository;
import com.gbsystem.rpbackoffice.repository.PenerimaanSupplierRepository;
import com.gbsystem.rpbackoffice.repository.PenyimpananMasukRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;

@Service
public class PenerimaanSupplierService {

	@Autowired
	private PenerimaanSupplierRepository eRepo;
	
	@Autowired
	private DetailPenerimaanSupplierRepository eDetailRepo;
	
	@Autowired
	private StockOfficeRepository eStockRepo;
	
	@Autowired
	private PenyimpananMasukRepository ePenyimpananRepo;
	
	public PenerimaanSupplier savePenerimaanSupplier(PenerimaanSupplier penerimaanSupplier) {
		
		String code_penerimaan = "PS-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		PenerimaanSupplier p = new PenerimaanSupplier();
		List<DetailPenerimaanSupplier> details = new ArrayList<>();
		
		p.setPenerimaan_code(code_penerimaan);
		p.setPembelian_code(penerimaanSupplier.getPembelian_code());
		p.setTanggal_penerimaan(penerimaanSupplier.getTanggal_penerimaan());
		p.setId_office(1);
		p.setLokasi_office("Kantor Pusat");
		p.setId_supplier(penerimaanSupplier.getId_supplier());
		p.setNama_supplier(penerimaanSupplier.getNama_supplier());
		p.setRowstatus(1);
		
		for(int i = 0; i < penerimaanSupplier.getDetailPenerimaanList().size(); i++) {
			DetailPenerimaanSupplier detail = new DetailPenerimaanSupplier();
			
			detail.setPenerimaan_code(code_penerimaan);
			detail.setTanggal_penerimaan(penerimaanSupplier.getTanggal_penerimaan());
			detail.setSku_code(penerimaanSupplier.getDetailPenerimaanList().get(i).getSku_code());
			detail.setArtikel(penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
			detail.setKategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getKategori());
			detail.setNama_kategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_kategori());
			detail.setType(penerimaanSupplier.getDetailPenerimaanList().get(i).getType());
			detail.setType_name(penerimaanSupplier.getDetailPenerimaanList().get(i).getType_name());
			detail.setUkuran(penerimaanSupplier.getDetailPenerimaanList().get(i).getUkuran());
			detail.setNama_barang(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_barang());
			detail.setKuantitas(penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
			detail.setHarga_jual(penerimaanSupplier.getDetailPenerimaanList().get(i).getHarga_jual());
			detail.setKeterangan(penerimaanSupplier.getDetailPenerimaanList().get(i).getKeterangan());
			detail.setHpp(penerimaanSupplier.getDetailPenerimaanList().get(i).getHpp());
			detail.setRowstatus(1);
			detail.setPenerimaanSupplier(p);
			details.add(detail);
			StockOffice d = new StockOffice();
			d = eStockRepo.findById_officeAndArtikel(
					1,
					penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
			if (d == null) {
				StockOffice new_insert = new StockOffice();
				
				new_insert.setId_office(1);
				new_insert.setLokasi_office("Kantor Pusat");
				new_insert.setFoto_barang(null);
				new_insert.setSku_code(penerimaanSupplier.getDetailPenerimaanList().get(i).getSku_code());
				new_insert.setArtikel(penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
				new_insert.setKategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getKategori());
				new_insert.setNama_kategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_kategori());
				new_insert.setType(penerimaanSupplier.getDetailPenerimaanList().get(i).getType());
				new_insert.setType_name(penerimaanSupplier.getDetailPenerimaanList().get(i).getType_name());
				new_insert.setNama_barang(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_barang());
				new_insert.setKuantitas(penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
				new_insert.setHarga_jual(penerimaanSupplier.getDetailPenerimaanList().get(i).getHarga_jual());
				new_insert.setHpp(penerimaanSupplier.getDetailPenerimaanList().get(i).getHpp());
				new_insert.setRowstatus(1);
				eStockRepo.save(new_insert);
			} else {
				d.setKuantitas(d.getKuantitas() + penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
				eStockRepo.save(d);
			}
			
			PenyimpananMasuk f = new PenyimpananMasuk();
			f.setId_office(1);
			f.setLokasi_office("Kantor Pusat");
			f.setPenerimaan_code(code_penerimaan);
			f.setTanggal_masuk(penerimaanSupplier.getTanggal_penerimaan());
			f.setArtikel(penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
			f.setSku_code(penerimaanSupplier.getDetailPenerimaanList().get(i).getSku_code());
			f.setKategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getKategori());
			f.setNama_kategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_kategori());
			f.setType(penerimaanSupplier.getDetailPenerimaanList().get(i).getType());
			f.setType_name(penerimaanSupplier.getDetailPenerimaanList().get(i).getType_name());
			f.setNama_barang(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_barang());
			f.setKuantitas(penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
			f.setUkuran(penerimaanSupplier.getDetailPenerimaanList().get(i).getUkuran());
			f.setHpp(penerimaanSupplier.getDetailPenerimaanList().get(i).getHpp());
			f.setHarga_jual(penerimaanSupplier.getDetailPenerimaanList().get(i).getHarga_jual());
			f.setKeterangan("Barang Masuk Dari Supplier " + penerimaanSupplier.getNama_supplier());
			f.setRowstatus(1);
			ePenyimpananRepo.save(f);
		}
		p.setDetailPenerimaanList(details);
		return eRepo.save(p);
	}

	public List<PenerimaanSupplier> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<PenerimaanSupplier> getAllPenerimaanSupplier(){

		return eRepo.findByRowstatus(1);
	}
	
	public String deletePenerimaanSupplierById(Long id)
    {
		PenerimaanSupplier p = new PenerimaanSupplier();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	
    	List<PenyimpananMasuk> f = new ArrayList<>();
		f = ePenyimpananRepo.findByPenerimaan_code(p.getPenerimaan_code());
		for (int j = 0; j < f.size(); j++) {
			f.get(j).setRowstatus(0);
			ePenyimpananRepo.save(f.get(j));	
		}
		
    	List<DetailPenerimaanSupplier> details = new ArrayList<>();
    	for(int i = 0; i < p.getDetailPenerimaanList().size(); i++) {
    		p.getDetailPenerimaanList().get(i).setRowstatus(0);
    		p.getDetailPenerimaanList().get(i).setPenerimaanSupplier(p);
    		details.add(p.getDetailPenerimaanList().get(i));
    		
	    	StockOffice d = new StockOffice();
			d = eStockRepo.findById_officeAndArtikel(
					p.getId_office(),
					p.getDetailPenerimaanList().get(i).getArtikel());
	    	d.setKuantitas(d.getKuantitas() - p.getDetailPenerimaanList().get(i).getKuantitas());
	    	eStockRepo.save(d);
	    	
    	}
    	p.setDetailPenerimaanList(details);
    	eRepo.save(p);
    	return "Berhasil Didelete!";
    }
	
	public String update(PenerimaanSupplier penerimaanSupplier) {
		PenerimaanSupplier p = new PenerimaanSupplier();
    	p = eRepo.findById(penerimaanSupplier.getId()).get();
    	
    	List<DetailPenerimaanSupplier> details = new ArrayList<>();
		
		p.setTanggal_penerimaan(penerimaanSupplier.getTanggal_penerimaan());
		p.setId_office(1);
		p.setLokasi_office("Kantor Pusat");
		p.setId_supplier(penerimaanSupplier.getId_supplier());
		p.setNama_supplier(penerimaanSupplier.getNama_supplier());
		p.setRowstatus(penerimaanSupplier.getRowstatus());
		
		for(int i = 0; i < penerimaanSupplier.getDetailPenerimaanList().size(); i++) {
			
			DetailPenerimaanSupplier detail_update = new DetailPenerimaanSupplier();
			
			if (penerimaanSupplier.getDetailPenerimaanList().get(i).getId() == null) {
				detail_update = null;
			} else {
				detail_update = eDetailRepo.findById(penerimaanSupplier.getDetailPenerimaanList().get(i).getId()).orElse(null);
			}
			
			if (detail_update != null) {
				detail_update.setTanggal_penerimaan(penerimaanSupplier.getTanggal_penerimaan());
				detail_update.setSku_code(penerimaanSupplier.getDetailPenerimaanList().get(i).getSku_code());
				detail_update.setArtikel(penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
				detail_update.setKategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getKategori());
				detail_update.setNama_kategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_kategori());
				detail_update.setType(penerimaanSupplier.getDetailPenerimaanList().get(i).getType());
				detail_update.setType_name(penerimaanSupplier.getDetailPenerimaanList().get(i).getType_name());
				detail_update.setNama_barang(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_barang());
				detail_update.setKuantitas(penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
				detail_update.setUkuran(penerimaanSupplier.getDetailPenerimaanList().get(i).getUkuran());
				detail_update.setHarga_jual(penerimaanSupplier.getDetailPenerimaanList().get(i).getHarga_jual());
				detail_update.setKeterangan(penerimaanSupplier.getDetailPenerimaanList().get(i).getKeterangan());
				detail_update.setHpp(penerimaanSupplier.getDetailPenerimaanList().get(i).getHpp());
				detail_update.setRowstatus(penerimaanSupplier.getDetailPenerimaanList().get(i).getRowstatus());
				
				if (penerimaanSupplier.getDetailPenerimaanList().get(i).getRowstatus() == 1) {
					
					StockOffice g = new StockOffice();
					g = eStockRepo.findById_officeAndArtikel(
							1,
							penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
					g.setKuantitas((g.getKuantitas() - detail_update.getKuantitas()) + penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
					eStockRepo.save(g);
				
					PenyimpananMasuk h = new PenyimpananMasuk();
					h = ePenyimpananRepo.getPenyimpananByPenerimanCodeandArtikel(
							penerimaanSupplier.getPenerimaan_code(),
							penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel()
							);
					h.setId_office(1);
					h.setLokasi_office(penerimaanSupplier.getLokasi_office());
					h.setPenerimaan_code(p.getPenerimaan_code());
					h.setTanggal_masuk(penerimaanSupplier.getTanggal_penerimaan());
					h.setSku_code(penerimaanSupplier.getDetailPenerimaanList().get(i).getSku_code());
					h.setArtikel(penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
					h.setKategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getKategori());
					h.setNama_kategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_kategori());
					h.setType(penerimaanSupplier.getDetailPenerimaanList().get(i).getType());
					h.setType_name(penerimaanSupplier.getDetailPenerimaanList().get(i).getType_name());
					h.setNama_barang(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_barang());
					h.setKuantitas(penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
					h.setRowstatus(1);
					ePenyimpananRepo.save(h);
				} else {
					StockOffice g = new StockOffice();
					g = eStockRepo.findById_officeAndArtikel(
							1,
							penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
					g.setKuantitas(g.getKuantitas() - penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
					eStockRepo.save(g);
				
					PenyimpananMasuk h = new PenyimpananMasuk();
					h = ePenyimpananRepo.getPenyimpananByPenerimanCodeandArtikel(
							penerimaanSupplier.getPenerimaan_code(),
							penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel()
							);
					h.setRowstatus(0);
					ePenyimpananRepo.save(h);
				}
				detail_update.setPenerimaanSupplier(p);
				details.add(detail_update);	
			} else {
				DetailPenerimaanSupplier detail = new DetailPenerimaanSupplier();
				
				detail.setPenerimaan_code(p.getPenerimaan_code());
				detail.setTanggal_penerimaan(penerimaanSupplier.getTanggal_penerimaan());
				detail.setSku_code(penerimaanSupplier.getDetailPenerimaanList().get(i).getSku_code());
				detail.setArtikel(penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
				detail.setKategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getKategori());
				detail.setNama_kategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_kategori());
				detail.setType(penerimaanSupplier.getDetailPenerimaanList().get(i).getType());
				detail.setType_name(penerimaanSupplier.getDetailPenerimaanList().get(i).getType_name());
				detail.setNama_barang(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_barang());
				detail.setKuantitas(penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
				detail.setUkuran(penerimaanSupplier.getDetailPenerimaanList().get(i).getUkuran());
				detail.setHarga_jual(penerimaanSupplier.getDetailPenerimaanList().get(i).getHarga_jual());
				detail.setKeterangan(penerimaanSupplier.getDetailPenerimaanList().get(i).getKeterangan());
				detail.setHpp(penerimaanSupplier.getDetailPenerimaanList().get(i).getHpp());
				detail.setRowstatus(1);
				detail.setPenerimaanSupplier(p);
				details.add(detail);
				
				StockOffice d = new StockOffice();
				d = eStockRepo.findById_officeAndArtikel(
						1,
						penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
				if (d == null) {
					StockOffice new_insert = new StockOffice();
					
					new_insert.setId_office(1);
					new_insert.setLokasi_office("Kantor Pusat");
					new_insert.setFoto_barang(null);
					new_insert.setSku_code(penerimaanSupplier.getDetailPenerimaanList().get(i).getSku_code());
					new_insert.setArtikel(penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
					new_insert.setKategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getKategori());
					new_insert.setNama_kategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_kategori());
					new_insert.setType(penerimaanSupplier.getDetailPenerimaanList().get(i).getType());
					new_insert.setType_name(penerimaanSupplier.getDetailPenerimaanList().get(i).getType_name());
					new_insert.setNama_barang(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_barang());
					new_insert.setKuantitas(penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
					new_insert.setHarga_jual(penerimaanSupplier.getDetailPenerimaanList().get(i).getHarga_jual());
					new_insert.setHpp(penerimaanSupplier.getDetailPenerimaanList().get(i).getHpp());
					new_insert.setRowstatus(1);
					eStockRepo.save(new_insert);
				} else {
					d.setKuantitas(d.getKuantitas() + penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
					eStockRepo.save(d);
				}
				
				PenyimpananMasuk f = new PenyimpananMasuk();
				f.setId_office(1);
				f.setLokasi_office("Kantor Pusat");
				f.setPenerimaan_code(p.getPenerimaan_code());
				f.setTanggal_masuk(penerimaanSupplier.getTanggal_penerimaan());
				f.setArtikel(penerimaanSupplier.getDetailPenerimaanList().get(i).getArtikel());
				f.setSku_code(penerimaanSupplier.getDetailPenerimaanList().get(i).getSku_code());
				f.setKategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getKategori());
				f.setNama_kategori(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_kategori());
				f.setType(penerimaanSupplier.getDetailPenerimaanList().get(i).getType());
				f.setType_name(penerimaanSupplier.getDetailPenerimaanList().get(i).getType_name());
				f.setNama_barang(penerimaanSupplier.getDetailPenerimaanList().get(i).getNama_barang());
				f.setKuantitas(penerimaanSupplier.getDetailPenerimaanList().get(i).getKuantitas());
				f.setUkuran(penerimaanSupplier.getDetailPenerimaanList().get(i).getUkuran());
				f.setHpp(penerimaanSupplier.getDetailPenerimaanList().get(i).getHpp());
				f.setHarga_jual(penerimaanSupplier.getDetailPenerimaanList().get(i).getHarga_jual());
				f.setKeterangan("Barang Masuk Dari Supplier " + penerimaanSupplier.getNama_supplier());
				f.setRowstatus(1);
				ePenyimpananRepo.save(f);
			}
			
		}
		p.setDetailPenerimaanList(details);
		eRepo.save(p);
		return "Sukses diupdate!";
	}

}
