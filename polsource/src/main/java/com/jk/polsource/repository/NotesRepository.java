package com.jk.polsource.repository;

import com.jk.polsource.model.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends CrudRepository<Note, Integer> {

    //Custom query to find newest notes that are not deleted in threads
    @Query(value = "SELECT note_a.id, note_a.thread_id, note_a.created, note_a.modified, note_a.note_title, note_a.content, note_a.note_version, note_a.is_deleted\n" +
            "FROM note as note_a\n" +
            "INNER JOIN (\n" +
            "\tSELECT thread_id, MAX(note_version) as newest, is_deleted\n" +
            "\tFROM note \n" +
            "\tGROUP BY thread_id, is_deleted\n" +
            ") note_b ON note_a.thread_id = note_b.thread_id AND note_a.note_version = note_b.newest AND note_a.is_deleted = note_b.is_deleted\n" +
            "WHERE note_a.is_deleted=0", nativeQuery = true)
    List<Note> findAllNewestUndeletedNotes();

    List<Note> findAllByThreadId(Long id);

    //Custom query to find newest note even if deleted by thread id
    @Query(value = "SELECT note_a.id, note_a.thread_id, note_a.created, note_a.modified, note_a.note_title, note_a.content, note_a.note_version, note_a.is_deleted\n" +
            "FROM note as note_a\n" +
            "INNER JOIN (\n" +
            "SELECT thread_id, MAX(note_version) as newest\n" +
            "FROM note \n" +
            "GROUP BY thread_id\n" +
            ") note_b ON note_a.thread_id = note_b.thread_id AND note_a.note_version = note_b.newest\n" +
            "WHERE note_a.thread_id=?1", nativeQuery = true)
    Note findNewestByThreadId(Long id);

    //Custom query to avoid deleted notes for getting by id
    @Query(value = "SELECT note.id, note.thread_id, note.created, note.modified, note.note_title, note.content, note.note_version, note.is_deleted\n" +
            "FROM note\n" +
            "WHERE note.id=?1 AND note.is_deleted=0", nativeQuery = true)
    Note getById(int id);

    //Custom query to avoid deleted notes for getting all
    @Query(value = "SELECT note.id, note.thread_id, note.created, note.modified, note.note_title, note.content, note.note_version, note.is_deleted\n" +
            "FROM note\n" +
            "WHERE note.is_deleted=0", nativeQuery = true)
    List<Note> getAll();
}
