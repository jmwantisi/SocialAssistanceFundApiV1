package org.api.socialassistancefundapiv1.repositories;
import org.api.socialassistancefundapiv1.models.PostalAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalAddressRepository extends JpaRepository<PostalAddress, Integer> {}
