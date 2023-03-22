package com.company.dto.teacherDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyLessonsDetail {
    private String dayofweek;
    private Integer para;
    private String id;
    private String group_id;
    private String room_id;
    private String subject_id;
    private String subject_name;
    private String room_name;
    private String group_name;
    private String course;
    private boolean completed;
}
