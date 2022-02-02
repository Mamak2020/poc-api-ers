package com.medhead.ers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.ers.model.Emergency;
import com.medhead.ers.model.HospitalPathologyDto;
import com.medhead.ers.repository.EmergencyRepository;
import com.medhead.ers.utils.DistanceUtils;

import lombok.Data;

/**
 * @author Mag ******************** WORK IN PROGRESS ...
 */
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
		try {

			/* => Search the patient geo location */
			if (emergency.getPatientLatitude() == null
					|| emergency.getPatientLongitude() == null) {
				emergency.setPatientLatitude((double) 10);
				emergency.setPatientLongitude((double) 10);
			}

			/*
			 * => Search the hospitals from zone and pathology with available
			 * beds
			 */
			List<HospitalPathologyDto> hospitalList = hospitalPathologyService
					.findAvailableHospitals(emergency.getIdZone(),
							emergency.getIdPathology());
			if (hospitalList.isEmpty() == true) {
				// No available beds for the patient pathology, search with
				// Default service
				System.out
						.println("Pas d'hopitaux de libre dans la pathologie");
				hospitalList = hospitalPathologyService.findAvailableHospitals(
						emergency.getIdZone(), DEFAULT_SERVICE);
				if (hospitalList.isEmpty() == true) {
					// No available beds for the patient into the search zone =>
					// ALERT !!!
					System.out.println(
							"Pas d'hopitaux dans la zone du patient !!!");
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
				// System.out.println("Index : " + i + " Distance : " +
				// distanceItem);
				if ((distanceItem < distance) || (i == 0)) {
					index = i;
					distance = distanceItem;
				}
				i++;
			}
			// System.out.println("indexFound : " + index + " Distance : " +
			// distance);

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

			// emergency.setDtResponse(LocalDateTime.now());
			String instructions;
			instructions = "Réservation: " + hospitalFound.getName() + " à "
					+ hospitalFound.getAddress() + " - Lattitude : "
					+ hospitalFound.getLatitude() + " - Longitute : "
					+ hospitalFound.getLongitude() + " - Distance: " + distance
					+ " kms" + " - Service: " + hospitalFound.getServiceName();

			emergency.setInstructions(instructions);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		/* => Save and return the the nearest hospital information */
		return emergencyRepository.save(emergency);

	}

}
