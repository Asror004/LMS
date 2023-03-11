package com.company.domain.basic;

import com.company.domain.Auditable;
import com.company.domain.basicsOfBasics.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "groups")
public class Group extends Auditable {
    @Column(nullable = false)
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private Faculty faculty;
    @Column(nullable = false, columnDefinition = "smallint default 1")
    private Byte course;
    @ManyToOne(cascade = CascadeType.ALL)
    private User owner;

    @Builder(builderMethodName = "childBuilder")
    public Group(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, User updatedBy, Boolean deleted, String name, Faculty faculty, Byte course, User owner) {
        super(id, createdAt, updatedAt, updatedBy, deleted);
        this.name = name;
        this.faculty = faculty;
        this.course = course;
        this.owner = owner;
    }
}
