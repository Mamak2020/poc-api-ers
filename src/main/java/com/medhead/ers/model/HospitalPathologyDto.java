package com.medhead.ers.model;

public interface HospitalPathologyDto {

	Long getId(); /* Hospital Id */

	String getName(); /* Hospital Name */

	String getAddress(); /* Hospital */

	Double getLatitude();

	Double getLongitude();

	Long getIdService();

	String getServiceName();

	// Long getDistance();

	// void setDistance(Long distance); // MAG => KO
}
