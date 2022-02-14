package com.medhead.ers.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Tag("UnitTest")
class DistanceUtilsTest {

	@Test
	@DisplayName("Given: les latitudes et longitudes des points A et B "
			+ "When: calcule la distance en kms entre les points A et B selon la formule Haversine "
			+ "Then: est de 11kms")
	void testCalcDistance() throws Exception {
		// Given
		final double latA = 47.8106061;
		final double longA = 0.9128109;
		final double latB = 47.7941772;
		final double longB = 1.0630365;

		// When
		long result = (long) DistanceUtils.calcDistance(latA, longA, latB,
				longB);

		// Then
		assertThat(result).isEqualTo(11);

	}

}
