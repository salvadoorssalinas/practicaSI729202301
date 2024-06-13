package io.travelers.platform.u20221b127.profiles.domain.model.entities;

import io.travelers.platform.u20221b127.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Business extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Business() {
    }

    public Business(String name) {
        this.name = name;
    }

}
