package io.travelers.platform.u20221b127.profiles.domain.model.aggregates;

import io.travelers.platform.u20221b127.profiles.domain.model.commands.CreateAgencyCommand;
import io.travelers.platform.u20221b127.profiles.domain.model.entities.Business;
import io.travelers.platform.u20221b127.profiles.domain.model.valueobjects.AgencyAddress;
import io.travelers.platform.u20221b127.profiles.domain.model.valueobjects.AgencyLocation;
import io.travelers.platform.u20221b127.profiles.domain.model.valueobjects.AgencyName;
import io.travelers.platform.u20221b127.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Represents an agency. It is an auditable aggregate root,
 * extends {@link AuditableAbstractAggregateRoot}.
 * @author Salvador Salinas
 */


@Entity
public class Agency extends AuditableAbstractAggregateRoot<Agency> {
    @ManyToOne
    @JoinColumn(name = "business_id")
    @NotNull
    private Business business;

    @Embedded
    @NotNull(message = "Name is required")
    private AgencyName name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "number", column = @Column(name = "address_number")),
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "address_zip_code")),
            @AttributeOverride(name = "country", column = @Column(name = "address_country"))
    })
    @NotNull(message = "Address is required")
    private AgencyAddress address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "location_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "location_longitude"))
    })
    @NotNull(message = "Location is required")
    private AgencyLocation location;

    public Agency() {
    }

    public Agency(Business business, AgencyName name, AgencyAddress address, AgencyLocation location) {
        this.business = business;
        this.name = name;
        this.address = address;
        this.location = location;
    }

    public Agency(CreateAgencyCommand command, Business business) {
        this.business = business;
        this.name = new AgencyName(command.name());
        this.address = new AgencyAddress(command.street(), command.number(), command.city(), command.zipCode(), command.country());
        this.location = new AgencyLocation(command.latitude(), command.longitude());
    }

    public Long getBusiness() {
        return business.getId();
    }

    public String getName() {
        return name.getAgencyName();
    }

    public String getAddress() {
        return address.getAgencyAddress();
    }

    public String getLocation() {
        return location.getAgencyLocation();
    }

}
