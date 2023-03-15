package com.company.service;

import com.company.domain.basicsOfBasics.User;
import com.company.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository repository;
    public Page<User> getStudents(String pg) {
        Pageable pageable = Pageable.ofSize(10);

        if ( Objects.nonNull(pg) ) {
            pageable = PageRequest.of(Integer.parseInt(pg), 10);
        }

        return repository.findByDeletedFalseAndGroup_Id(2, pageable);
    }
}
