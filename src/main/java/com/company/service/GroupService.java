package com.company.service;
import com.company.domain.basic.Group;
import com.company.dto.groupDTO.CreateGroupDTO;
import com.company.dto.groupDTO.DeleteGroupDTO;
import com.company.dto.groupDTO.UpdateGroupDTO;
import com.company.mappers.group.GroupMapper;
import com.company.repository.GroupRepository;
import com.company.repository.LanguageRepository;
import com.company.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupMapper groupMapper;

    private final GroupRepository groupRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public void delete(DeleteGroupDTO groupDTO) {
        Group group = groupMapper.deleteGroup(groupDTO);
        group.setUpdatedAt(LocalDateTime.now());
        groupRepository.delete(group);
    }

    public void update(UpdateGroupDTO groupDTO) {
//        TODO: add update logic
        Group group = groupMapper.updateGroup(groupDTO);
        group.setUpdatedAt(LocalDateTime.now());
        groupRepository.save(group);
    }

    public void create(CreateGroupDTO groupDTO) {
        Group group = groupMapper.fromCreateDTO(groupDTO);
        groupRepository.save(group);
    }
}
