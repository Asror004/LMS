package com.company.domain.basicsOfBasics;

import com.company.domain.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Address extends Auditable {
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;

    @Builder(builderMethodName = "childBuilder")
    public Address(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, String country, String region, String city, String street) {
        super(id, createdAt, updatedAt, deleted);
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
    }
}
