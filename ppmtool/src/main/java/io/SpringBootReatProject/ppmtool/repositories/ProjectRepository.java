package io.SpringBootReatProject.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;

import io.SpringBootReatProject.ppmtool.domain.Project;

public interface ProjectRepository extends CrudRepository<Project,Long>  {
 @Override
 Iterable<Project> findAllById(Iterable<Long> iterable);

}
