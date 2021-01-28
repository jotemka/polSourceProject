package com.jk.polsource.service;

import com.jk.polsource.model.Note;
import com.jk.polsource.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesHistoryServiceImpl implements NotesHistoryService{

    @Autowired
    private NotesRepository notesRepository;

    @Override
    public List<Note> findAllByThreadId(Long threadId) {
        List<Note> notes = this.notesRepository.findAllByThreadId(threadId);
        return notes;
    }

    @Override
    public Note findById(int id) {
        return this.notesRepository.getById(id);
    }

    @Override
    public Note save(Note note) {
        return this.notesRepository.save(note);
    }
}
