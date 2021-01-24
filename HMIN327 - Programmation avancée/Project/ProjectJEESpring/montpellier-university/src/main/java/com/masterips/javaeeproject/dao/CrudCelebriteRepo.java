package com.masterips.javaeeproject.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.masterips.javaeeproject.entities.Celebrite;

@Repository("crud")
public interface CrudCelebriteRepo extends CrudRepository<Celebrite, Long> {

}
