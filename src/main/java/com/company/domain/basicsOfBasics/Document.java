package com.company.domain.basicsOfBasics;

import com.company.domain.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Document extends Auditable {
    @Column(nullable = false)
    private String originalName;
    @Column(nullable = false)
    private String generatedName;
    @Column(nullable = false)
    private String extension;
    @Column(nullable = false)
    private String mimeType;
    @Column(nullable = false)
    private Long size;

    @Builder(builderMethodName = "childBuilder")
    public Document(Integer id, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean deleted, String originalName, String generatedName, String extension, String mimeType, Long size) {
        super(id, createdAt, updatedAt, deleted);
        this.originalName = originalName;
        this.generatedName = generatedName;
        this.extension = extension;
        this.mimeType = mimeType;
        this.size = size;
    }


    @PrePersist
    public void persist(){
        this.generatedName = UUID.randomUUID() + "." + this.extension;
    }
}
