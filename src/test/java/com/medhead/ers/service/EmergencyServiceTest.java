package com.medhead.ers.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.medhead.ers.model.Emergency;
@SpringBootTest
class EmergencyServiceTest {

	private static Instant startedAt;
	Emergency emergency = new Emergency();

	@Autowired
	private EmergencyService emergencyService; // = new EmergencyService();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// System.out.println("Appel avant tous les tests");
		// startedAt = Instant.now();

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// System.out.println("Appel après tous les tests");
		/*
		 * final Instant endedAt = Instant.now(); final long duration =
		 * Duration.between(startedAt, endedAt).toMillis();
		 * System.out.println(MessageFormat.format("Durée des tests : {0} ms",
		 * duration));
		 */
	}

	@BeforeEach
	void setUpBeforeMethod() throws Exception {
		// System.out.println("Appel avant chaque test");
		startedAt = Instant.now();

	}

	@AfterEach
	void setUpAfterMethod() throws Exception {
		// System.out.println("Appel après chaque test");
		final Instant endedAt = Instant.now();
		final long duration = Duration.between(startedAt, endedAt).toMillis();
		// System.out.println(
		// MessageFormat.format("Durée du test : {0} ms", duration));
	}

	@DisplayName("Réserve un lit dans le service Chirurgie cardio de l'hôpital le plus proche de Lunay")
	@Test
	void testCreateEmergency() throws Exception {

		// Given
		emergency.setIdZone((long) 24);
		emergency.setIdResponder((long) 1);
		emergency.setIdPatient((long) 1);
		emergency.setPatientFirstName("Maurice");
		emergency.setPatientLastName("Moss");
		emergency.setPatientGender('M');
		emergency.setPatientAge((long) 50);
		emergency.setPatientAddress("20 rue du Progrès 41360 Lunay");
		emergency.setPatientLatitude((double) 47.8106061);
		emergency.setPatientLongitude((double) 0.9128109);
		emergency.setIdPathology((long) 70);

		// When
		Emergency result = emergencyService.requestMedicalEmergency(emergency);

		// Then
		assertThat(result.getIdHospital()).isEqualTo(3);
		assertThat(result.getIdHospitalService()).isEqualTo(4);

		// Use case : No available beds change to default service

	}

}
