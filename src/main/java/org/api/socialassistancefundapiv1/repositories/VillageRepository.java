package org.api.socialassistancefundapiv1.repositories;
import java.util.List;

import org.api.socialassistancefundapiv1.models.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageRepository extends JpaRepository<Village, Integer> {
	List<Village> findBySubLocationId(int subLocationId);
}
