package com.jk.polsource.service;

import com.jk.polsource.model.Note;

import java.util.List;

public interface NotesService {
    Note findById(int id);
    List<Note> getAll();
    Note getNewestForThread(Long id);
    Note save(Note note);
}
