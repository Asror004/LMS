package com.company.domain.auth;

import com.company.domain.Auditable;
import com.company.domain.basicsOfBasics.Language;
import com.company.domain.basicsOfBasics.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class AuthUser extends Auditable {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Language language;
    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_user_roles",
            joinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_role_id", referencedColumnName = "id")
    )
    private Collection<AuthRole> authRoles;
    @Builder.Default
    private Status status = Status.INACTIVE;

    public enum Status {
        BLOCKED,
        INACTIVE,
        ACTIVE
    }

    @Builder(builderMethodName = "childBuilder")
    public AuthUser(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, User updatedBy, Boolean deleted, String username, String password, Language language, Collection<AuthRole> authRoles, Status status) {
        super(id, createdAt, updatedAt, updatedBy, deleted);
        this.username = username;
        this.password = password;
        this.language = language;
        this.authRoles = authRoles;
        this.status = status;
    }
}
