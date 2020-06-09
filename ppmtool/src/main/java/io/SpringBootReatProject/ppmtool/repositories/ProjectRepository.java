package io.SpringBootReatProject.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.SpringBootReatProject.ppmtool.domain.Project;
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();
}
