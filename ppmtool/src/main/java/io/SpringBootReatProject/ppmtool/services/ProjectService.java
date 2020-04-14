package io.SpringBootReatProject.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.SpringBootReatProject.ppmtool.domain.Project;
import io.SpringBootReatProject.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		
		//Logic
		 
		return projectRepository.save(project);
	}
}
