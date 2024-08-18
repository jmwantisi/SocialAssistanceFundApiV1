package org.api.socialassistancefundapiv1.dtos;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubCountyDTO {
	
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
	
	public CountyDTO getCounty() {
		return county;
	}
	
	public void setCounty(CountyDTO county) {
		this.county = county;
	}
	
	public List<LocationDTO> getLocations() {
		return locations;
	}
	
	public void setLocations(List<LocationDTO> locations) {
		this.locations = locations;
	}
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
    private String name;
	
	@JsonProperty("county")
    private CountyDTO county;
	
	@JsonProperty("locations")
    private List<LocationDTO> locations;
}
