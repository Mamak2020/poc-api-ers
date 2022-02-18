package com.medhead.ers.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.ers.model.Emergency;
import com.medhead.ers.model.HospitalPathologyDto;
import com.medhead.ers.repository.EmergencyRepository;
import com.medhead.ers.utils.DistanceUtils;

import lombok.Data;

@Data
@Service
public class EmergencyService {

	private static final long DEFAULT_SERVICE = 16; // EMERGENCY SERVICE

	@Autowired
	private EmergencyRepository emergencyRepository;

	@Autowired
	private HospitalPathologyService hospitalPathologyService;

	@Autowired
	private DistanceUtils distanceUtils;

	public Optional<Emergency> getEmergency(final Long id) {
		return emergencyRepository.findById(id);
	}

	public Iterable<Emergency> getEmergencies() {
		return emergencyRepository.findAll();
	}

	public Emergency requestMedicalEmergency(Emergency emergency) {
		final Instant startedAt = Instant.now();

		/* => Search the patient geo location */
		if (emergency.getPatientLatitude() == null
				|| emergency.getPatientLongitude() == null) {
			/* Add an API Call to get the geo localisation */
			emergency.setPatientLatitude((double) 47);
			emergency.setPatientLongitude((double) 1);
		}

		/*
		 * => Search the hospitals from zone and pathology with available beds
		 */
		List<HospitalPathologyDto> hospitalList = hospitalPathologyService
				.findAvailableHospitals(emergency.getIdZone(),
						emergency.getIdPathology());

		/*
		 * => No available beds for the patient pathology, search with Default
		 * service
		 */
		if (hospitalList.isEmpty()) {
			hospitalList = hospitalPathologyService.findAvailableHospitals(
					emergency.getIdZone(), DEFAULT_SERVICE);
			if (hospitalList.isEmpty()) {
				// No available beds for the patient into the search zone =>
				// ALERT !!!
				emergency.setInstructions(
						"Alerte!!! Pas de lits de disponible dans les hopitaux de la zone d'intervention du patient. Faire une ERS dans zones alentoures.)");
				return emergencyRepository.save(emergency);

			}
		}

		long distance = 0;
		int index = 0;
		int i = 0;

		// MAG => TO BE changed to a for loop
		for (HospitalPathologyDto item : hospitalList) {
			long distanceItem = (long) DistanceUtils.calcDistance(
					emergency.getPatientLatitude(),
					emergency.getPatientLongitude(), item.getLatitude(),
					item.getLongitude());
			if ((distanceItem < distance) || (i == 0)) {
				index = i;
				distance = distanceItem;
			}
			i++;
		}

		// Get the nearest hospital
		HospitalPathologyDto hospitalFound = hospitalList.get(index);

		/*
		 * => Book the bed to the nearest hospital
		 */
		hospitalPathologyService.bookingBed(hospitalFound.getIdService());

		/*
		 * => Complete the log emergency request with the nearest hospital
		 * information
		 */
		emergency.setIdHospital(hospitalFound.getId());
		emergency.setHospitalName(hospitalFound.getName());
		emergency.setHospitalAddress(hospitalFound.getAddress());
		emergency.setHospitalLatitude(hospitalFound.getLatitude());
		emergency.setHospitalLongitude(hospitalFound.getLongitude());
		emergency.setHospitalServiceName(hospitalFound.getServiceName());
		emergency.setIdHospitalService(hospitalFound.getIdService());
		emergency.setDistance(distance);

		String instructions;
		instructions = "Réservation: " + hospitalFound.getName() + " à "
				+ hospitalFound.getAddress() + " - Lattitude : "
				+ hospitalFound.getLatitude() + " - Longitute : "
				+ hospitalFound.getLongitude() + " - Distance: " + distance
				+ " kms" + " - Service: " + hospitalFound.getServiceName();

		emergency.setInstructions(instructions);

		final Instant endedAt = Instant.now();
		final long duration = Duration.between(startedAt, endedAt).toMillis();
		emergency.setDuration(duration);

		/* => Save and return the the nearest hospital information */
		return emergencyRepository.save(emergency);

	}

}
