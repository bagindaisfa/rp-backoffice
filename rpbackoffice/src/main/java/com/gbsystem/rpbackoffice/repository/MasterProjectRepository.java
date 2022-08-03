package com.gbsystem.rpbackoffice.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gbsystem.rpbackoffice.entities.MasterProject;

@Repository
public interface MasterProjectRepository extends JpaRepository<MasterProject, Long> {
	List<MasterProject> findByRowstatus(@Param("rowstatus") int rowstatus);
	
	public MasterProject findById(int id);
	
	@Query(value = "SELECT * FROM master_project WHERE rowstatus = 1 AND (project_name LIKE %:keyword%) ", nativeQuery = true)
	List<MasterProject> search(String keyword);
}