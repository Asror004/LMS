package com.company.domain.auth;

import com.company.domain.Auditable;
import com.company.domain.basicsOfBasics.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;

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

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_role_permissions",
            joinColumns = @JoinColumn(name = "auth_role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_permission_id", referencedColumnName = "id")
    )
    private Collection<AuthPermission> authPermissions;

    @Builder(builderMethodName = "childBuilder")
    public AuthRole(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, String name, String code, Collection<AuthPermission> authPermissions) {
        super(id, createdAt, updatedAt, deleted);
        this.name = name;
        this.code = code;
        this.authPermissions = authPermissions;
    }
}
