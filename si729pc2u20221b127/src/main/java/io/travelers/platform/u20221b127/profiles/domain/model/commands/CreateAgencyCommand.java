package io.travelers.platform.u20221b127.profiles.domain.model.commands;

public record CreateAgencyCommand(
        Long businessId,
        String name,
        String street,
        String number,
        String city,
        String zipCode,
        String country,
        Long latitude,
        Long longitude) {
}
