package io.travelers.platform.u20221b127.profiles.domain.services;

import io.travelers.platform.u20221b127.profiles.domain.model.aggregates.Agency;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetAgencyByIdQuery;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetAllAgenciesQuery;

import java.util.List;
import java.util.Optional;

public interface AgencyQueryService {
    List<Agency> handle(GetAllAgenciesQuery query);
    Optional<Agency> handle(GetAgencyByIdQuery query);
}
