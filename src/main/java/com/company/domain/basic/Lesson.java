package com.company.domain.basic;

import com.company.domain.Auditable;
import com.company.domain.basicsOfBasics.Teacher;
import com.company.domain.basicsOfBasics.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Lesson extends Auditable {
    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
    @ManyToOne(cascade = CascadeType.ALL)
    private Group group;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;
    @Column(nullable = false, columnDefinition = "smallint")
    private Byte para;
    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;

    @Builder(builderMethodName = "childBuilder")

    public Lesson(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, User updatedBy, Boolean deleted, Subject subject, Teacher teacher, Group group, DayOfWeek dayOfWeek, Byte para, Room room) {
        super(id, createdAt, updatedAt, updatedBy, deleted);
        this.subject = subject;
        this.teacher = teacher;
        this.group = group;
        this.dayOfWeek = dayOfWeek;
        this.para = para;
        this.room = room;
    }
}
