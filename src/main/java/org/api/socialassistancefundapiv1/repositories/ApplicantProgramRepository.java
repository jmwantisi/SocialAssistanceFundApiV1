package org.api.socialassistancefundapiv1.repositories;
import org.api.socialassistancefundapiv1.models.ApplicantProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantProgramRepository extends JpaRepository<ApplicantProgram, Integer> {}