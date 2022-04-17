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
	
	public ChartOfAccount findByNoAkun(String noAkun);
	
	@Query(value= "SELECT * FROM chart_of_account WHERE rowstatus = 1 AND no_akun IN :no_akun", nativeQuery = true )
	List<ChartOfAccount> findByChartOfAccountNo_akun(@Param("no_akun") List<String> chartOfAccountNama_akunList);
	
	@Query(value = "SELECT * FROM chart_of_account WHERE rowstatus = 1 AND (no_akun LIKE %:keyword% OR nama_akun LIKE %:keyword% OR tipe LIKE %:keyword% OR kelompok LIKE %:keyword% )", nativeQuery = true)
	List<ChartOfAccount> search(String keyword);
}
