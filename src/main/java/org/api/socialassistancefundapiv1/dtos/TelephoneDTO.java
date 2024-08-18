package org.api.socialassistancefundapiv1.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TelephoneDTO {
	
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("phone_number")
    private String phoneNumber;
	
	@JsonProperty("type")
    private String type;
}

