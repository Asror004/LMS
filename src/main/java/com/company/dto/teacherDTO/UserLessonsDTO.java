package com.company.dto.teacherDTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLessonsDTO {
    private String roomName;
    private String subjectName;
    private Integer dayOfWeek;
    private Integer para;
    private String teacher;
}
