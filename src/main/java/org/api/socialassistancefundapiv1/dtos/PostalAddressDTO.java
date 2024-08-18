package org.api.socialassistancefundapiv1.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostalAddressDTO {
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2() {
		return addressLine2;
	}
	
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("address_line_1")
    private String addressLine1;
	
	@JsonProperty("address_line_2")
    private String addressLine2;
	
	@JsonProperty("postal_code")
    private String postalCode;
}
