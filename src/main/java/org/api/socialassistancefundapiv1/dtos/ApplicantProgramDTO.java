package org.api.socialassistancefundapiv1.dtos;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicantProgramDTO {
	
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
	public ApplicantDTO getApplicant() {
		return applicant;
	}
	
	public void setApplicant(ApplicantDTO applicant) {
		this.applicant = applicant;
	}
	
	public ProgramDTO getProgram() {
		return program;
	}
	
	public void setProgram(ProgramDTO program) {
		this.program = program;
	}
	
	public LocalDate getApplicationDate() {
		return applicationDate;
	}
	
	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}
	
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("applicant")
    private ApplicantDTO applicant;
	
	@JsonProperty("program")
    private ProgramDTO program;
	
	@JsonProperty("application_date")
    private LocalDate applicationDate;
	
}

