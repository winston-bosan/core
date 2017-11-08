package com.liveasy.demo.repository;

import com.liveasy.demo.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long>{
    Role findByRole(String role);
}
