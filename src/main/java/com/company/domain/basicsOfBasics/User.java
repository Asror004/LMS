package com.company.domain.basicsOfBasics;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Group;
import com.company.domain.basic.Subject;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private int authUserId;
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
    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subject",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "authUserId"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id")
    )
    private List<Subject> subjects;
    @OneToOne(cascade = CascadeType.MERGE)
    private Document document;
    @Column(columnDefinition = "boolean default 'f'")
    private boolean deleted;

    public User(int authUserId) {
        this.authUserId = authUserId;
    }
}
