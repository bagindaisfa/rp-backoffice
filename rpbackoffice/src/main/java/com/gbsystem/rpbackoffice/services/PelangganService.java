package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.Pelanggan;
import com.gbsystem.rpbackoffice.repository.PelangganRepository;

@Service
public class PelangganService {
	
	@Autowired
	private PelangganRepository eRepo;
	
	public String savePelanggan(String nik,String nama_pelanggan, String no_hp, String email, String alamat, double total_kunjungan,
			double kuantitas, double poin, double total_pembelian) {
		String response = "";
		Pelanggan q = new Pelanggan();
		q = eRepo.findByNoHandphone(no_hp);
		if (q != null) {
			response = "Pelanggan sudah ada!";
		} else {
			Pelanggan p = new Pelanggan();
			p.setTanggal_join(new Date());
			p.setNik(nik);
			p.setNama_pelanggan(nama_pelanggan);
			p.setNo_hp(no_hp);
			p.setEmail(email);
			p.setAlamat(alamat);
			p.setTotal_kunjungan(total_kunjungan);
			p.setKuantitas(kuantitas);
			p.setPoin(poin);
			p.setTotal_pembelian(total_pembelian);
			p.setRowstatus(1);
			eRepo.save(p);
			response = "Sukses!";
		}
		return response;
	}
	
	public String savePelangganMobile(Pelanggan pelanggan) {
		String response = "";
		Pelanggan q = new Pelanggan();
		q = eRepo.findByNoHandphone(pelanggan.getNo_hp());
		if (q != null) {
			response = "Pelanggan sudah ada!";
		} else {
			Pelanggan p = new Pelanggan();
			p.setTanggal_join(new Date());
			p.setNik(pelanggan.getNik());
			p.setNama_pelanggan(pelanggan.getNama_pelanggan());
			p.setNo_hp(pelanggan.getNo_hp());
			p.setEmail(pelanggan.getEmail());
			p.setAlamat(pelanggan.getAlamat());
			p.setTotal_kunjungan(pelanggan.getTotal_kunjungan());
			p.setKuantitas(pelanggan.getKuantitas());
			p.setPoin(pelanggan.getPoin());
			p.setTotal_pembelian(pelanggan.getTotal_pembelian());
			p.setRowstatus(1);
			eRepo.save(p);
			response = "Sukses!";
		}
		return response;
	}

	public List<Pelanggan> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public void download(){
		eRepo.download();
	}
	
	public List<Pelanggan> getAllPelanggan(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deletePelangganById(Long id)
    {
		Pelanggan p = new Pelanggan();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, String nik, String nama_pelanggan, String no_hp, String email, String alamat, double total_kunjungan,
			double kuantitas, double poin, double total_pembelian) {
		Pelanggan p = new Pelanggan();
		p = eRepo.findById(id).get();
		p.setNik(nik);
		p.setNama_pelanggan(nama_pelanggan);
		p.setNo_hp(no_hp);
		p.setEmail(email);
		p.setAlamat(alamat);
		p.setTotal_kunjungan(total_kunjungan);
		p.setKuantitas(kuantitas);
		p.setPoin(poin);
		p.setTotal_pembelian(total_pembelian);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
	
	public void updateMobile(Pelanggan pelanggan) {
		Pelanggan p = new Pelanggan();
		p = eRepo.findById(pelanggan.getId()).get();
		p.setNama_pelanggan(pelanggan.getNama_pelanggan());
		p.setNik(pelanggan.getNik());
		p.setNo_hp(pelanggan.getNo_hp());
		p.setEmail(pelanggan.getEmail());
		p.setAlamat(pelanggan.getAlamat());
		p.setTotal_kunjungan(pelanggan.getTotal_kunjungan());
		p.setKuantitas(pelanggan.getKuantitas());
		p.setPoin(pelanggan.getPoin());
		p.setTotal_pembelian(pelanggan.getTotal_pembelian());
		p.setRowstatus(1);
    	eRepo.save(p);
	}
	
	
	public Double totalPoin(String nama_pelanggan, String no_hp_pelanggan){
		return eRepo.totalPoin(nama_pelanggan, no_hp_pelanggan);
	}
	
}
