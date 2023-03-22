package com.company.dto.groupDTO;

import com.company.domain.basic.Faculty;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.MessageSource;

@Table(
        name = "groups",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "course", "faculty_id"})
        }
)
public record CreateGroupDTO(
        @NotNull(message = "Course is required")
        Integer course,

        @NotBlank(message = "Name is required")
        String name,

        @Size(min = 1, max = 8, message = "Faculty is required")
        Integer facultyId,

        @NotNull(message = "Teacher is required")
        Integer teacherId

) {

}
