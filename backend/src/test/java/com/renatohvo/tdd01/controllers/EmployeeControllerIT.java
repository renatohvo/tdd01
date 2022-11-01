package com.renatohvo.tdd01.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renatohvo.tdd01.dto.EmployeeDTO;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EmployeeControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void findAllShouldReturnPagedResourcesSortedByName() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/employees")
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.content[0].name").value("Alan"));
		result.andExpect(jsonPath("$.content[1].name").value("Ana"));
		result.andExpect(jsonPath("$.content[2].name").value("Carol"));
	}
	
	@Test
	public void insertShouldInsertResource() throws Exception {

		EmployeeDTO dto = new EmployeeDTO(null, "Sonia", "sonia@sonia.com", 1L);
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/employees")
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").value("Sonia"));
		result.andExpect(jsonPath("$.email").value("sonia@sonia.com"));
		result.andExpect(jsonPath("$.departmentId").value(1L));
	}	
}
