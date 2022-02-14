package com.medhead.ers.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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

	@Test
	@DisplayName("Given: 72 id pour la pathologie Neurologie  "
			+ "When: consulte la liste des spécialisations avec ses hôpitaux "
			+ "Then: on a l'hôpital TROUSSEAU")
	void testFindAvailableHospitals() throws Exception {

		// Given
		long idZone = 24;
		long idPathologie = 72;

		// When
		List<HospitalPathologyDto> result = hospitalPathologyService
				.findAvailableHospitals(idZone, idPathologie);

		// Then
		assertThat(result.contains("CHRU TROUSSEAU - CHAMBRAY"));
	}

}
