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
    private Integer user_id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;
}
