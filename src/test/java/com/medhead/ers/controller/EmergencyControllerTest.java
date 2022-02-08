package com.medhead.ers.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("IntegrationTest")
@DisplayName("Tests d'intégration API ERS")
class EmergencyControllerTest {

	@Autowired
	public MockMvc mockMvc;

	@Test
	@DisplayName("Le journal d'intervention des urgences médicales pour id=1 concerne la patiente Magalie")
	void testGetEmergency() throws Exception {
		mockMvc.perform(get("/emergencies/{id}", 1)).andExpect(status().isOk())
				.andExpect(jsonPath("$.patientFirstName").value("Magalie"));
	}

	@Test
	@DisplayName("Dans le journal d'intervention des urgences médicales des urgences médicales")
	void testGetEmergencies() throws Exception {
		mockMvc.perform(get("/emergencies")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Lunay")));
	}

	@Test
	@DisplayName("Cas urgence Cardiologie à Lunay, dispo à Blois")
	void testCreateEmergency1() throws Exception {

		// Given
		final String jsonBody = "{" + "\"idZone\":24," + "\"idResponder\":1,"
				+ "\"idPatient\":1," + "\"patientFirstName\":\"Magalie\","
				+ "\"patientLastName\":\"Morteau\","
				+ "\"patientGender\":\"F\"," + "\"patientAge\":50,"
				+ "\"patientAddress\":\"20 rue du Progrès 41360 Lunay \","
				+ "\"patientLatitude\":47.8106061,"
				+ "\"patientLongitude\": 0.9128109," + "\"idPathology\":70,"
				+ "\"dtStart\":null}";
		// When
		final MvcResult result = mockMvc.perform(post("/emergencies")
				.contentType(MediaType.APPLICATION_JSON).content(jsonBody))
				// Then
				// .andExpect(status().isOk()).andExpect(jsonPath("$.hospitalName")
				// .value("CH BLOIS SIMONE VEIL"))
				.andReturn();

		assertThat(result.getResponse().getContentAsString())
				.contains("\"hospitalName\":\"CH BLOIS SIMONE VEIL\"");
	}

	@Test
	@DisplayName("Cas urgence Réspiratoire à Lunay, dispo en Urgence à Vendôme")
	void testCreateEmergency2() throws Exception {

		// Given
		final String jsonBody = "{" + "\"idZone\":2," + "\"idResponder\":1,"
				+ "\"idPatient\":2," + "\"patientFirstName\":\"Maurice\","
				+ "\"patientLastName\":\"Moss\"," + "\"patientGender\":\"M\","
				+ "\"patientAge\":50,"
				+ "\"patientAddress\":\"20 rue du Progrès 41360 Lunay \","
				+ "\"patientLatitude\":47.8106061,"
				+ "\"patientLongitude\": 0.9128109," + "\"idPathology\":41,"
				+ "\"dtStart\":null}";
		// When
		mockMvc.perform(post("/emergencies")
				.contentType(MediaType.APPLICATION_JSON).content(jsonBody))
				// Then
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.hospitalName")
						.value("CH VENDOME - MONTOIRE"))
				.andExpect(jsonPath("$.hospitalServiceName")
						.value("Médecine d'urgence"));

	}

}
