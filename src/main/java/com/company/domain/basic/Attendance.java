package com.company.domain.basic;

import com.company.domain.Auditable;
import com.company.domain.basicsOfBasics.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Attendance extends Auditable {
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Lesson lesson;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false, columnDefinition = "default true")
    private Boolean attended;

    @Builder(builderMethodName = "childBuilder")
    public Attendance(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, User updatedBy, Boolean deleted, User user, Lesson lesson, LocalDate date, Boolean attended) {
        super(id, createdAt, updatedAt, updatedBy, deleted);
        this.user = user;
        this.lesson = lesson;
        this.date = date;
        this.attended = attended;
    }
}
