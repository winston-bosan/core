package com.liveasy.demo.repository;

import com.liveasy.demo.model.HouseSubmodels.Layout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayoutRepository extends CrudRepository<Layout, Long>{



}
