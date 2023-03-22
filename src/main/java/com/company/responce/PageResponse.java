package com.company.responce;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageResponse<T> {
    private List<T> content;
    private boolean prev;
    private boolean next;
    private short current;
    private short pages;
}
