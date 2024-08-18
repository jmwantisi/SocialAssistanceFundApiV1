package org.api.socialassistancefundapiv1.dtos;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgramDTO {
	
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<ApplicantProgramDTO> getApplicantPrograms() {
		return applicantPrograms;
	}
	
	public void setApplicantPrograms(List<ApplicantProgramDTO> applicantPrograms) {
		this.applicantPrograms = applicantPrograms;
	}
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
    private String name;
	
	@JsonProperty("description")
    private String description;
	
	@JsonProperty("applicant_programs")
    private List<ApplicantProgramDTO> applicantPrograms;
}
