package io.travelers.platform.u20221b127.profiles.interfaces.rest.resources;

public record AgencyResource(
        Long id,
        Long businessId,
        String name,
        String address,
        String location
) {
}
