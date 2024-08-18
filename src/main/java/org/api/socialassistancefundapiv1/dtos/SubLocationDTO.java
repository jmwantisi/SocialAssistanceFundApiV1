package org.api.socialassistancefundapiv1.dtos;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubLocationDTO {
	
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
	
	public LocationDTO getLocation() {
		return location;
	}
	
	public void setLocation(LocationDTO location) {
		this.location = location;
	}
	
	public List<VillageDTO> getVillages() {
		return villages;
	}
	
	public void setVillages(List<VillageDTO> villages) {
		this.villages = villages;
	}
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
    private String name;
	
	@JsonProperty("location")
    private LocationDTO location;
	
	@JsonProperty("villages")
    private List<VillageDTO> villages;
}
