package com.collabera.jump.ems.model;

import java.io.Serializable;

public class Address implements Serializable{

	private String city;
	
	private String street;
	
	private int zipcode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", street=" + street + ", zipcode=" + zipcode + "]";
	}
}
