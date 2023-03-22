package com.company.dto.groupDTO;

import com.company.domain.basic.Faculty;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
//        @UniqueElements(message = "Group with this name already exists")
        Integer course,

//        @NotNull(message = "Name is required")
        @NotBlank(message = "Name is required")
        String name,

//        @NotNull(message = "Faculty is required")
        @NotNull(message = "Faculty is required")
        Integer facultyId,

//        @NotNull(message = "Teacher is required")
        @NotNull(message = "Teacher is required")
        Integer teacherId

) {

}
