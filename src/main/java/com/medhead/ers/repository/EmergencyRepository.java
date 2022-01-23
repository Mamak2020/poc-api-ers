package com.medhead.ers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medhead.ers.model.Emergency;

@Repository
public interface EmergencyRepository extends CrudRepository<Emergency, Long> {

}
