package com.company.domain.basic;

import com.company.domain.Auditable;
import com.company.domain.basicsOfBasics.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group extends Auditable {
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Faculty faculty;
    @Column(nullable = false, columnDefinition = "smallint default 1")
    private Byte course;
    @ManyToOne
    private User owner;

    @Builder(builderMethodName = "childBuilder")
    public Group(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, String name, Faculty faculty, Byte course, User owner) {
        super(id, createdAt, updatedAt, deleted);
        this.name = name;
        this.faculty = faculty;
        this.course = course;
        this.owner = owner;
    }
}
