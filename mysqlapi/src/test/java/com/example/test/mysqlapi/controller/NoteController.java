package com.example.test.mysqlapi.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.test.mysqlapi.exception.ResourceNotFoundException;
import com.example.test.mysqlapi.model.Note;
import com.example.test.mysqlapi.repository.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    // Create
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    // get all
    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // get one
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") int noteID) {
        return noteRepository.findById(noteID).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteID));
    }

    // update one
    @PutMapping("notes/{id}")
    public Note updateNote(@PathVariable(value = "id") int noteID, @Valid @RequestBody Note noteDetails) {
        Note note = noteRepository.findById(noteID)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteID));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        Note updateNote = noteRepository.save(note);
        return updateNote;
    }

    // delete one
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") int noteID) {
        Note note = noteRepository.findById(noteID)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteID));
        noteRepository.delete(note);
        return ResponseEntity.ok().build();
    }
}