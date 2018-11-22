package com.example.test.mysqlapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.mysqlapi.model.LearnModel;

public interface LearnModelRepository extends JpaRepository<LearnModel, Integer> {

}
