package org.api.socialassistancefundapiv1.repositories;
import java.util.Optional;
import org.api.socialassistancefundapiv1.models.SubLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubLocationRepository extends JpaRepository<SubLocation, Integer> {
	Optional<SubLocation> findByName(String name);
}
