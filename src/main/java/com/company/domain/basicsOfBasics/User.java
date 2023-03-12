package com.company.domain.basicsOfBasics;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Group;
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
@Table(name = "users")
public class User {
    @Id
    @OneToOne(cascade = CascadeType.MERGE)
    private AuthUser authUser;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String middleName;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Group group;
    @ManyToOne(cascade = CascadeType.MERGE)
    private User createdBy;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Address address;
    @Column(nullable = false)
    private LocalDateTime birthDate;
    @Column(nullable = false)
    private String passport;
    @Column(nullable = false)
    private String gender;
    @OneToOne(cascade = CascadeType.MERGE)
    private Document document;
}
