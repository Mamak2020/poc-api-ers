package com.medhead.ers.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medhead.ers.model.HospitalPathology;
import com.medhead.ers.model.HospitalPathologyDto;

@Repository
@Transactional // ==> TO CHECK BY A CODE REVIEW
public interface HospitalPathologyRepository extends CrudRepository<HospitalPathology, Long> {
//	List<HospitalPathology> findByIdHospital(Long idHospital);
	@Modifying
	@Query(value = "update hospital_pathology set available_beds = (available_beds - 1) where id = ?", nativeQuery = true)
	int bookingBed(long id);

	@Query(value = "SELECT h.id id, h.name name, h.address address, h.latitude latitude, h.longitude longitude, hp.id idservice, p.name_pathology serviceName\r\n"
			+ "		FROM public.hospital h, hospital_pathology hp, pathology p\r\n" + "		where h.id_zone=?\r\n"
			+ "		and h.is_available_bed = true\r\n" + "		and hp.id_hospital = h.id\r\n"
			+ "		and hp.available_beds > 0\r\n" + "		and  hp.id_pathology = ?\r\n"
			+ "		and hp.id_pathology = p.id", nativeQuery = true)
	List<HospitalPathologyDto> findAvailableHospitals(long idZone, long idPathology);

}
