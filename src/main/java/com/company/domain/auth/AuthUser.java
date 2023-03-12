package com.company.domain.auth;

import com.company.domain.Auditable;
import com.company.domain.basicsOfBasics.Language;
import com.company.domain.basicsOfBasics.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    public AuthUser(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean deleted, String username, String password, Language language, Collection<AuthRole> authRoles, Status status) {
        super(id, createdAt, updatedAt, deleted);
        this.username = username;
        this.password = password;
        this.language = language;
        this.authRoles = authRoles;
        this.status = status;
    }
}
