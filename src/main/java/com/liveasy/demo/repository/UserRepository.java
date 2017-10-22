package com.liveasy.demo.repository;

import com.liveasy.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

}
