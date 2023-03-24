package com.company.dto.lessonDTO;

public record LessonCreateDTO(
        String faculty,
        String group,
        Integer day,
        Byte para,
        String teacher,
        String room
) {
}
