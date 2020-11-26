package com.masterips.javaeeproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masterips.javaeeproject.entities.Celebrite;

@Repository
public interface CelebriteRepository extends JpaRepository<Celebrite, Long> {

}
