package org.api.socialassistancefundapiv1.models;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "counties")
public class County {

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

	public List<SubCounty> getSubCounties() {
		return subCounties;
	}

	public void setSubCounties(List<SubCounty> subCounties) {
		this.subCounties = subCounties;
	}

	@Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "county", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubCounty> subCounties;
    
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
