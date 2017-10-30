package com.liveasy.demo.repository;

import com.liveasy.demo.model.HouseSubmodels.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{



}
