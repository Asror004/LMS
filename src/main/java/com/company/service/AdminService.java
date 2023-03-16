package com.company.service;

import com.company.domain.auth.AuthUser;
import com.company.domain.basic.Group;
import com.company.domain.basicsOfBasics.User;
import com.company.repository.GroupRepository;
import com.company.repository.UserRepository;
import com.company.repository.auth.AuthUserRepository;
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
    private final GroupRepository groupRepository;
    private final AuthUserRepository authUserRepository;
    public Page<User> getStudents(String pg, int groupId) {
        Pageable pageable = Pageable.ofSize(8);

        if ( Objects.nonNull(pg) ) {
            pageable = PageRequest.of(Integer.parseInt(pg), 8);
        }

        return repository.findByDeletedFalseAndGroup_Id(groupId, pageable);
    }

    public Page<Group> getGroups(String name, String pg) {
        Pageable pageable = Pageable.ofSize(6);

        if ( Objects.nonNull(pg) ) {
            pageable = PageRequest.of(Integer.parseInt(pg), 6);
        }

        return groupRepository.findByName(name, pageable);
    }

    public Page<AuthUser> getStudentsByUsername(Integer pg, String username) {
        Pageable pageable = Pageable.ofSize(5);

        if ( Objects.nonNull(pg) ) {
            pageable = PageRequest.of(pg, 5);
        }

        return authUserRepository.findByDeletedFalseAndUsernameLikeIgnoreCase(username, pageable);
    }
}
