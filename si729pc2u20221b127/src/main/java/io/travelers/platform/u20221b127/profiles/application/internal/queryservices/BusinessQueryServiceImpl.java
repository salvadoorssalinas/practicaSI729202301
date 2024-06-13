package io.travelers.platform.u20221b127.profiles.application.internal.queryservices;

import io.travelers.platform.u20221b127.profiles.application.internal.commandservices.BusinessCommandServiceImpl;
import io.travelers.platform.u20221b127.profiles.domain.model.entities.Business;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetAllBusinessesQuery;
import io.travelers.platform.u20221b127.profiles.domain.model.queries.GetBusinessByIdQuery;
import io.travelers.platform.u20221b127.profiles.domain.services.BusinessQueryService;
import io.travelers.platform.u20221b127.profiles.infrastructure.persistence.jpa.repositories.BusinessRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessQueryServiceImpl implements BusinessQueryService {
    private final BusinessRepository businessRepository;

    public BusinessQueryServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    /**
     * Handle the query to get all businesses
     * @param query the query to get all businesses
     * @return the list of businesses
     */
    @Override
    public List<Business> handle(GetAllBusinessesQuery query) {
        return businessRepository.findAll();
    }

    /**
     * Handle the query to get a business by id
     * @param query the query to get a business by id
     * @return the business
     */
    @Override
    public Optional<Business> handle(GetBusinessByIdQuery query) {
        return businessRepository.findById(query.id());
    }

}
