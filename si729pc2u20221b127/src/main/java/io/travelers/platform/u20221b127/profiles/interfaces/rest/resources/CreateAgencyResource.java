package io.travelers.platform.u20221b127.profiles.interfaces.rest.resources;

public record CreateAgencyResource(
        Long businessId,
        String name,
        String street,
        String number,
        String city,
        String zipCode,
        String country,
        Long latitude,
        Long longitude
) {
}
