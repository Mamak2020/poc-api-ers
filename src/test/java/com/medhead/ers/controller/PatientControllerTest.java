package com.medhead.ers.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {
	@Autowired
	public MockMvc mockMvc;

	@Test
	public void testGetPatient() throws Exception {
		mockMvc.perform(get("/patients/{id}", 1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.lastName").value("Morteau"));
	}

	@Test
	public void test2GetPatient() throws Exception {
		mockMvc.perform(get("/patients/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.lastName").value("Morteau"));
	}

	@Test
	public void testGetCount() throws Exception {
		mockMvc.perform(get("/count")).andExpect(status().isOk());

	}

}
