package io.travelers.platform.u20221b127.profiles.domain.model.valueobjects;

public record AgencyLocation(Long latitude, Long longitude) {
    public AgencyLocation {
        if (latitude == null) {
            throw new IllegalArgumentException("Latitude must not be null");
        }
        if (longitude == null) {
            throw new IllegalArgumentException("Longitude must not be null");
        }
    }
    public String getAgencyLocation() {
        return String.format("%s %s", latitude, longitude);
    }
}
