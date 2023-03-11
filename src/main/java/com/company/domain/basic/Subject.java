package com.company.domain.basic;

import com.company.domain.Auditable;
import com.company.domain.basicsOfBasics.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Subject extends Auditable {
    @Column(nullable = false)
    private String name;

    @Builder(builderMethodName = "childBuilder")
    public Subject(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean deleted, String name) {
        super(id, createdAt, updatedAt, deleted);
        this.name = name;
    }
}
