package com.gbsystem.rpbackoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.GeneralJournal;

@Repository
public interface GeneralJournalRepository extends JpaRepository<GeneralJournal, Long> {
	@Query(value ="SELECT * FROM general_journal WHERE rowstatus=1 AND A.tanggal_transaksi >= :tanggal_awal AND A.tanggal_transaksi <= :tanggal_akhir ", nativeQuery=true )
	List<GeneralJournal> findAll(Date tanggal_awal, Date tanggal_akhir);
	
	@Query(value ="SELECT "
			+ "A.id AS id,A.nomor_journal AS nomor_journal,A.project AS project,A.project_name AS project_name,"
			+ "A.rincian_transaksi AS rincian_transaksi,A.rowstatus AS rowstatus,A.tanggal_transaksi AS tanggal_transaksi,"
			+ "A.credit_amount AS credit_amount,A.debit_amount AS debit_amount,C.kelompok AS kelompok,C.nama_akun AS nama_akun,"
			+ "A.no_akun AS no_akun,"
			+ "CASE WHEN C.saldo_normal = 'Debit' THEN (@balance \\:\\= @balance + (A.debit_amount - A.credit_amount))+c.saldo_awal "
			+ "WHEN C.saldo_normal = 'Kredit' THEN (@balance \\:\\= @balance + (A.credit_amount - A.debit_amount))+c.saldo_awal "
			+ "ELSE 0 END AS 'saldo_akhir' "
			+ "FROM general_journal A JOIN (SELECT @balance \\:\\= 0) B LEFT JOIN chart_of_account C ON A.no_akun=C.no_akun "
			+ "WHERE A.rowstatus=1 AND A.tanggal_transaksi >= :tanggal_awal AND A.tanggal_transaksi <= :tanggal_akhir "
			+ "AND A.project LIKE %:project% "
			+ "ORDER BY A.tanggal_transaksi,A.id", nativeQuery=true )
	List<GeneralJournal> findBukuBesar(Date tanggal_awal, Date tanggal_akhir, String project);
	
}
