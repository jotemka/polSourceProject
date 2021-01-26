package com.jk.polsource.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jk.polsource.dto.NewNote;
import com.jk.polsource.model.Note;
import com.jk.polsource.model.ResponseObject;
import com.jk.polsource.notifications.Notification;
import com.jk.polsource.service.NotesService;
import com.jk.polsource.service.NotesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/notes")
public class NotesController extends BaseController {

    @Autowired
    private NotesServiceImpl notesService;

    @Autowired
    private ObjectMapper mapper;
//    private static ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "/all-current-notes")
    public @ResponseBody
    ResponseEntity getAllCurrentNotes(){

        try{
            List<Note> notes =this.notesService.getAll();

            if(CollectionUtils.isEmpty(notes)/* notes.size() == 0 || notes == null*/){
                return new ResponseEntity<>(ResponseObject.createError(Notification.NO_CURRENT_NOTES), HttpStatus.NOT_FOUND);
            }

            JsonNode returnData = mapper.valueToTree(notes);
            return new ResponseEntity<>(ResponseObject.createSuccess(Notification.CURRENT_NOTE_GET_SUCCESS, returnData), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseObject.createError(Notification.CURRENT_NOTE_GET_ERROR), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/new-note")
    public ResponseEntity createNote(@Valid @RequestBody NewNote note){
        try{
            Note newlyCreatedNote = new Note(note.getTitle(), note.getContent());
//            this.notesService.save(newlyCreatedNote);
            JsonNode returnData = mapper.valueToTree(this.notesService.save(newlyCreatedNote));
            return new ResponseEntity<>(ResponseObject.createSuccess(Notification.NOTE_CREATED, returnData), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseObject.createError(Notification.NOTE_CREATED_ERROR), HttpStatus.CONFLICT);
        }

    }

    @PostMapping(value = "/updated-note")
    public ResponseEntity updateNote(@Valid @RequestBody Note note) {
        try{
            Note newestVersionNote = this.notesService.getNewestForThread(note.getThreadId());
            Note updatedNote = new Note(note.getTitle(), note.getContent(), note.getCreated(), note.getThreadId(), newestVersionNote.getVersion());
            JsonNode returnData = mapper.valueToTree(this.notesService.save(updatedNote));
            return new ResponseEntity<>(ResponseObject.createSuccess(Notification.NOTE_UPDATED, returnData), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseObject.createError(Notification.NOTE_UPDATED_ERROR), HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteNote(@PathVariable("id") int id){
        try{
            Note note = this.notesService.findById(id);
            if(ObjectUtils.isEmpty(note)) {
                return new ResponseEntity<>(ResponseObject.createError(Notification.NOTE_DELETED_NULL), HttpStatus.NOT_FOUND);
            }
            note.setDeleted(true);
            JsonNode returnData = mapper.valueToTree(this.notesService.save(note));
            return new ResponseEntity<>(ResponseObject.createSuccess(Notification.NOTE_DELETED, returnData), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseObject.createError(Notification.NOTE_DELETED_ERROR), HttpStatus.BAD_REQUEST);
        }
    }


}
