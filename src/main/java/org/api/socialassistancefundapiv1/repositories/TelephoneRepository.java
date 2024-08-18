package org.api.socialassistancefundapiv1.repositories;
import org.api.socialassistancefundapiv1.models.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Integer> {}
