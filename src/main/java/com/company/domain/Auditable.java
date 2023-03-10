package com.company.domain;

import com.company.domain.basicsOfBasics.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@MappedSuperclass
public class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "default now()")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne(cascade = CascadeType.ALL)
    private User updatedBy;
    @Column(nullable = false)
    private Boolean deleted;
}
