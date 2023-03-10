package com.company.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Subject extends Auditable{
    @Column(nullable = false)
    private String name;

    @Builder(builderMethodName = "childBuilder")
    public Subject(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, User updatedBy, Boolean deleted, String name) {
        super(id, createdAt, updatedAt, updatedBy, deleted);
        this.name = name;
    }
}
