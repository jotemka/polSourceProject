package com.jk.polsource.repository;

import com.jk.polsource.model.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends CrudRepository<Note, Long> {

    @Query(nativeQuery = true, value = "")
    List<Note> findAllNewestUndeletedNotes();

    List<Note> findAllByThreadId(Long id);
}
