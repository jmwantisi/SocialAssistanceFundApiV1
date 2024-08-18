package org.api.socialassistancefundapiv1.models;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "applicant_programs")
public class ApplicantProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", columnDefinition = "INT", nullable = false)
	@JsonIgnore
    private Applicant applicant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", columnDefinition = "INT", nullable = false)
    @JsonIgnore
    private Program program;

    @Column(name = "application_date", nullable = true)
    private LocalDate applicationDate;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    public int getVoid() {
		return voidColumn;
	}

	public void setVoid(int _void) {
		this.voidColumn = _void;
	}

	@Column(name = "void", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int voidColumn;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}

