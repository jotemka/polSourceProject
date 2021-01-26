package com.jk.polsource.service;

import com.jk.polsource.model.Note;

import java.util.List;

public interface NotesHistoryService {
    List<Note> findAllByThreadId(Long threadId);
    Note findById(int id);
    Note save(Note note);
}
