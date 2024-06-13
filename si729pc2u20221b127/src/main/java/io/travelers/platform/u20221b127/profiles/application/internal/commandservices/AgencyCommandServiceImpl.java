package io.travelers.platform.u20221b127.profiles.application.internal.commandservices;

import io.travelers.platform.u20221b127.profiles.domain.model.aggregates.Agency;
import io.travelers.platform.u20221b127.profiles.domain.model.commands.CreateAgencyCommand;
import io.travelers.platform.u20221b127.profiles.domain.model.valueobjects.AgencyAddress;
import io.travelers.platform.u20221b127.profiles.domain.model.valueobjects.AgencyLocation;
import io.travelers.platform.u20221b127.profiles.domain.model.valueobjects.AgencyName;
import io.travelers.platform.u20221b127.profiles.domain.services.AgencyCommandService;
import io.travelers.platform.u20221b127.profiles.infrastructure.persistence.jpa.repositories.AgencyRepository;
import io.travelers.platform.u20221b127.profiles.infrastructure.persistence.jpa.repositories.BusinessRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgencyCommandServiceImpl implements AgencyCommandService {
    private final AgencyRepository agencyRepository;
    private final BusinessRepository businessRepository;

    public AgencyCommandServiceImpl(AgencyRepository agencyRepository, BusinessRepository businessRepository) {
        this.agencyRepository = agencyRepository;
        this.businessRepository = businessRepository;
    }

    /**
     * Handle the command to create an agency
     * @param command the command to create an agency
     * @return the created agency
     * @throws IllegalArgumentException if the agency already exists
     * @author Salvador Salinas
     */
    @Override
    public Optional<Agency> handle(CreateAgencyCommand command) {
        // Check if the business exists
        var business = businessRepository.findById(command.businessId());
        if (business.isEmpty()) {
            throw new IllegalArgumentException("Business not found");
        }

        // Check if the agency already exists by businessId and name
        var name = new AgencyName(command.name());
        agencyRepository.findByBusinessIdAndName(command.businessId(), name)
                .ifPresent(agency -> {
                    throw new IllegalArgumentException("Agency with businessId and name already exists");
                });

        // Check if the agency already exists by location
        var location = new AgencyLocation(command.latitude(), command.longitude());
        agencyRepository.findByLocation(location)
                .ifPresent(agency -> {
                    throw new IllegalArgumentException("Agency with location already exists");
                });

        // Check if the agency already exists by address
        var address = new AgencyAddress(command.street(), command.number(), command.city(), command.zipCode(), command.country());
        agencyRepository.findByAddress(address)
                .ifPresent(agency -> {
                    throw new IllegalArgumentException("Agency with address already exists");
                });

        var agency = new Agency(business.get(), name, address, location);
        agencyRepository.save(agency);
        return Optional.of(agency);
    }

}
