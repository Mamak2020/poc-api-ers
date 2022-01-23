package com.medhead.ers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medhead.ers.model.Hospital;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long> {

}
