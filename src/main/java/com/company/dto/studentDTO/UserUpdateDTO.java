package com.company.dto.studentDTO;

import com.company.domain.basicsOfBasics.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserUpdateDTO {
    private Address address;
    private String username;
}
