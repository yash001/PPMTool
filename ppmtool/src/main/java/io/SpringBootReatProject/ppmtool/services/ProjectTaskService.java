package io.SpringBootReatProject.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import io.SpringBootReatProject.ppmtool.domain.Backlog;
import io.SpringBootReatProject.ppmtool.domain.Project;
import io.SpringBootReatProject.ppmtool.domain.ProjectTask;
import io.SpringBootReatProject.ppmtool.exceptions.ProjectNotFoundException;
import io.SpringBootReatProject.ppmtool.repositories.BacklogRepository;
import io.SpringBootReatProject.ppmtool.repositories.ProjectRepository;
import io.SpringBootReatProject.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {


    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

        //Exceptions: Project not found
    	try{
    	
    	//PTs to be added to a specific project, project != null, BL exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        //set the bl to pt
        projectTask.setBacklog(backlog);
        //we want our project sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
        Integer BacklogSequence = backlog.getPTSequence();
        // Update the BL SEQUENCE
        BacklogSequence++;

        backlog.setPTSequence(BacklogSequence);

        //Add Sequence to Project Task
        projectTask.setProjectSequence(backlog.getProjectIdentifier()+"-"+BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        //INITIAL priority when priority null

        //INITIAL status when status is null
        if(projectTask.getStatus()==""|| projectTask.getStatus()==null){
            projectTask.setStatus("TO_DO");
        }

        if(projectTask.getPriority()==null){ //In the future we need projectTask.getPriority()== 0 to handle the form
            projectTask.setPriority(3);
        }

    	}catch (Exception e) {
    		throw new ProjectNotFoundException("Project not found");
    	}
        return projectTaskRepository.save(projectTask);
    }


	public Iterable<ProjectTask> findBacklogById(String id) {
		
		Project project = projectRepository.findByProjectIdentifier(id);
		
		if(project == null) {
			throw new ProjectNotFoundException("Project with ID: '" +id + "' does not exists");
		}
		
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
	}
	
	public ProjectTask findPTByProjectSequence(@PathVariable String backlog_id ,@PathVariable String sequence) {
		//make sure we are searching on the right backlog
		return projectTaskRepository.findByProjectSequence(sequence);
	}
}
