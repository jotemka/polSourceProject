package com.jk.polsource.repository;

import com.jk.polsource.model.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends CrudRepository<Note, Long> {

    @Query(nativeQuery = true,
            value = "SELECT note.id, note.thread_id, note.created, note.modified, note.note_title, note.content, note.note_version\n" +
                    "FROM note INNER JOIN (SELECT thread_id, max(note_version) AS version2\n" +
                    "FROM (\n" +
                    "SELECT note.thread_id, note.note_version\n" +
                    "FROM note\n" +
                    "GROUP BY note.thread_id, note.note_version, note.is_deleted\n" +
                    "HAVING note.is_deleted=0\n" +
                    ") as not_deleted\n" +
                    "GROUP BY thread_id\n" +
                    ")  AS tmp ON (note.thread_id = tmp.thread_id) AND (note.note_version = tmp.version2)")
    List<Note> findAllNewestUndeletedNotes();

    List<Note> findAllByThreadId(Long id);
}
