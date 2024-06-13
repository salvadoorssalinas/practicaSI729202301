package io.travelers.platform.u20221b127.profiles.infrastructure.persistence.jpa.repositories;

import io.travelers.platform.u20221b127.profiles.domain.model.entities.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
}
