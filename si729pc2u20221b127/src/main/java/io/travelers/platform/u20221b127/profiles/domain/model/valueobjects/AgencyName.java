package io.travelers.platform.u20221b127.profiles.domain.model.valueobjects;

public record AgencyName(String name) {

    public AgencyName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Agency name must not be null or blank");
        }
    }

    public String getAgencyName() {
        return name;
    }
}
