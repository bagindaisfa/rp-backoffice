package com.gbsystem.rpbackoffice.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbsystem.rpbackoffice.repository.GeneralJournalRepository;
import com.gbsystem.rpbackoffice.entities.GeneralJournal;
import com.gbsystem.rpbackoffice.entities.JournalDetail;

@Service
public class GeneralJournalService {
	@Autowired
	private GeneralJournalRepository eRepo;
	
	public void saveGeneralJournal(
			LocalDateTime tanggal_transaksi, String project, String project_name, 
			String rincian_transaksi, List<JournalDetail> journalDetail
			) {
		GeneralJournal p = new GeneralJournal();
		String noJournal = "J-"+ new SimpleDateFormat("yyMM").format(new Date()) +"-"+ (eRepo.count()+1);
		List<JournalDetail> detailList = new ArrayList<>();
		p.setNomorJournal(noJournal);
		p.setTanggal_transaksi(tanggal_transaksi);
		p.setProject(project);
		p.setProject_name(project_name);
		p.setRincian_transaksi(rincian_transaksi);
		p.setRowstatus(1);
		for (int i = 0; i < journalDetail.size(); i++) {
			JournalDetail d = new JournalDetail();
			d.setNomorJournal(noJournal);
			d.setNama_akun(journalDetail.get(i).getNama_akun());
			d.setDebit_amount(journalDetail.get(i).getDebit_amount());
			d.setCredit_amount(journalDetail.get(i).getCredit_amount());
			d.setRowstatus(journalDetail.get(i).getRowstatus());
			d.setGeneralJournal(p);
			detailList.add(d);
		}
		p.setJournalDetail(detailList);
		eRepo.save(p);
	}

	public GeneralJournal getAll(String nomorJournal){
		return eRepo.findByNomorJournalAndRowstatus(nomorJournal, 1);
	}
	
}
