package com.masterips.javaeeproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masterips.javaeeproject.entities.Departement;

public interface DepartementRepository extends JpaRepository<Departement, String> {

}
