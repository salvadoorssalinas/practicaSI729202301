package io.travelers.platform.u20221b127.profiles.interfaces.rest.transform;

import io.travelers.platform.u20221b127.profiles.domain.model.entities.Business;
import io.travelers.platform.u20221b127.profiles.interfaces.rest.resources.BusinessResource;

public class BusinessResourceFromEntityAssembler {
    public static BusinessResource toResourceFromEntity(Business entity) {
        return new BusinessResource(
                entity.getId(),
                entity.getName());
    }
}
