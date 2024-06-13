package io.travelers.platform.u20221b127.profiles.application.internal.commandservices;

import io.travelers.platform.u20221b127.profiles.domain.model.commands.CreateBusinessCommand;
import io.travelers.platform.u20221b127.profiles.domain.model.entities.Business;
import io.travelers.platform.u20221b127.profiles.domain.services.BusinessCommandService;
import io.travelers.platform.u20221b127.profiles.infrastructure.persistence.jpa.repositories.BusinessRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessCommandServiceImpl implements BusinessCommandService {
    private final BusinessRepository businessRepository;

    public BusinessCommandServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    /**
     * Handle the command to create a business
     * @param command the command to create a business
     * @return the created business
     */
    @Override
    public Optional<Business> handle(CreateBusinessCommand command) {
        // Check if the business already exists by name
        businessRepository.findByName(command.name())
                .ifPresent(business -> {
                    throw new IllegalArgumentException("Business with name already exists");
                });

        var business = new Business(command.name());
        businessRepository.save(business);
        return Optional.of(business);
    }


}
