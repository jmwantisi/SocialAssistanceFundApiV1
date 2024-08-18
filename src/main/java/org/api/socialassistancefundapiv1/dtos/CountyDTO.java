package org.api.socialassistancefundapiv1.dtos;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CountyDTO {
	
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
	
	public List<SubCountyDTO> getSubCounties() {
		return subCounties;
	}
	
	public void setSubCounties(List<SubCountyDTO> subCounties) {
		this.subCounties = subCounties;
	}
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
    private String name;
	
	@JsonProperty("sub_counties")
    private List<SubCountyDTO> subCounties;
}
