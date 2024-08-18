package org.api.socialassistancefundapiv1.repositories;
import org.api.socialassistancefundapiv1.models.PhysicalAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalAddressRepository extends JpaRepository<PhysicalAddress, Integer> {}
