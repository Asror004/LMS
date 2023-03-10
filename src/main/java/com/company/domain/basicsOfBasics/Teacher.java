package com.company.domain.basicsOfBasics;

import com.company.domain.basic.Subject;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Teacher {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;
}
