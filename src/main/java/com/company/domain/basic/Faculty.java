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
@Entity
public class Faculty extends Auditable {
    @Column(nullable = false)
    private String name;

    @Builder(builderMethodName = "childBuilder")
    public Faculty(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, String name) {
        super(id, createdAt, updatedAt, deleted);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                "getUpdatedAt()='" + getUpdatedAt() + '\'' +
                "isDeleted()='" + isDeleted() + '\'' +
                "getCreatedAt()='" + getCreatedAt() + '\'' +
                '}';
    }
}
