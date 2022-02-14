/**
 * 
 */
package com.medhead.ers.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Mag
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Tests Endpoints Pathologies by hospital")
class HospitalPathologyControllerTest {

	/**
	 * Test method for
	 * {@link com.medhead.ers.controller.HospitalPathologyController#getHospitalPathologies()}.
	 */
	@Autowired
	public MockMvc mockMvc;

	@DisplayName("Given: dans la liste des spécialisations avec ses hôpitaux  When: consulte cette liste Then: on a Médecine Respiratoire")
	@Test
	void testGetHospitalPathologies() throws Exception {
		mockMvc.perform(get("/pathologies")).andExpect(status().isOk())
				.andExpect(content().string(containsString("41")));
	}

	/**
	 * Test method for
	 * {@link com.medhead.ers.controller.HospitalPathologyController#getHospitalByPathology(java.lang.Long)}.
	 */

	@DisplayName("Given: 72 id pour la pathologie Neurologie  When: consulte la liste des spécialisations avec ses hôpitaux Then: on a l'hôpital TROUSSEAU")
	@Test
	void testGetHospitalByPathology() throws Exception {
		mockMvc.perform(get("/pathologies/{id_pathology}", 72))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("TROUSSEAU")));
	}
}
