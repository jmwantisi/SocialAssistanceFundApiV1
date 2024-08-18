package org.api.socialassistancefundapiv1.dtos;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public class LocationDTO {
	
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
	
	public SubCountyDTO getSubCounty() {
		return subCounty;
	}
	
	public void setSubCounty(SubCountyDTO subCounty) {
		this.subCounty = subCounty;
	}
	
	public List<SubLocationDTO> getSubLocations() {
		return subLocations;
	}
	
	public void setSubLocations(List<SubLocationDTO> subLocations) {
		this.subLocations = subLocations;
	}
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
    private String name;
	
	@JsonProperty("sub_county")
    private SubCountyDTO subCounty;
	
	@JsonProperty("sub_locations")
    private List<SubLocationDTO> subLocations;
}
