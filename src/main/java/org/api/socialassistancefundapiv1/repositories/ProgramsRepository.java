package org.api.socialassistancefundapiv1.repositories;
import org.api.socialassistancefundapiv1.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramsRepository extends JpaRepository<Program, Integer> {
	Optional<Program> findByName(String name);
}
