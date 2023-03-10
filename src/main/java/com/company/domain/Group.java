package com.company.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Group extends Auditable{
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
