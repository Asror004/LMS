package com.company.domain.basicsOfBasics;

import com.company.domain.basic.Subject;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Teacher {
    @Id
    @JoinColumn(name = "authUserId")
    @OneToOne(cascade = CascadeType.MERGE)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;
}
