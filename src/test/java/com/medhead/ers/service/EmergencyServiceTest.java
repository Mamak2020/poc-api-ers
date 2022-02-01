package com.medhead.ers.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
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
		System.out.println(
				MessageFormat.format("Durée du test : {0} ms", duration));
	}

	@Timeout(unit = TimeUnit.MILLISECONDS, value = 4000)
	@Test
	void testCreateEmergency() throws Exception {
		// Use case : No available beds change to default service
		String instruction = "Réservation: Hopital de Meaux à Meaux - Lattitude : 3.0 - Longitute : 3.0 - Distance: 157 kms - Service: Soins intensifs";

		// Given
		emergency.setIdZone((long) 11);
		emergency.setIdResponder((long) 1);
		emergency.setIdPatient((long) 1);
		emergency.setPatientFirstName("Maurice");
		emergency.setPatientLastName("Moss");
		emergency.setPatientGender('M');
		emergency.setPatientAge((long) 50);
		emergency.setPatientAddress("111 La Fosse 41360 Lunay");
		emergency.setPatientLatitude((double) 47.833);
		emergency.setPatientLongitude((double) 0.781);
		emergency.setIdPathology((long) 70);

		// When
		Emergency result = emergencyService.requestMedicalEmergency(emergency);

		// Then
		assertThat(result.getIdHospital()).isEqualTo(1);

		assertThat(result.getIdHospitalService()).isEqualTo(1);
		// assertThat(result.getInstructions()).isEqualTo(instruction);

	}

}