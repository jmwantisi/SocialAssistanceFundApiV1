package org.api.socialassistancefundapiv1.models;
import javax.persistence.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "applicants")
public class Applicant {
	
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
	
	public LocalDate getDob() {
		return dob;
	}
	
	public void setDob(LocalDate dob) {
		this.dob = dob;
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

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public SubCounty getSubCounty() {
		return subCounty;
	}

	public void setSubCounty(SubCounty subCounty) {
		this.subCounty = subCounty;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public SubLocation getSubLocation() {
		return subLocation;
	}

	public void setSubLocation(SubLocation subLocation) {
		this.subLocation = subLocation;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public PostalAddress getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(PostalAddress postalAddress) {
		this.postalAddress = postalAddress;
	}

	public PhysicalAddress getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(PhysicalAddress physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public List<Telephone> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}
	
   @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public int get_void() {
		return _void;
	}

	public void set_void(int _void) {
		this._void = _void;
	}

	public List<ApplicantProgram> getApplicantProgrammes() {
		return applicantProgrammes;
	}

	public void setApplicantProgrammes(List<ApplicantProgram> applicantProgrammes) {
		this.applicantProgrammes = applicantProgrammes;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "application_date", nullable = true)
    private LocalDate applicationDate;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "sex", nullable = false)
    private String sex;
    
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

	@Column(name = "age", nullable = false)
    private int age;

    @Column(name = "marital_status", nullable = false)
    private String maritalStatus;

    @Column(name = "id_number", nullable = false)
    private String idNumber;

    @ManyToOne
    @JoinColumn(name = "county_id", columnDefinition = "INT", nullable = false)
    private County county;

    @ManyToOne
    @JoinColumn(name = "sub_county_id", columnDefinition = "INT", nullable = false)
    private SubCounty subCounty;

    @ManyToOne
    @JoinColumn(name = "location_id", columnDefinition = "INT", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "sub_location_id", columnDefinition = "INT", nullable = false)
    private SubLocation subLocation;

    @ManyToOne
    @JoinColumn(name = "village_id", columnDefinition = "INT", nullable = false)
    private Village village;
    
    @ManyToOne
    @JoinColumn(name = "postal_address_id", columnDefinition = "INT", nullable = false)
    private PostalAddress postalAddress;

    @ManyToOne
    @JoinColumn(name = "physical_address_id", columnDefinition = "INT", nullable = false)
    private PhysicalAddress physicalAddress;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telephone> telephones;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "void", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int _void;

	@ManyToMany
    @JoinTable(
        name = "applicant_programs",
        joinColumns = @JoinColumn(name = "applicant_id", columnDefinition = "INT"),
        inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private List<ApplicantProgram> applicantProgrammes;

}


