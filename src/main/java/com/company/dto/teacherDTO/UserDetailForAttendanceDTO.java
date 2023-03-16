package com.company.dto.teacherDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDetailForAttendanceDTO {
    private Integer id;
    private String firstName;
    private String lastName;
}
