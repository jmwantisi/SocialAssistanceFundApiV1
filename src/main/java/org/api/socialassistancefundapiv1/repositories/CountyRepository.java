package org.api.socialassistancefundapiv1.repositories;
import java.util.Optional;
import org.api.socialassistancefundapiv1.models.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepository extends JpaRepository<County, Integer> { 
	Optional<County> findByName(String name);
}
