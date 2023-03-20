package com.company.domain.auth;

import com.company.domain.Auditable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthRole extends Auditable {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String code;

    @Builder(builderMethodName = "childBuilder")
    public AuthRole(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, String name, String code) {
        super(id, createdAt, updatedAt, deleted);
        this.name = name;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuthRole authRole = (AuthRole) o;
        return getId() != null && Objects.equals(getId(), authRole.getId());
    }
}
