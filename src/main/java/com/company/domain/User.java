package com.company.domain;

import com.company.domain.auth.AuthUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class User {
    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AuthUser authUser;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String middleName;
    @ManyToOne(cascade = CascadeType.ALL)
    private Group group;
    @ManyToOne(cascade = CascadeType.ALL)
    private User createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    @Column(nullable = false)
    private LocalDateTime birthDate;
    @Column(nullable = false)
    private String passport;
    private Boolean gender;
    @OneToOne(cascade = CascadeType.ALL)
    private Document document;
}
