package com.masterips.javaeeproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masterips.javaeeproject.entities.Monument;

@Repository
public interface MonumentRepository extends JpaRepository<Monument, String> {

}
