package com.jk.polsource.service;

import com.jk.polsource.model.Note;
import com.jk.polsource.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Note> allNotes = this.notesRepository.findAllByIsDeletedFalse();
        //filter here
        List<Note> currentNotes = new ArrayList<Note>();
        Long threadId = 0L;
        for (int i = 0; i<allNotes.size(); i++){
            if(!threadId.equals(allNotes.get(i).getThreadId())){
                currentNotes.add(allNotes.get(i));
                threadId = allNotes.get(i).getThreadId();
            }
        }

//        List<Note> notes = this.notesRepository.findAllNewestUndeletedNotes();
        return currentNotes;
    }

    @Override
    public void save(Note note) {
        this.notesRepository.save(note);
    }
}
