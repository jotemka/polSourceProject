package com.jk.polsource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jk.polsource.model.Note;
import com.jk.polsource.model.ResponseObject;
import com.jk.polsource.service.NotesHistoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
public class NotesHistoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NotesHistoryServiceImpl notesService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getAllByThreadIdTest() throws Exception{
        Long id = 2L;

        MvcResult result = mockMvc.perform(get("/notes-history/all-notes-in-thread/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ResponseObject response = mapper.readValue(result.getResponse().getContentAsString(), ResponseObject.class);
        ObjectReader reader = mapper.readerFor(new TypeReference<List<Note>>() {
        });
        List<Note> retrievedNotes = reader.readValue(response.getData());

        Assert.assertEquals(5, retrievedNotes.size());
    }

    @Test
    public void deleteNoteTest() throws Exception {
        int id = 2;

        MvcResult result = mockMvc.perform(delete("/notes-history/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ResponseObject response = mapper.readValue(result.getResponse().getContentAsString(), ResponseObject.class);
        ObjectReader reader = mapper.readerFor(new TypeReference<Note>() {
        });
        Note removedNote = reader.readValue(response.getData());

        Assert.assertEquals(true, removedNote.getDeleted());
    }

//    @Test
//    public void getNoteTest() throws Exception {
//        int id = 1;
//        String title = "Test";
//        String content = "Some content";
//
//        MvcResult result = mockMvc.perform(get("/notes/" + id)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//        ResponseObject response = mapper.readValue(result.getResponse().getContentAsString(), ResponseObject.class);
//        ObjectReader reader = mapper.readerFor(new TypeReference<Note>() {
//        });
//        Note retrievedNote = reader.readValue(response.getData());
//
//        Assert.assertEquals(id, retrievedNote.getId());
//        Assert.assertEquals(title, retrievedNote.getTitle());
//        Assert.assertEquals(content, retrievedNote.getContent());
//    }

}
