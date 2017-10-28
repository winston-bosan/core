package com.liveasy.demo.repository;

import com.liveasy.demo.model.House;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends CrudRepository<House, Long>{



}
