package com.liveasy.demo.repository;

import com.liveasy.demo.model.UserSubmodels.Preference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends CrudRepository<Preference,Long>{
}
