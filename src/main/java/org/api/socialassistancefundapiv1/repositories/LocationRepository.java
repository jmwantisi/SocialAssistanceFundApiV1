package org.api.socialassistancefundapiv1.repositories;
import java.util.List;
import java.util.Optional;
import org.api.socialassistancefundapiv1.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
	Optional<Location> findByName(String name);
	List<Location> findBySubCountyId(int subCountyId);
}
