package com.medhead.ers.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.medhead.ers.model.HospitalPathologyDto;
import com.medhead.ers.repository.HospitalPathologyRepository;

@SpringBootTest
class HospitalPathologyServiceTest {

	@Autowired
	HospitalPathologyService hospitalPathologyService;

	@Autowired
	HospitalPathologyRepository hospitalPathologyRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Liste des hôpitaux disponibles en fonction de la zone et de la pathologie")
	void testFindAvailableHospitals() {

		// Given
		long idZone = 24;
		long idPathologie = 70;

		// When
		List<HospitalPathologyDto> result = hospitalPathologyService
				.findAvailableHospitals(idZone, idPathologie);

		// Then
		assertThat(result.get(0).getServiceName())
				.isEqualTo("Chirurgie cardiothoracique");
	}

	/*
	 * @Test
	 * 
	 * @DisplayName("Réserve un lit dans le service Chirurgie cardio de l'hôpital de Chateaudun"
	 * ) void testBookingBed() {
	 * 
	 * // Given long idService = 11;
	 * 
	 * // When hospitalPathologyService.bookingBed(idService);
	 * 
	 * // Then
	 * assertThat(hospitalPathologyRepository.findById(idService)).isNotNull();
	 * 
	 * }
	 */
}
