package org.api.socialassistancefundapiv1.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VillageDTO {
	
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public SubLocationDTO getSubLocation() {
		return subLocation;
	}
	
	public void setSubLocation(SubLocationDTO subLocation) {
		this.subLocation = subLocation;
	}
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
    private String name;
	
	@JsonProperty("sub_location")
    private SubLocationDTO subLocation;
}
