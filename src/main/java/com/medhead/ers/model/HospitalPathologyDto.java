package com.medhead.ers.model;

public interface HospitalPathologyDto {

	Long getId();

	String getName();

	String getAddress();

	Double getLatitude();

	Double getLongitude();

	Long getIdService();

	String getServiceName();

	Long getDistance();

	void setDistance(Long distance); // MAG => KO
}
