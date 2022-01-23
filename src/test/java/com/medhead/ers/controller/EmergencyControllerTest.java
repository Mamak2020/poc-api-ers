package com.medhead.ers.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EmergencyControllerTest {

	@Autowired
	public MockMvc mockMvc;

	@Test
	public void testGetEmergency() throws Exception {
		mockMvc.perform(get("/emergencies/{id}", 1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.patientLastName").value("Morteau"));
	}

	@Test
	public void testGetEmergencies() throws Exception {
		mockMvc.perform(get("/emergencies")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Chelles")));
		// .andExpect(jsonPath("$[20].hospitalName").isNotEmpty());
	}

	@Test
	public void testGetCountEmergency() throws Exception {
		mockMvc.perform(get("/emergencies/count")).andExpect(status().isOk());

	}

	@Test
	public void testCreateEmergency() throws Exception {

		// Given
		final String jsonBody = "{" + "\"idZone\":11," + "\"idResponder\":1," + "\"idPatient\":2,"
				+ "\"patientFirstName\":\"Maurice\"," + "\"patientLastName\":\"Moss\"," + "\"patientGender\":\"M\","
				+ "\"patientAge\":51," + "\"patientAddress\":\"1 rue de Turbigo 75002 Paris\","
				+ "\"patientLatitude\":4," + "\"patientLongitude\":4," + "\"idPathology\":2," + "\"dtStart\":null}";
		// When
		mockMvc.perform(post("/emergencies").contentType(MediaType.APPLICATION_JSON).content(jsonBody))
				// Then
				.andExpect(status().isOk()).andExpect(jsonPath("$.instructions").isNotEmpty());

	}

}
