package com.jk.polsource.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jk.polsource.model.Note;
import com.jk.polsource.model.ResponseObject;
import com.jk.polsource.notifications.Notification;
import com.jk.polsource.service.NotesService;
import com.jk.polsource.service.NotesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/notes")
public class NotesController extends BaseController {

    @Autowired
    private NotesServiceImpl notesService;

    private static ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "/all-current-notes")
    public @ResponseBody
    ResponseEntity getAllCurrentNotes(){

        try{
            List<Note> notes =this.notesService.getAll();
            if(notes.size() == 0){
                return new ResponseEntity<>(ResponseObject.createSuccess(Notification.NO_CURRENT_NOTES), HttpStatus.OK);
            }

            JsonNode returnData = mapper.valueToTree(notes);
            return new ResponseEntity<>(ResponseObject.createSuccess(Notification.CURRENT_NOTE_GET_SUCCESS, returnData), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseObject.createError(Notification.CURRENT_NOTE_GET_ERROR), HttpStatus.BAD_REQUEST);
        }
    }



}
