package com.gbsystem.rpbackoffice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.gbsystem.rpbackoffice.entities.MasterProject;
import com.gbsystem.rpbackoffice.services.MasterProjectService;

@RestController
@RequestMapping("/master/project")
@CrossOrigin
public class MasterProjectController {
	@Autowired
	private MasterProjectService masterProjectService;

    @GetMapping("/all")
	public ResponseEntity<List<MasterProject>> getAll() {
        return new ResponseEntity<>(masterProjectService.getAllMasterProject(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MasterProject>> search(@Param("keyword") String keyword) {
    	return new ResponseEntity<>(masterProjectService.search(keyword), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public String saveMaster_tipe(@RequestParam("project_name") String project_name) 
	{
    	masterProjectService.saveMasterProject(project_name);
		return "redirect:/tipe";
	}
    
    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,@RequestParam("project_name") String project_name) throws Exception {
    	
    	if (project_name != "") {
    		masterProjectService.update(id, project_name);
    	}
    	return "Update Data Successs!";
		
    }
    @GetMapping("/delete")
    public String deleteTipe(@RequestParam("id") Long id)
    {
    	
    	masterProjectService.deleteMasterProjectById(id);
    	return "redirect:/kategori";
    }
}
