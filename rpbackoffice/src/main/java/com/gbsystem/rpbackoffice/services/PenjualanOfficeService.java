package com.gbsystem.rpbackoffice.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.entities.PenjualanOffice;
import com.gbsystem.rpbackoffice.repository.PenjualanOfficeRepository;

@Service
public class PenjualanOfficeService {
	@Autowired
	private PenjualanOfficeRepository eRepo;
	
	public void savePenjualanOffice(Date tanggal_transaksi, String id_transaksi, String id_office, String lokasi_office,
			double kuantitas, double total) {
		
		PenjualanOffice p = new PenjualanOffice();
		p.setTanggal_transaksi(tanggal_transaksi);
		p.setId_transaksi(id_transaksi);
		p.setId_office(id_office);
		p.setLokasi_office(lokasi_office);
		p.setKuantitas(kuantitas);
		p.setTotal(total);
		p.setRowstatus(1);
		eRepo.save(p);
	}
	public List<PenjualanOffice> getAllPenjualan(){

		return eRepo.findByRowstatus(1);
	}
	
	public List<PenjualanOffice> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public void deletePenjualanById(Long id)
    {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, Date tanggal_transaksi, String id_transaksi, String id_office, String lokasi_office,
			double kuantitas, double total) {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	
		p.setTanggal_transaksi(tanggal_transaksi);
		p.setId_transaksi(id_transaksi);
		p.setId_office(id_office);
		p.setLokasi_office(lokasi_office);
		p.setKuantitas(kuantitas);
		p.setTotal(total);
		p.setRowstatus(1);
    	eRepo.save(p);
	}
	
	public void changePenjualanTanggal_transaksi(Long id ,Date tanggal_transaksi)
    {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	p.setTanggal_transaksi(tanggal_transaksi);
    	eRepo.save(p);    
    }
	
	public void changePenjualanId_transaksi(Long id ,String id_transaksi)
    {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	p.setId_transaksi(id_transaksi);
    	eRepo.save(p);    
    }
	
	public void changePenjualanId_office(Long id ,String id_office)
    {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	p.setId_office(id_office);
    	eRepo.save(p);    
    }
	
	public void changePenjualanLokasi_office(Long id ,String lokasi_office)
    {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	p.setLokasi_office(lokasi_office);
    	eRepo.save(p);    
    }
	
	public void changePenjualanKuantitas(Long id ,int kuantitas)
    {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	p.setKuantitas(kuantitas);
    	eRepo.save(p);    
    }
	
	public void changePenjualanTotal(Long id ,double total)
    {
		PenjualanOffice p = new PenjualanOffice();
    	p = eRepo.findById(id).get();
    	p.setTotal(total);
    	eRepo.save(p);    
    }
}
