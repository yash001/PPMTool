package io.SpringBootReatProject.ppmtool.repositories;

import io.SpringBootReatProject.ppmtool.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
