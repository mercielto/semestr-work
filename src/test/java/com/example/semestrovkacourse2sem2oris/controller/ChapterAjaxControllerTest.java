package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.service.ChapterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ChapterAjaxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChapterService chapterService;

    @Test
    public void testGetChapter() throws Exception {
        ChapterResponse chapterResponse = new ChapterResponse();
        chapterResponse.setLink("testLink");

        Mockito.when(chapterService.getByLink("testLink")).thenReturn(chapterResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/chapter/ajax/testLink")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"link\":\"testLink\"}"));
    }

    @Test
    public void testPostChapter() throws Exception {
        ChapterRequest request = new ChapterRequest();
        request.setTitle("New Chapter");

        Mockito.when(chapterService.create(any(ChapterRequest.class))).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/chapter/ajax/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Chapter\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void testPutChapter() throws Exception {
        ChapterRequest request = new ChapterRequest();
        request.setTitle("Updated Chapter");

        Mockito.when(chapterService.put(anyString(), any(ChapterRequest.class))).thenReturn("Updated");

        mockMvc.perform(MockMvcRequestBuilders.put("/chapter/ajax/testLink")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Chapter\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Updated"));
    }

    @Test
    public void testDeleteChapter() throws Exception {
        Mockito.doNothing().when(chapterService).deleteChapter("testLink");

        mockMvc.perform(MockMvcRequestBuilders.delete("/chapter/ajax/testLink"))
                .andExpect(status().isOk());
    }
}
