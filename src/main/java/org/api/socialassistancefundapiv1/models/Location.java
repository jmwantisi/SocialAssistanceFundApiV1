package org.api.socialassistancefundapiv1.models;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SubCounty getSubCounty() {
		return subCounty;
	}

	public void setSubCounty(SubCounty subCounty) {
		this.subCounty = subCounty;
	}

	public List<SubLocation> getSubLocations() {
		return subLocations;
	}

	public void setSubLocations(List<SubLocation> subLocations) {
		this.subLocations = subLocations;
	}

	@Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_county_id", nullable = false, columnDefinition = "INT")
    @JsonIgnore
    private SubCounty subCounty;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubLocation> subLocations;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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
