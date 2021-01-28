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
        return this.notesRepository.getById(id);
    }

    @Override
    public List<Note> getAllCurrent() {
        List<Note> currentNotes = this.notesRepository.findAllNewestUndeletedNotes();
        return currentNotes;
    }

    @Override
    public List<Note> getAll() {
        List<Note> allNonDeletedNotes = this.notesRepository.getAll();
        return allNonDeletedNotes;
    }

    @Override
    public Note getNewestForThread(Long id) {
        return this.notesRepository.findNewestByThreadId(id);
    }

    @Override
    public Note save(Note note) {
        return this.notesRepository.save(note);
    }
}
