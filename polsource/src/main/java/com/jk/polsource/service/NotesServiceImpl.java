package com.jk.polsource.service;

import com.jk.polsource.model.Note;
import com.jk.polsource.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImpl implements  NotesService {

    @Autowired
    private NotesRepository notesRepository;


    @Override
    public Note findById(int id) {
        return this.notesRepository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Note> getAll() {
        List<Note> notes = this.notesRepository.findAllNewestUndeletedNotes();
        return notes;
    }

    @Override
    public void save(Note note) {
        this.notesRepository.save(note);
    }
}
