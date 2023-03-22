package com.company.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public String plusDays(LocalDate date, int count) {
        return date.plusDays(count).toString();
    }
    public LocalDate plusDays2(LocalDate date, int count) {
        return date.plusDays(count);
    }
}
