package com.company.service;

import com.company.domain.basic.Subject;
import com.company.dto.subjectDTO.CreateSubjectDTO;
import com.company.dto.subjectDTO.DeleteSubjectDTO;
import com.company.dto.subjectDTO.UpdateSubjectDTO;
import com.company.mappers.subject.SubjectMapper;
import com.company.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectMapper subjectMapper;
    private final SubjectRepository subjectRepository;

    public void create(CreateSubjectDTO subject) {
        Subject subject1 = subjectMapper.fromCreateDTO(subject);
        subjectRepository.save(subject1);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public void update(UpdateSubjectDTO subject) {
        Subject subject1 = subjectMapper.updateSubject(subject);
        subject1.setUpdatedAt(LocalDateTime.now());
        subjectRepository.update(subject1.getId(),subject1.getName(),subject1.getUpdatedAt());
    }

    public void delete(DeleteSubjectDTO subject) {
        Subject subject1 = subjectMapper.deleteSubject(subject);
        subject1.setUpdatedAt(LocalDateTime.now());
        subjectRepository.delete(subject1.getId(),subject1.getUpdatedAt());
    }
}
