package io.travelers.platform.u20221b127.profiles.application.internal.queryservices;

import io.travelers.platform.u20221b127.profiles.domain.model.aggregates.Agency;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetAgencyByIdQuery;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetAllAgenciesQuery;
import io.travelers.platform.u20221b127.profiles.domain.services.AgencyQueryService;
import io.travelers.platform.u20221b127.profiles.infrastructure.persistence.jpa.repositories.AgencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyQueryServiceImpl implements AgencyQueryService {
    private final AgencyRepository agencyRepository;

    public AgencyQueryServiceImpl(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    /**
     * Handle the query to get all agencies
     * @param query the query to get all agencies
     * @return the list of agencies
     * @author Salvador Salinas
     */
    @Override
    public List<Agency> handle(GetAllAgenciesQuery query) {
        return agencyRepository.findAll();
    }

    /**
     * Handle the query to get an agency by id
     * @param query the query to get an agency by id
     * @return the agency
     * @author Salvador Salinas
     */
    @Override
    public Optional<Agency> handle(GetAgencyByIdQuery query) {
        return agencyRepository.findById(query.id());
    }

}
