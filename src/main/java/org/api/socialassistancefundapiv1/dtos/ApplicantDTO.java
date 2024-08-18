package org.api.socialassistancefundapiv1.dtos;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class ApplicantDTO {
	
    public Integer getId() {
		return id;
	}
    
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDate getApplicationDate() {
		return applicationDate;
	}
	
	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	public String getIdNumber() {
		return idNumber;
	}
	
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public Integer getCountyId() {
		return countyId;
	}
	
	public void setCountyId(int id) {
		this.countyId = id;
	}
	
	public Integer getSubCountyId() {
		return subCountyId;
	}
	
	public void setSubCountyId(int id) {
		this.subCountyId = id;
	}
	
	public Integer getLocationId() {
		return locationId;
	}
	
	public void setLocationId(int id) {
		this.locationId = id;
	}
	
	public Integer getSubLocationId() {
		return subLocationId;
	}
	
	public void setSubLocationId(int id) {
		this.subLocationId = id;
	}
	
	public Integer getVillageId() {
		return villageId;
	}
	
	public void setVillageId(int id) {
		this.villageId = id;
	}
	
	// this Ok
	public PostalAddressDTO getPostalAddress() {
		return postalAddress;
	}
	
	public void setPostalAddress(PostalAddressDTO postalAddress) {
		this.postalAddress = postalAddress;
	}
	public PhysicalAddressDTO getPhysicalAddress() {
		return physicalAddress;
	}
	
	public void setPhysicalAddress(PhysicalAddressDTO physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	
	public List<TelephoneDTO> getTelephones() {
		return telephones;
	}
	
	public void setTelephones(List<TelephoneDTO> telephones) {
		this.telephones = telephones;
	}
	
	public List<Integer> getApplicantProgrammes() {
		return applicantProgrammes;
	}
	
	public void setApplicantProgrammes(List<Integer> applicantProgrammes) {
		this.applicantProgrammes = applicantProgrammes;
	}
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("postal_address")
	private PostalAddressDTO postalAddress;
	
	@JsonProperty("physical_address")
    private PhysicalAddressDTO physicalAddress;
	
	@JsonProperty("telephones")
    private List<TelephoneDTO> telephones;
	
	@JsonProperty("applicant_programmes")
    private List<Integer> applicantProgrammes;
	
	@JsonProperty("applicantion_date")
	private LocalDate applicationDate;
	
	@JsonProperty("first_name")
    private String firstName;
	
	@JsonProperty("middle_name")
    private String middleName;
	
	@JsonProperty("last_name")
    private String lastName;
	
	@JsonProperty("sex")
    private String sex;
	
	@JsonProperty("age")
    private Integer age;
	
	@JsonProperty("marital_status")
    private String maritalStatus;
	
	@JsonProperty("id_number")
    private String idNumber;
	
	@JsonProperty("county_id")
    private Integer countyId;
	
	@JsonProperty("sub_county_id")
    private Integer subCountyId;
	
	@JsonProperty("location_id")
    private Integer locationId;
	
	@JsonProperty("sub_location_id")
    private Integer subLocationId;
	
	@JsonProperty("village_id")
    private Integer villageId;
}


