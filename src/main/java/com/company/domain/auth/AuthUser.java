package com.company.domain.auth;

import com.company.domain.Auditable;
import com.company.domain.basicsOfBasics.Language;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collection;


@Getter
@Setter
@Entity
@ToString(callSuper = true)
public class AuthUser extends Auditable {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Language language;
    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_user_roles",
            joinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_role_id", referencedColumnName = "id")
    )
    private Collection<AuthRole> authRoles;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_user_permissions",
            joinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_permission_id", referencedColumnName = "id")
    )
    private Collection<AuthPermission> authPermissions;
    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public enum Status {
        BLOCKED,
        INACTIVE,
        ACTIVE
    }

    public AuthUser() {
    }

    @Builder(builderMethodName = "childBuilder")
    public AuthUser(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, String username, String password, Language language, Collection<AuthRole> authRoles, Collection<AuthPermission> authPermissions, Status status) {
        super(id, createdAt, updatedAt, deleted);
        this.username = username;
        this.password = password;
        this.language = language;
        this.authRoles = authRoles;
        this.authPermissions = authPermissions;
        this.status = status;
    }
}
