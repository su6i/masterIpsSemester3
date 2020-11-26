package com.masterips.javaeeproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masterips.javaeeproject.entities.Lieu;

@Repository
public interface LieuRepository extends JpaRepository<Lieu, String> {

}
