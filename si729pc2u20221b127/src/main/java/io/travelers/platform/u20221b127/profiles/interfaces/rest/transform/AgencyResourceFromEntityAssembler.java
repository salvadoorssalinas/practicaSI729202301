package io.travelers.platform.u20221b127.profiles.interfaces.rest.transform;

import io.travelers.platform.u20221b127.profiles.domain.model.aggregates.Agency;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.resources.AgencyResource;

public class AgencyResourceFromEntityAssembler {
    public static AgencyResource toResourceFromEntity(Agency entity) {
        return new AgencyResource(
                entity.getId(),
                entity.getBusiness(),
                entity.getName(),
                entity.getAddress(),
                entity.getLocation()
        );
    }
}
