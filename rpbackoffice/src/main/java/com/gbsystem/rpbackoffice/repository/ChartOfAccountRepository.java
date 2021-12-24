package com.gbsystem.rpbackoffice.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.ChartOfAccount;

@Repository
public interface ChartOfAccountRepository extends JpaRepository<ChartOfAccount, Long> {
	List<ChartOfAccount> findByRowstatus(@Param("rowstatus") int rowstatus);
	

	@Query(value= "SELECT * FROM chart_of_account WHERE rowstatus = 1 AND artikel_product IN :nama_akun", nativeQuery = true )
	List<ChartOfAccount> findByChartOfAccountNama_akun(@Param("nama_akun") List<String> chartOfAccountNama_akunList);
	
	@Query(value = "SELECT * FROM chart_of_account WHERE rowstatus = 1 AND "
			+ "MATCH(nama_akun, tipe, kelompok) "
			+ "AGAINST (?1)", nativeQuery = true)
	List<ChartOfAccount> search(String keyword);
}
