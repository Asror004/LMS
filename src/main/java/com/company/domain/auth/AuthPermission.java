package com.company.domain.auth;

import com.company.domain.Auditable;
import com.company.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthPermission extends Auditable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String code;

    @Builder(builderMethodName = "childBuilder")
    public AuthPermission(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, User updatedBy, Boolean deleted, String name, String code) {
        super(id, createdAt, updatedAt, updatedBy, deleted);
        this.name = name;
        this.code = code;
    }
}
