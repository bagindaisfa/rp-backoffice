package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gbsystem.rpbackoffice.repository.PembelianRepository;
import com.gbsystem.rpbackoffice.entities.DetailPembelian;
import com.gbsystem.rpbackoffice.entities.Pembelian;

@Service
public class PembelianService {
	@Autowired
	private PembelianRepository eRepo;
	
	public Pembelian savePembelian(Pembelian pembelian ) {
		
		Pembelian p = new Pembelian();
		String pembelian_code = "PO-" + new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		Date tanggal_transaksi = pembelian.getTanggal_transaksi();
		List<DetailPembelian> details = new ArrayList<>();
		
		p.setPembelian_code(pembelian_code);
		p.setTanggal_transaksi(tanggal_transaksi);
		p.setId_supplier(pembelian.getId_supplier());
		p.setNama_supplier(pembelian.getNama_supplier());
		p.setRowstatus(1);
		for(int i = 0; i < pembelian.getDetail_pembelian().size(); i++) {
			DetailPembelian d = new DetailPembelian();
			d.setPembelian_code(pembelian_code);
			d.setTanggal_transaksi(tanggal_transaksi);
			d.setArtikel(pembelian.getDetail_pembelian().get(i).getArtikel());
			d.setKategori(pembelian.getDetail_pembelian().get(i).getKategori());
			d.setNama_kategori(pembelian.getDetail_pembelian().get(i).getNama_kategori());
			d.setType(pembelian.getDetail_pembelian().get(i).getType());
			d.setType_name(pembelian.getDetail_pembelian().get(i).getType_name());
			d.setNama_barang(pembelian.getDetail_pembelian().get(i).getNama_barang());
			d.setKuantitas(pembelian.getDetail_pembelian().get(i).getKuantitas());
			d.setUkuran(pembelian.getDetail_pembelian().get(i).getUkuran());
			d.setHpp(pembelian.getDetail_pembelian().get(i).getHpp());
			d.setHarga_jual(pembelian.getDetail_pembelian().get(i).getHarga_jual());
			d.setTotal_hpp(pembelian.getDetail_pembelian().get(i).getKuantitas()* pembelian.getDetail_pembelian().get(i).getHpp());
			d.setRowstatus(1);
			d.setPembelian(p);
			details.add(d);
		}
		p.setDetail_pembelian(details);
		return eRepo.save(p);
	}

	public List<Pembelian> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<Pembelian> getAllPembelian(){

		return eRepo.findByRowstatus(1);
	}
	
	public Pembelian deletePembelianById(Long id)
    {
		Pembelian p = new Pembelian();
    	p = eRepo.findById(id).get();
    	List<DetailPembelian> details = new ArrayList<>();
    	p.setRowstatus(0);
    	for(int i = 0; i < p.getDetail_pembelian().size(); i++) {
			DetailPembelian d = new DetailPembelian();
			d = p.getDetail_pembelian().get(i);
			d.setRowstatus(0);
			d.setPembelian(p);
			details.add(d);
		}
    	p.setDetail_pembelian(details);
    	return eRepo.save(p);
    }
	
	public Pembelian update(Pembelian pembelian ) {
		Pembelian p = new Pembelian();
		p = eRepo.findById(pembelian.getId()).get();
		List<DetailPembelian> details = new ArrayList<>();
		
		p.setPembelian_code(pembelian.getPembelian_code());
		p.setTanggal_transaksi(pembelian.getTanggal_transaksi());
		
		p.setId_supplier(pembelian.getId_supplier());
		p.setNama_supplier(pembelian.getNama_supplier());
		p.setRowstatus(pembelian.getRowstatus());
		for(int i = 0; i < pembelian.getDetail_pembelian().size(); i++) {
			DetailPembelian d = new DetailPembelian();
			d = p.getDetail_pembelian().get(i);
			d.setPembelian_code(pembelian.getPembelian_code());
			d.setTanggal_transaksi(pembelian.getTanggal_transaksi());
			d.setArtikel(pembelian.getDetail_pembelian().get(i).getArtikel());
			d.setKategori(pembelian.getDetail_pembelian().get(i).getKategori());
			d.setNama_kategori(pembelian.getDetail_pembelian().get(i).getNama_kategori());
			d.setType(pembelian.getDetail_pembelian().get(i).getType());
			d.setType_name(pembelian.getDetail_pembelian().get(i).getType_name());
			d.setNama_barang(pembelian.getDetail_pembelian().get(i).getNama_barang());
			d.setKuantitas(pembelian.getDetail_pembelian().get(i).getKuantitas());
			d.setUkuran(pembelian.getDetail_pembelian().get(i).getUkuran());
			d.setHpp(pembelian.getDetail_pembelian().get(i).getHpp());
			d.setHarga_jual(pembelian.getDetail_pembelian().get(i).getHarga_jual());
			d.setTotal_hpp(pembelian.getDetail_pembelian().get(i).getKuantitas()* pembelian.getDetail_pembelian().get(i).getHpp());
			d.setRowstatus(pembelian.getDetail_pembelian().get(i).getRowstatus());
			d.setPembelian(p);
			details.add(d);
		}
		p.setDetail_pembelian(details);
		return eRepo.save(p);
	}
	
}
