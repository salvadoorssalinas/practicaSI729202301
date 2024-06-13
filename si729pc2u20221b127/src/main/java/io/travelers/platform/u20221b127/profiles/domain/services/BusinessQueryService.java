package io.travelers.platform.u20221b127.profiles.domain.services;

import io.travelers.platform.u20221b127.profiles.domain.model.entities.Business;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetAllBusinessesQuery;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetBusinessByIdQuery;

import java.util.List;
import java.util.Optional;

public interface BusinessQueryService {
    List<Business> handle(GetAllBusinessesQuery query);
    Optional<Business> handle(GetBusinessByIdQuery query);
}
