package com.example.youcontribute.controllers;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.BDDMockito;

import com.example.youcontribute.controllers.requests.CreateRepositoryRequest;
import com.example.youcontribute.models.Repository;
import com.example.youcontribute.service.RepositoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RepositoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RepositoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RepositoryService repositoryService;

    @Test
    public void it_should_list_repositories() throws Exception {
        Repository repository = Repository.builder().organization("huseyinbabal").repository("youcontribute").build();
        BDDMockito.given(this.repositoryService.list()).willReturn(Collections.singletonList(repository));

        this.mockMvc.perform(get("/repositories")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("youcontribute"))
                .andExpect(jsonPath("$[0].organization").value("huseyinbabal"));
    }

    @Test
    public void it_should_create_repository() throws Exception {
        // Given
        String organization = "github";
        String name = "octocat";
        CreateRepositoryRequest request = CreateRepositoryRequest.builder()
                .organization(organization)
                .repository(name)
                .build();

        doNothing().when(this.repositoryService).create(organization, name);

        // When
        this.mockMvc.perform(post("/repositories")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
