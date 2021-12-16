package com.gbsystem.rpbackoffice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbsystem.rpbackoffice.repository.MasterProjectRepository;
import com.gbsystem.rpbackoffice.entities.MasterProject;

@Service
public class MasterProjectService {
	@Autowired
	private MasterProjectRepository eRepo;
	
	public MasterProject saveMasterProject( String project_name ) {
		
		MasterProject p = new MasterProject();
		p.setProject_name(project_name);
		p.setRowstatus(1);
		return eRepo.save(p);
	}

	public List<MasterProject> search(String keyword){
		return eRepo.search(keyword);
	}
	
	public List<MasterProject> getAllMasterProject(){

		return eRepo.findByRowstatus(1);
	}
	
	public void deleteMasterProjectById(Long id)
    {
		MasterProject p = new MasterProject();
    	p = eRepo.findById(id).get();
    	p.setRowstatus(0);
    	eRepo.save(p);    
    }
	
	public void update(Long id, String project_name ) {
		MasterProject p = new MasterProject();
    	p = eRepo.findById(id).get();
		p.setProject_name(project_name);
		p.setRowstatus(1);
    	eRepo.save(p);
	}

}
