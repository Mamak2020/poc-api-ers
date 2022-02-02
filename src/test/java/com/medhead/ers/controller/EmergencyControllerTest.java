package com.medhead.ers.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
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
	@DisplayName("API ERS retourne les données du journal d'intervention des urgences médicales pour id=1")
	public void testGetEmergency() throws Exception {
		mockMvc.perform(get("/emergencies/{id}", 1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.patientLastName").value("Morteau"));
	}

	@Test
	@DisplayName("API ERS retourne la liste des données du journal d'intervention des urgences médicales")
	public void testGetEmergencies() throws Exception {
		mockMvc.perform(get("/emergencies")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Lunay")));
	}

	@Test
	@DisplayName("API ERS réserve et retourne l'hôpital le plus proche de Lunay pour une urgence cardio")
	public void testCreateEmergency() throws Exception {

		// Given
		final String jsonBody = "{" + "\"idZone\":11," + "\"idResponder\":1,"
				+ "\"idPatient\":1," + "\"patientFirstName\":\"Maurice\","
				+ "\"patientLastName\":\"Moss\"," + "\"patientGender\":\"M\","
				+ "\"patientAge\":50,"
				+ "\"patientAddress\":\"20 rue du Progrès 41360 Lunay \","
				+ "\"patientLatitude\":47.8106061,"
				+ "\"patientLongitude\": 0.9128109," + "\"idPathology\":70,"
				+ "\"dtStart\":null}";
		// When
		mockMvc.perform(post("/emergencies")
				.contentType(MediaType.APPLICATION_JSON).content(jsonBody))
				// Then
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.hospitalName").value("CH CHATEAUDUN"));

	}

}
