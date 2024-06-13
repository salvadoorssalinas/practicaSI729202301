package io.travelers.platform.u20221b127.profiles.interfaces.rest.transform;

import io.travelers.platform.u20221b127.profiles.domain.model.commands.CreateBusinessCommand;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.resources.CreateBusinessResource;

public class CreateBusinessCommandFromResourceAssembler {
    public static CreateBusinessCommand toCommandFromResource(CreateBusinessResource resource) {
        return new CreateBusinessCommand(
                resource.name()
        );
    }
}
