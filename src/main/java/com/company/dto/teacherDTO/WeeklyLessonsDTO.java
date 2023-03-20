package com.company.dto.teacherDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyLessonsDTO {
    private LocalDate monday;
    private int week;
    private List<DailyLessonsDetail> lessons;
}
