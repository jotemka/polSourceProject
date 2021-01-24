package com.jk.polsource.service;

import com.jk.polsource.model.Note;
import com.jk.polsource.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NotesHistoryServiceImpl implements NotesHistoryService{

    @Autowired
    private NotesRepository notesRepository;

    @Override
    public List<Note> findAllByThreadId(Long threadId) {
        List<Note> notes = this.notesRepository.findAllByThreadId(threadId);
        return notes;
    }
}
