package io.travelers.platform.u20221b127.profiles.domain.services;

import io.travelers.platform.u20221b127.profiles.domain.model.commands.CreateBusinessCommand;
import io.travelers.platform.u20221b127.profiles.domain.model.entities.Business;

import java.util.Optional;

public interface BusinessCommandService {
    Optional<Business> handle(CreateBusinessCommand command);
}
