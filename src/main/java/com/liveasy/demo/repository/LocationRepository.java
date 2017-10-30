package com.liveasy.demo.repository;

import com.liveasy.demo.model.HouseSubmodels.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long>{



}
