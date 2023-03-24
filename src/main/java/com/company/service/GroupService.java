package com.company.service;
import com.company.domain.basic.Faculty;
import com.company.domain.basic.Group;
import com.company.domain.basicsOfBasics.User;
import com.company.dto.groupDTO.CreateGroupDTO;
import com.company.dto.groupDTO.DeleteGroupDTO;
import com.company.dto.groupDTO.UpdateGroupDTO;
import com.company.mappers.group.GroupMapper;
import com.company.repository.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public void delete(DeleteGroupDTO groupDTO) {
        Group group = Group.childBuilder().id(groupDTO.id()).build();
        groupRepository.delete(group);
    }

    public void update(UpdateGroupDTO groupDTO) {
//        TODO: add update logic
        Group group = Group.childBuilder()
                .id(groupDTO.id())
                .name(groupDTO.name())
                .faculty(facultyRepository.findById(groupDTO.faculty()).orElseThrow())
                .course(groupDTO.course())
                .owner(userRepository.findById(groupDTO.teacher()).orElseThrow())
                .updatedAt(LocalDateTime.now())
                .build();
        groupRepository.save(group);
    }

    public void create(CreateGroupDTO groupDTO,Integer facultyId,Integer ownerId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow();
        User teacher = userRepository.findById(ownerId).orElseThrow();
        groupRepository.save(
                Group.childBuilder()
                        .course(groupDTO.course())
                        .name(groupDTO.name())
                        .faculty(faculty)
                        .owner(teacher)
                        .build()
        );
    }
}
