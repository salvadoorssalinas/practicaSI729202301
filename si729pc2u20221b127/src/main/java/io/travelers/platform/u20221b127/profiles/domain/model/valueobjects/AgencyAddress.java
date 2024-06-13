package io.travelers.platform.u20221b127.profiles.domain.model.valueobjects;

public record AgencyAddress(String street, String number, String city, String zipCode, String country) {

    public AgencyAddress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street must not be null or blank");
        }
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Number must not be null or blank");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City must not be null or blank");
        }
        if (zipCode == null || zipCode.isBlank()) {
            throw new IllegalArgumentException("Zip code must not be null or blank");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country must not be null or blank");
        }
    }

    public String getAgencyAddress() {
        return street + " " + number + ", " + city + ", " + zipCode + ", " + country;
    }

}
