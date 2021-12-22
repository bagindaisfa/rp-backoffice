package com.gbsystem.rpbackoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.GeneralJournal;

@Repository
public interface GeneralJournalRepository extends JpaRepository<GeneralJournal, String> {
	
	public GeneralJournal findByNomorJournalAndRowstatus(String nomorJournal, int rowstatus);
	
}
