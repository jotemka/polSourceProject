package com.jk.polsource;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jk.polsource.dto.NewNote;
import com.jk.polsource.model.Note;
import com.jk.polsource.model.ResponseObject;
import com.jk.polsource.service.NotesServiceImpl;
import org.junit.Assert;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
public class NotesControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NotesServiceImpl notesService;

    @Autowired
    private ObjectMapper mapper;

//    @BeforeAll
//    public void setUp(){
//        Note testNote = new Note("Title", "Content");
//        notesService.save(testNote);
//    }

    @Test
    public void getAllCurrentNoteTest() throws Exception{
        MvcResult result = mockMvc.perform(get("/notes/all-current-notes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ResponseObject response = mapper.readValue(result.getResponse().getContentAsString(), ResponseObject.class);
        ObjectReader reader = mapper.readerFor(new TypeReference<List<Note>>() {
        });
        List<Note> list = reader.readValue(response.getData());

        Assert.assertEquals(3, list.size());
    }

    @Test
    public void getAllTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/notes/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ResponseObject response = mapper.readValue(result.getResponse().getContentAsString(), ResponseObject.class);
        ObjectReader reader = mapper.readerFor(new TypeReference<List<Note>>() {
        });
        List<Note> list = reader.readValue(response.getData());

        Assert.assertEquals(5, list.size());
    }

    @Test
    public void createNoteTest() throws Exception {
        NewNote note = new NewNote("TEST TITLE", "TEST CONTENT");

        MvcResult result = mockMvc.perform(post("/notes/new-note")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(note)))
                .andExpect(status().isCreated())
                .andReturn();
        ResponseObject response = mapper.readValue(result.getResponse().getContentAsString(), ResponseObject.class);
        ObjectReader reader = mapper.readerFor(new TypeReference<Note>() {
        });
        Note savedNote = reader.readValue(response.getData());

        Assert.assertEquals(note.getTitle(), savedNote.getTitle());
        Assert.assertEquals(note.getContent(), savedNote.getContent());
    }

    @Test
    public void updateNoteTest() throws Exception {
        Note noteToModify = notesService.findById(2);
        String modTitle = "Modified Title";
        String modContent = "Modified Content";
        noteToModify.setTitle(modTitle);
        noteToModify.setContent(modContent);

        MvcResult result = mockMvc.perform(post("/notes/updated-note")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(noteToModify)))
                .andExpect(status().isOk())
                .andReturn();
        ResponseObject response = mapper.readValue(result.getResponse().getContentAsString(), ResponseObject.class);
        ObjectReader reader = mapper.readerFor(new TypeReference<Note>() {
        });
        Note updatedNote = reader.readValue(response.getData());

        Assert.assertEquals(modTitle, updatedNote.getTitle());
        Assert.assertEquals(modContent, updatedNote.getContent());
    }

    @Test
    public void deleteNoteTest() throws Exception {
        int id = 7;

        MvcResult result = mockMvc.perform(delete("/notes/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ResponseObject response = mapper.readValue(result.getResponse().getContentAsString(), ResponseObject.class);
        ObjectReader reader = mapper.readerFor(new TypeReference<Note>() {
        });
        Note removedNote = reader.readValue(response.getData());

        Assert.assertEquals(id, removedNote.getId());
        Assert.assertEquals(true, removedNote.getDeleted());
    }

    @Test
    public void getNoteTest() throws Exception {
        int id = 1;
        String title = "Test";
        String content = "Some content";

        MvcResult result = mockMvc.perform(get("/notes/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ResponseObject response = mapper.readValue(result.getResponse().getContentAsString(), ResponseObject.class);
        ObjectReader reader = mapper.readerFor(new TypeReference<Note>() {
        });
        Note retrievedNote = reader.readValue(response.getData());

        Assert.assertEquals(id, retrievedNote.getId());
        Assert.assertEquals(title, retrievedNote.getTitle());
        Assert.assertEquals(content, retrievedNote.getContent());
    }
}
