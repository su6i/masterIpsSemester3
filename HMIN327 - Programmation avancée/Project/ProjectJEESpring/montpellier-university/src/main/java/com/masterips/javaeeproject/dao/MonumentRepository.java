package com.masterips.javaeeproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masterips.javaeeproject.entities.Monument;

public interface MonumentRepository extends JpaRepository<Monument, String> {

}
