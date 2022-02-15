package com.medhead.ers.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Tests Endpoints API ERS")
class EmergencyControllerTest {

	@Autowired
	public MockMvc mockMvc;

	@Test
	@DisplayName("Given: id=1 dans le journal d'intervention des urgences  "
			+ "When: consulte le log " + "Then: concerne la patiente Magalie")
	void testGetEmergency() throws Exception {
		// Given : id = 1
		// When
		mockMvc.perform(get("/emergencies/{id}", 1))
				// Then
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.patientFirstName").value("Magalie"));
	}

	@Test
	@DisplayName("Given: dans le journal d'intervention des urgences  When: consulte le log Then: on a la ville de Lunay")
	void testGetEmergencies() throws Exception {
		mockMvc.perform(get("/emergencies")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Lunay")));
	}

	@Test
	@DisplayName("Given: urgence cardiologie à Lunay When: ERS Then: dispo en Cardio à Blois")
	void test1CreateEmergency() throws Exception {

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
		mockMvc.perform(post("/emergencies")
				.contentType(MediaType.APPLICATION_JSON).content(jsonBody))
				// Then
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.hospitalName").value(Matchers
						.containsStringIgnoringCase("CH BLOIS SIMONE VEIL")));
	}

	@Test
	@DisplayName("Given: urgence réspiratoire à Lunay When: ERS Then: dispo en Urgence à Vendôme")
	void test2CreateEmergency() throws Exception {

		// Given
		final String jsonBody = "{" + "\"idZone\":24," + "\"idResponder\":1,"
				+ "\"idPatient\":2," + "\"patientFirstName\":\"Maurice\","
				+ "\"patientLastName\":\"Moss\"," + "\"patientGender\":\"M\","
				+ "\"patientAge\":50,"
				+ "\"patientAddress\":\"20 rue du Progrès 41360 Lunay \","
				+ "\"patientLatitude\":47.8106061,"
				+ "\"patientLongitude\": 0.9128109," + "\"idPathology\":41,"
				+ "\"dtStart\":null}";
		// When
		mockMvc.perform(post("/emergencies")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonBody))

				// Then
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.hospitalName")
						.value("CH VENDOME - MONTOIRE"))
				.andExpect(jsonPath("$.hospitalServiceName")
						.value(Matchers.containsStringIgnoringCase("urgence")));

	}

	@Test
	@DisplayName("Given: urgence réspiratoire à Saint-Calais When: ERS Then: ALERTE aucun hôpital dispo dans la région")
	void test3CreateEmergency() throws Exception {

		// Given
		final String jsonBody = "{" + "\"idZone\":52," + "\"idResponder\":2,"
				+ "\"idPatient\":3," + "\"patientFirstName\":\"Roy\","
				+ "\"patientLastName\":\"Trenneman\","
				+ "\"patientGender\":\"M\"," + "\"patientAge\":36,"
				+ "\"patientAddress\":\"Rue Amédée-Savidan 72120 Saint-Calais\","
				+ "\"patientLatitude\":47.9211271,"
				+ "\"patientLongitude\":0.7429723," + "\"idPathology\":41,"
				+ "\"dtStart\":null}";
		// When
		mockMvc.perform(post("/emergencies")
				.contentType(MediaType.APPLICATION_JSON).content(jsonBody))
				// Then
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.hospitalName").isEmpty())
				.andExpect(jsonPath("$.instructions")
						.value(Matchers.containsStringIgnoringCase("Alerte")));
	}

}
