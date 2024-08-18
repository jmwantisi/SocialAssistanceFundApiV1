package org.api.socialassistancefundapiv1.repositories;
import java.util.Optional;
import org.api.socialassistancefundapiv1.models.SubCounty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCountyRepository extends JpaRepository<SubCounty, Integer> { 
	Optional<SubCounty> findByName(String name);
}
