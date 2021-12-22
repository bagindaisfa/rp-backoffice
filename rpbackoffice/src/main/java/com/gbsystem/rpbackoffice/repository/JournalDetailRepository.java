package com.gbsystem.rpbackoffice.repository;

import com.gbsystem.rpbackoffice.entities.GeneralJournal;
import com.gbsystem.rpbackoffice.entities.JournalDetail;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalDetailRepository extends JpaRepository<JournalDetail, Long> {
	List<JournalDetail> findByGeneralJournal(GeneralJournal generalJournal, Sort sort);
}
