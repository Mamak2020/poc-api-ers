package com.medhead.ers.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Tests Endpoints Hospitals")
class HospitalControllerTest {

	@Autowired
	public MockMvc mockMvc;

	@DisplayName("Given: dans la liste des hôpitaux  When: consulte cette liste Then: on a l'hôpital TROUSSEAU")
	@Test
	void testGetHospitals() throws Exception {
		mockMvc.perform(get("/hospitals")).andExpect(status().isOk())
				.andExpect(content().string(containsString("TROUSSEAU")));
	}

	@DisplayName("Given: id=1 dans la liste des hôpitaux  When: consulte cet hôpital Then: concerne l'hôpital CHATEAUDUN")
	@Test
	void testGetHospital() throws Exception {
		mockMvc.perform(get("/hospitals/{id}", 1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("CH CHATEAUDUN"));

	}
}
