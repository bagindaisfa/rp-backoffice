package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.DetailProformaInvoice;
import com.gbsystem.rpbackoffice.entities.MasterProduct;
import com.gbsystem.rpbackoffice.entities.PIReport;
import com.gbsystem.rpbackoffice.entities.ProformaInvoice;
import com.gbsystem.rpbackoffice.entities.StockOffice;
import com.gbsystem.rpbackoffice.repository.DetailProformaInvoiceRepository;
import com.gbsystem.rpbackoffice.repository.MasterProductRepository;
import com.gbsystem.rpbackoffice.repository.PIReportRepository;
import com.gbsystem.rpbackoffice.repository.ProformaInvoiceRepository;
import com.gbsystem.rpbackoffice.repository.StockOfficeRepository;

@Service
public class ProformaInvoiceService {
	@Autowired
	private ProformaInvoiceRepository eRepo;
	
	@Autowired
	private DetailProformaInvoiceRepository eDetailRepo;
	
	@Autowired
	private StockOfficeRepository eStockOfficeRepo;
	
	@Autowired
	private MasterProductRepository eMasterProductRepository;
	
	@Autowired
	private PIReportRepository eReportRepo;
	
	public ProformaInvoice saveProformaInvoice(ProformaInvoice proformaInvoice) {
		
		String pi_no = "PI-" + new SimpleDateFormat("yyMM").format(new Date()) + "-O" + (eRepo.count() + 1);
		Date tanggal_transaksi = proformaInvoice.getTanggal_transaksi();
		double total = 0;
		ProformaInvoice p = new ProformaInvoice();
		List<DetailProformaInvoice> details = new ArrayList<>();
		
		p.setTanggal_transaksi(tanggal_transaksi);
		p.setPi_no(pi_no);
		p.setId_office(proformaInvoice.getId_office());
		p.setLokasi_office(proformaInvoice.getLokasi_office());
		p.setNo_hp_pelanggan(proformaInvoice.getNo_hp_pelanggan());
		p.setNama_pelanggan(proformaInvoice.getNama_pelanggan());
		p.setId_karyawan(proformaInvoice.getId_karyawan());
		p.setNama_karyawan(proformaInvoice.getNama_karyawan());
		p.setDiskon((proformaInvoice.getDiskon() == null ? 0.0 : proformaInvoice.getDiskon()));
		p.setDiskon_remark(proformaInvoice.getDiskon_remark());
		p.setOngkos_kirim((proformaInvoice.getOngkos_kirim() == null ? 0.0 : proformaInvoice.getOngkos_kirim()));
		p.setEkspedisi(proformaInvoice.getEkspedisi());
		p.setPajak_biaya(proformaInvoice.getPajak_biaya() == null ? 0.0 : proformaInvoice.getPajak_biaya());
		p.setMetode_pembayaran(proformaInvoice.getMetode_pembayaran());
		p.setBank_name(proformaInvoice.getBank_name());
		p.setNo_rek(proformaInvoice.getNo_rek());
		
		for(int i = 0; i < proformaInvoice.getDetail_proforma_invoice().size(); i++) {
			Double diskon_satuan = proformaInvoice.getDetail_proforma_invoice().get(i).getDiskon() == null ? 0.0 : proformaInvoice.getDetail_proforma_invoice().get(i).getDiskon();
			DetailProformaInvoice d = new DetailProformaInvoice();
			
			MasterProduct prod = new MasterProduct();
			prod = eMasterProductRepository.findByArticle(proformaInvoice.getDetail_proforma_invoice().get(i).getArtikel());
			
			StockOffice checker = new StockOffice();
			checker = eStockOfficeRepo.findById_officeAndArtikel(
					proformaInvoice.getId_office(),
					proformaInvoice.getDetail_proforma_invoice().get(i).getArtikel());
			if (checker != null) {
				d.setPi_no(pi_no);
				d.setTanggal_transaksi(tanggal_transaksi);
				d.setSku_code(proformaInvoice.getDetail_proforma_invoice().get(i).getSku_code());
				d.setArtikel(proformaInvoice.getDetail_proforma_invoice().get(i).getArtikel());
				d.setKategori(prod == null ? "" : prod.getKategori());
				d.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
				d.setType(prod == null ? 0 : prod.getType());
				d.setType_name(prod == null ? "" : prod.getType_name());
				d.setNama_barang(prod == null ? "" : prod.getNama_product());
				d.setKuantitas(proformaInvoice.getDetail_proforma_invoice().get(i).getKuantitas());
				d.setHarga_satuan_barang(prod == null ? 0 : prod.getHarga_jual());
				d.setDiskon(diskon_satuan);
				
				if (diskon_satuan > 0.0 && diskon_satuan > 100.0) {
					d.setTotal(proformaInvoice.getDetail_proforma_invoice().get(i).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) - diskon_satuan));
					total += (proformaInvoice.getDetail_proforma_invoice().get(i).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) - diskon_satuan));
				} else if (diskon_satuan > 0.0 && diskon_satuan <= 100.0) {
					d.setTotal(proformaInvoice.getDetail_proforma_invoice().get(i).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) * ((100.0 - diskon_satuan) / 100.0)));
					total += (proformaInvoice.getDetail_proforma_invoice().get(i).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) * ((100.0 - diskon_satuan) / 100.0)));
				} else {
					d.setTotal(proformaInvoice.getDetail_proforma_invoice().get(i).getKuantitas() * (prod == null ? 0 : prod.getHarga_jual()));
					total += (proformaInvoice.getDetail_proforma_invoice().get(i).getKuantitas() * (prod == null ? 0 : prod.getHarga_jual()));	
				}
				
				d.setRowstatus(1);
				d.setProformaInvoice(proformaInvoice);
				details.add(d);
			}
		}
		Double pajak = proformaInvoice.getPajak_biaya() == null ? 0.0 : proformaInvoice.getPajak_biaya();
		Double diskon = proformaInvoice.getDiskon() == null ? 0.0 : proformaInvoice.getDiskon();
		Double total_penjualan = 0.0;
		
		if (diskon > 100.0) {
			if (pajak > 0.0) {
				Double total_new = total - diskon;
				total_penjualan =total_new + (total_new * (pajak / 100));
				
			} else if (pajak <= 0.0) {
				total_penjualan =total - diskon;
			} else if (diskon <= 0.0 && pajak > 0.0) {
				total_penjualan =total + (total * (pajak / 100));
			} else {
				total_penjualan =total;
			}
		} else if (diskon <= 100.0 && diskon > 0.0) {
			if (diskon > 0.0 && pajak > 0.0) {
				Double total_new = total - (total * (diskon / 100));
				total_penjualan =total_new + (total_new * (pajak / 100));
				
			} else if (diskon > 0.0 && pajak <= 0.0) {
				total_penjualan =total - (total * (diskon / 100));
			} else if (diskon <= 0.0 && pajak > 0.0) {
				total_penjualan =total + (total * (pajak / 100));
			} else {
				total_penjualan =total;
			}
		} else {
			if (pajak > 0.0) {
				total_penjualan =total + (total * (pajak / 100));
			} else {
				total_penjualan =total;	
			}
		}
		
		p.setTotal_penjualan(total_penjualan + (proformaInvoice.getOngkos_kirim() == null ? 0.0 : proformaInvoice.getOngkos_kirim()));
		p.setDp(proformaInvoice.getDp());
		if (proformaInvoice.getDp() > 100.0) {
			p.setTotal_dp(proformaInvoice.getDp());
		} else {
			p.setTotal_dp(p.getTotal_penjualan() * (proformaInvoice.getDp() / 100.0));
		}
		p.setRowstatus(1);
		p.setDetail_proforma_invoice(details);
		
		
		return eRepo.save(p);
	}
	
	public List<ProformaInvoice> getAllProformaInvoice(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<ProformaInvoice> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public ProformaInvoice getPi(String pi_no){
		return eRepo.getPi(pi_no);
	}
	
	public ProformaInvoice getPiUpdate(String pi_no){
		return eRepo.getPiOld(pi_no);
	}
	
	public String deleteProformaInvoiceById(Long id)
    {
		ProformaInvoice p = new ProformaInvoice();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	
		
		List<DetailProformaInvoice> details = new ArrayList<>();
    	for(int i = 0; i < p.getDetail_proforma_invoice().size(); i++) {
    		p.getDetail_proforma_invoice().get(i).setRowstatus(0);
    		p.getDetail_proforma_invoice().get(i).setProformaInvoice(p);
			details.add(p.getDetail_proforma_invoice().get(i));
    	}
    	
    	p.setDetail_proforma_invoice(details);
    	eRepo.save(p);
    	
    	return "Berhasil didelete!";
    }
	
	public ProformaInvoice update(ProformaInvoice proformaInvoice) {
		double total = 0;
		ProformaInvoice p = new ProformaInvoice();
    	p = eRepo.findById(proformaInvoice.getId()).get();
    	
    	p.setTanggal_transaksi(proformaInvoice.getTanggal_transaksi());
    	p.setId_office(proformaInvoice.getId_office());
		p.setLokasi_office(proformaInvoice.getLokasi_office());
		p.setNo_hp_pelanggan(proformaInvoice.getNo_hp_pelanggan());
		p.setNama_pelanggan(proformaInvoice.getNama_pelanggan());
		p.setId_karyawan(proformaInvoice.getId_karyawan());
		p.setNama_karyawan(proformaInvoice.getNama_karyawan());
		p.setDiskon((proformaInvoice.getDiskon() == null ? 0.0 : proformaInvoice.getDiskon()));
		p.setDiskon_remark(proformaInvoice.getDiskon_remark());
		p.setOngkos_kirim((proformaInvoice.getOngkos_kirim() == null ? 0.0 : proformaInvoice.getOngkos_kirim()));
		p.setEkspedisi(proformaInvoice.getEkspedisi());
		p.setPajak_biaya((proformaInvoice.getPajak_biaya() == null ? 0.0 : proformaInvoice.getPajak_biaya()));
		p.setMetode_pembayaran(proformaInvoice.getMetode_pembayaran());
		p.setBank_name(proformaInvoice.getBank_name());
		p.setNo_rek(proformaInvoice.getNo_rek());
		p.setRowstatus(proformaInvoice.getRowstatus());
		
		List<DetailProformaInvoice> details = new ArrayList<>();
		for(int k = 0; k < proformaInvoice.getDetail_proforma_invoice().size(); k++) {
			Double diskon_satuan = proformaInvoice.getDetail_proforma_invoice().get(k).getDiskon() == null ? 0.0 : proformaInvoice.getDetail_proforma_invoice().get(k).getDiskon();
			
			DetailProformaInvoice d = new DetailProformaInvoice();
			
			MasterProduct prod = new MasterProduct();
			prod = eMasterProductRepository.findByArticle(proformaInvoice.getDetail_proforma_invoice().get(k).getArtikel());
			
			StockOffice checker = new StockOffice();
			checker = eStockOfficeRepo.findById_officeAndArtikel(
					proformaInvoice.getId_office(),
					proformaInvoice.getDetail_proforma_invoice().get(k).getArtikel());
			if (checker != null) {
				if (proformaInvoice.getDetail_proforma_invoice().get(k).getId() == null) {
					d = null;
				} else {
					d = eDetailRepo.findById(proformaInvoice.getDetail_proforma_invoice().get(k).getId()).orElse(null);
				}
				
				if (d != null) {
					if (proformaInvoice.getDetail_proforma_invoice().get(k).getRowstatus() == 1) {
						if (diskon_satuan > 0.0 && diskon_satuan > 100.0) {
							d.setTotal(proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) - diskon_satuan));
							total += (proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) - diskon_satuan));
						} else if (diskon_satuan > 0.0 && diskon_satuan <= 100.0) {
							d.setTotal(proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) * ((100.0 - diskon_satuan) / 100.0)));
							total += (proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) * ((100.0 - diskon_satuan) / 100.0)));
						} else {
							d.setTotal(proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * (prod == null ? 0 : prod.getHarga_jual()));
							total += (proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * (prod == null ? 0 : prod.getHarga_jual()));	
						}
						
					} 
					d.setTanggal_transaksi(proformaInvoice.getTanggal_transaksi());
					d.setArtikel(proformaInvoice.getDetail_proforma_invoice().get(k).getArtikel());
					d.setSku_code(proformaInvoice.getDetail_proforma_invoice().get(k).getSku_code());
					d.setKategori(prod == null ? "" : prod.getKategori());
					d.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
					d.setType(prod == null ? 0 : prod.getType());
					d.setType_name(prod == null ? "" : prod.getType_name());
					d.setNama_barang(prod == null ? "" : prod.getNama_product());
					d.setKuantitas(proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas());
					d.setHarga_satuan_barang(proformaInvoice.getDetail_proforma_invoice().get(k).getHarga_satuan_barang());
					d.setDiskon(diskon_satuan);
					d.setRowstatus(proformaInvoice.getDetail_proforma_invoice().get(k).getRowstatus());
					d.setProformaInvoice(p);
					details.add(d);
					
				} else {
					DetailProformaInvoice new_insert = new DetailProformaInvoice();
					new_insert.setId_transaksi(p.getId_transaksi());
					new_insert.setTanggal_transaksi(proformaInvoice.getTanggal_transaksi());
					new_insert.setSku_code(proformaInvoice.getDetail_proforma_invoice().get(k).getSku_code());
					new_insert.setArtikel(proformaInvoice.getDetail_proforma_invoice().get(k).getArtikel());
					new_insert.setKategori(prod == null ? "" : prod.getKategori());
					new_insert.setNama_kategori(prod == null ? "" : prod.getNama_kategori());
					new_insert.setType(prod == null ? 0 : prod.getType());
					new_insert.setType_name(prod == null ? "" : prod.getType_name());
					new_insert.setNama_barang(prod == null ? "" : prod.getNama_product());
					new_insert.setKuantitas(proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas());
					new_insert.setHarga_satuan_barang(prod == null ? 0 : prod.getHarga_jual());
					new_insert.setDiskon(diskon_satuan);
					
					if (diskon_satuan > 0.0 && diskon_satuan > 100.0) {
						new_insert.setTotal(proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) - diskon_satuan));
						total += (proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) - diskon_satuan));
					} else if (diskon_satuan > 0.0 && diskon_satuan <= 100.0) {
						new_insert.setTotal(proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) * ((100.0 - diskon_satuan) / 100.0)));
						total += (proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * ((prod == null ? 0 : prod.getHarga_jual()) * ((100.0 - diskon_satuan) / 100.0)));
					} else {
						new_insert.setTotal(proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * (prod == null ? 0 : prod.getHarga_jual()));
						total += (proformaInvoice.getDetail_proforma_invoice().get(k).getKuantitas() * (prod == null ? 0 : prod.getHarga_jual()));	
					}
					new_insert.setRowstatus(1);
					new_insert.setProformaInvoice(p);
					details.add(new_insert);
					
				}
			}
		}
		Double pajak = proformaInvoice.getPajak_biaya() == null ? 0.0 : proformaInvoice.getPajak_biaya();
		Double diskon = proformaInvoice.getDiskon() == null ? 0.0 : proformaInvoice.getDiskon();
		Double total_penjualan = 0.0;
		
		if (diskon > 100.0) {
			if (diskon > 0.0 && pajak > 0.0) {
				Double total_new = total - diskon;
				total_penjualan =total_new + (total_new * (pajak / 100));
				
			} else if (diskon > 0.0 && pajak <= 0.0) {
				total_penjualan =total - diskon;
			} else if (diskon <= 0.0 && pajak > 0.0) {
				total_penjualan =total + (total * (pajak / 100));
			} else {
				total_penjualan =total;
			}
		} else if (diskon <= 100.0 && diskon > 0.0) {
			if (diskon > 0.0 && pajak > 0.0) {
				Double total_new = total - (total * (diskon / 100));
				total_penjualan =total_new + (total_new * (pajak / 100));
				
			} else if (diskon > 0.0 && pajak <= 0.0) {
				total_penjualan =total - (total * (diskon / 100));
			} else if (diskon <= 0.0 && pajak > 0.0) {
				total_penjualan =total + (total * (pajak / 100));
			} else {
				total_penjualan =total;
			}
		} else {
			if (pajak > 0.0) {
				total_penjualan =total + (total * (pajak / 100));
			} else {
				total_penjualan =total;	
			}
		}
		
		
		p.setTotal_penjualan(total_penjualan + (proformaInvoice.getOngkos_kirim() == null ? 0.0 : proformaInvoice.getOngkos_kirim()));
		p.setDp(proformaInvoice.getDp());
		if (proformaInvoice.getDp() > 100.0) {
			p.setTotal_dp(proformaInvoice.getDp());
		} else {
			p.setTotal_dp(p.getTotal_penjualan() * (proformaInvoice.getDp() / 100.0));
		}
		p.setRowstatus(1);
    	p.setDetail_proforma_invoice(details);
    	return eRepo.save(p);
	}
	
	public List<PIReport> ProformaInvoice(int id_office, String pi_no) {
		return eReportRepo.ProformaInvoice(id_office, pi_no);
	}
}
