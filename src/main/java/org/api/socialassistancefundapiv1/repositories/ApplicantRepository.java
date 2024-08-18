package org.api.socialassistancefundapiv1.repositories;
import org.api.socialassistancefundapiv1.models.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    Page<Applicant> findAll(Pageable pageable);
    List<Applicant> findByLastNameContaining(String lastName);
}

