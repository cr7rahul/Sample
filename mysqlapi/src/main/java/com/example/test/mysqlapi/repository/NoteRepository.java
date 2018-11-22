package com.example.test.mysqlapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.mysqlapi.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{

}
