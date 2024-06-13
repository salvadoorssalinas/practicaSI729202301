package io.travelers.platform.u20221b127.profiles.infrastructure.persistence.jpa.repositories;

import io.travelers.platform.u20221b127.profiles.domain.model.aggregates.Agency;
import io.travelers.platform.u20221b127.profiles.domain.model.valueobjects.AgencyAddress;
import io.travelers.platform.u20221b127.profiles.domain.model.valueobjects.AgencyLocation;
import io.travelers.platform.u20221b127.profiles.domain.model.valueobjects.AgencyName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    Optional<Agency> findByBusinessId(Long businessId);
    Optional<Agency> findByName(AgencyName name);
    Optional<Agency> findByAddress(AgencyAddress address);
    Optional<Agency> findByLocation(AgencyLocation location);
    Optional<Agency> findByBusinessIdAndName(Long businessId, AgencyName name);
}
