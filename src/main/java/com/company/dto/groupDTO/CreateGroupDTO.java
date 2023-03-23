package com.company.dto.groupDTO;

import com.company.domain.basic.Faculty;
import com.company.domain.basicsOfBasics.Teacher;
import com.company.domain.basicsOfBasics.User;
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
        Byte course,

//        @NotNull(message = "Name is required")
        @Size(min = 1,max = 20,message = "Name is required")
        String name,

//        @NotNull(message = "Faculty is required")
        @NotNull(message = "Faculty is required")
        Integer faculty,

//        @NotNull(message = "Teacher is required")
        @NotNull(message = "Teacher is required")
        Integer owner

) {

}
