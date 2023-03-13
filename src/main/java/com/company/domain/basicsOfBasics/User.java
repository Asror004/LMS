package com.company.domain.basicsOfBasics;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Group;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private Integer authUserId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String middleName;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Group group;
    @ManyToOne(cascade = CascadeType.MERGE)
    private AuthUser createdBy;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Address address;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(nullable = false)
    private String passport;
    @Column(nullable = false)
    private String gender;
    @OneToOne(cascade = CascadeType.MERGE)
    private Document document;
}
