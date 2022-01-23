package com.medhead.ers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medhead.ers.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

}
