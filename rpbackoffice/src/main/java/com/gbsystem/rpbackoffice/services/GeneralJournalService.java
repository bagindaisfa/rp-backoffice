package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.repository.ChartOfAccountRepository;
import com.gbsystem.rpbackoffice.repository.GeneralJournalRepository;
import com.gbsystem.rpbackoffice.repository.MasterProjectRepository;
import com.gbsystem.rpbackoffice.entities.GeneralJournal;


@Service
public class GeneralJournalService {
	@Autowired
	private GeneralJournalRepository eRepo;
	
	@Autowired
	private ChartOfAccountRepository eCoaRepo;
	
	@Autowired
	private MasterProjectRepository eProjectRepo;
	
	public void saveGeneralJournal( List<GeneralJournal> generalJournal ) {
		String noJournal = "J-"+ new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		for (int i = 0; i < generalJournal.size(); i++) {
			GeneralJournal d = new GeneralJournal();
			String acc_name = "";
			String project_name = "";
			
			if (generalJournal.get(i).getNoAkun() != "") {
				acc_name = eCoaRepo.findByNoAkun(generalJournal.get(i).getNoAkun()).getNamaAkun();
			}
			
			if (generalJournal.get(i).getProject_id() != 0) {
				project_name = eProjectRepo.findById(generalJournal.get(i).getProject_id()).getProject_name();
			}
			
			
			d.setNomorJournal(noJournal);
			d.setTanggal_transaksi(generalJournal.get(i).getTanggal_transaksi());
			d.setNoAkun(generalJournal.get(i).getNoAkun());
			d.setNama_akun(acc_name);
			d.setKelompok(generalJournal.get(i).getKelompok());
			d.setRincian_transaksi(generalJournal.get(i).getRincian_transaksi());
			d.setDebit_amount(generalJournal.get(i).getDebit_amount());
			d.setCredit_amount(generalJournal.get(i).getCredit_amount());
			d.setProject_id(generalJournal.get(i).getProject_id());
			d.setProject_name(project_name);
			d.setRowstatus(generalJournal.get(i).getRowstatus());
			eRepo.save(d);
		}
		
	}

	public List<GeneralJournal> bukuBesar(Date tanggal_awal, Date tanggal_akhir, String project){
		return eRepo.findBukuBesar(tanggal_awal,tanggal_akhir, project);
	}
	
	public List<GeneralJournal> getAll(Date tanggal_awal, Date tanggal_akhir){
		return eRepo.findAll(tanggal_awal,tanggal_akhir);
	}
	public void update( List<GeneralJournal> generalJournal ) {
		for (int i = 0; i < generalJournal.size(); i++) {
			
			GeneralJournal d = new GeneralJournal();
			d = eRepo.findById(generalJournal.get(i).getId());
			
			String acc_name = eCoaRepo.findByNoAkun(generalJournal.get(i).getNoAkun()).getNamaAkun();
			String project_name = eProjectRepo.findById(generalJournal.get(i).getProject_id()).getProject_name();
			
			d.setNomorJournal(generalJournal.get(i).getNomorJournal());
			d.setTanggal_transaksi(generalJournal.get(i).getTanggal_transaksi());
			d.setNoAkun(generalJournal.get(i).getNoAkun());
			d.setNama_akun(acc_name);
			d.setKelompok(generalJournal.get(i).getKelompok());
			d.setRincian_transaksi(generalJournal.get(i).getRincian_transaksi());
			d.setDebit_amount(generalJournal.get(i).getDebit_amount());
			d.setCredit_amount(generalJournal.get(i).getCredit_amount());
			d.setProject_id(generalJournal.get(i).getProject_id());
			d.setProject_name(project_name);
			d.setRowstatus(generalJournal.get(i).getRowstatus());
			eRepo.save(d);
		}
	}
}
