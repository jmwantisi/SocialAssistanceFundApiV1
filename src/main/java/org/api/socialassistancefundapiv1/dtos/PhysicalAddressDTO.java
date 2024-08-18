package org.api.socialassistancefundapiv1.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhysicalAddressDTO {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("street")
    private String street;
	
	@JsonProperty("city")
    private String city;
	
	@JsonProperty("house_number")
    private String houseNumber;
}

