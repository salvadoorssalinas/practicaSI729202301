package io.travelers.platform.u20221b127.profiles.interfaces.rest.transform;

import io.travelers.platform.u20221b127.profiles.domain.model.commands.CreateAgencyCommand;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.resources.CreateAgencyResource;

public class CreateAgencyCommandFromResourceAssembler {
    public static CreateAgencyCommand toCommandFromResource(CreateAgencyResource resource) {
        return new CreateAgencyCommand(
                resource.businessId(),
                resource.name(),
                resource.street(),
                resource.number(),
                resource.city(),
                resource.zipCode(),
                resource.country(),
                resource.latitude(),
                resource.longitude()
        );
    }
}
