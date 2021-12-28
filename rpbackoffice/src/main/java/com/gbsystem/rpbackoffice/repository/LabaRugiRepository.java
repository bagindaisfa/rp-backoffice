package com.gbsystem.rpbackoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.LabaRugi;

@Repository
public interface LabaRugiRepository extends JpaRepository<LabaRugi, Long> {
	@Query(value ="SELECT * FROM((SELECT "
			+ "A.id AS id,A.no_akun AS no_akun,A.nama_akun AS nama_akun,"
			+ "CASE WHEN A.saldo_normal = 'Debit' THEN (@balance \\:\\= A.saldo_awal + (SUM(B.debit_amount) - SUM(B.credit_amount))) "
			+ "WHEN A.saldo_normal = 'Kredit' THEN (@balance \\:\\= A.saldo_awal + (SUM(B.credit_amount) - SUM(B.debit_amount))) ELSE 0  "
			+ "END AS saldo FROM chart_of_account A JOIN general_journal B ON A.no_akun=B.no_akun AND B.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.kelompok IN ('Pendapatan','Beban') AND YEAR(B.tanggal_transaksi) LIKE %:year% GROUP BY A.no_akun ORDER BY B.tanggal_transaksi) "
			+ "UNION DISTINCT "
			+ "(SELECT id,no_akun,nama_akun,0 AS saldo FROM chart_of_account WHERE rowstatus=1 AND kelompok IN ('Pendapatan','Beban'))) X GROUP BY X.no_akun ORDER BY X.no_akun", nativeQuery=true )
	List<LabaRugi> findLabaRugi(@Param("year") String year);
	
	@Query(value ="SELECT * FROM((SELECT "
			+ "A.id AS id,A.no_akun AS no_akun,A.nama_akun AS nama_akun,"
			+ "CASE WHEN A.saldo_normal = 'Debit' THEN (@balance \\:\\= A.saldo_awal + (SUM(B.debit_amount) - SUM(B.credit_amount))) "
			+ "WHEN A.saldo_normal = 'Kredit' THEN (@balance \\:\\= A.saldo_awal + (SUM(B.credit_amount) - SUM(B.debit_amount))) ELSE 0  "
			+ "END AS saldo FROM chart_of_account A JOIN general_journal B ON A.no_akun=B.no_akun AND B.rowstatus=1 "
			+ "WHERE A.rowstatus=1 AND A.tipe IN ('Aktiva Lancar','Aktiva Tetap','Kewajiban','Ekuitas') AND YEAR(B.tanggal_transaksi) LIKE %:year% GROUP BY A.no_akun ORDER BY B.tanggal_transaksi) "
			+ "UNION DISTINCT "
			+ "(SELECT id,no_akun,nama_akun,0 AS saldo FROM chart_of_account WHERE rowstatus=1 AND tipe IN ('Aktiva Lancar','Aktiva Tetap','Kewajiban','Ekuitas'))) X GROUP BY X.no_akun ORDER BY X.no_akun", nativeQuery=true )
	List<LabaRugi> findNeracaKeuangan(@Param("year") String year);
}
