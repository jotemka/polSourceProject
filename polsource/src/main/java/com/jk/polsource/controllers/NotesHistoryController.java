package com.jk.polsource.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jk.polsource.model.Note;
import com.jk.polsource.model.ResponseObject;
import com.jk.polsource.notifications.Notification;
import com.jk.polsource.service.NotesHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notes-history")
public class NotesHistoryController extends BaseController {

    @Autowired
    private NotesHistoryServiceImpl notesHistoryService;

    private static ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "/all-notes-in-thread/{id}")
    public @ResponseBody
    ResponseEntity getAllByThreadId(@PathVariable("id") Long id){
        try{
            List<Note> notes = this.notesHistoryService.findAllByThreadId(id);

            if(notes.size() == 0){
                return new ResponseEntity<>(ResponseObject.createError(Notification.NO_NOTE_HISTORY), HttpStatus.NOT_FOUND);
            }

            JsonNode returnData = mapper.valueToTree(notes);
            return new ResponseEntity<>(ResponseObject.createSuccess(Notification.NOTE_HISTORY_SUCCESS, returnData), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseObject.createError(Notification.NOTE_HISTORY_ERROR), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteNote(@PathVariable("id") int id){
        try{
            Note note = this.notesHistoryService.findById(id);
            if(note == null) {
                return new ResponseEntity<>(ResponseObject.createError(Notification.NOTE_DELETED_NULL), HttpStatus.NOT_FOUND);
            }
            note.setDeleted(true);
            this.notesHistoryService.save(note);
            return new ResponseEntity<>(ResponseObject.createSuccess(Notification.NOTE_DELETED), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseObject.createError(Notification.NOTE_DELETED_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
