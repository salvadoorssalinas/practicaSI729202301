package io.travelers.platform.u20221b127.profiles.domain.services;

import io.travelers.platform.u20221b127.profiles.domain.model.aggregates.Agency;
import io.travelers.platform.u20221b127.profiles.domain.model.commands.CreateAgencyCommand;

import java.util.Optional;

public interface AgencyCommandService {
    Optional<Agency> handle(CreateAgencyCommand command);
}
