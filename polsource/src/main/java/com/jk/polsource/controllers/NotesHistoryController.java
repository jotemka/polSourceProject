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
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/notes-history")
public class NotesHistoryController extends BaseController {

    @Autowired
    private NotesHistoryServiceImpl notesHistoryService;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping(value = "/all-notes-in-thread/{id}")
    public @ResponseBody
    ResponseEntity getAllByThreadId(@PathVariable("id") Long id){
        try{
            List<Note> notes = this.notesHistoryService.findAllByThreadId(id);

            if(CollectionUtils.isEmpty(notes)){
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
            if(ObjectUtils.isEmpty(note)) {
                return new ResponseEntity<>(ResponseObject.createError(Notification.NOTE_DELETED_NULL), HttpStatus.NOT_FOUND);
            }
            note.setDeleted(true);
            JsonNode returnData = mapper.valueToTree(this.notesHistoryService.save(note));
            return new ResponseEntity<>(ResponseObject.createSuccess(Notification.NOTE_DELETED, returnData), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseObject.createError(Notification.NOTE_DELETED_ERROR), HttpStatus.BAD_REQUEST);
        }
    }


}
