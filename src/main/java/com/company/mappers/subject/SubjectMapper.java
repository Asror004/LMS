package com.company.mappers.subject;

import com.company.domain.basic.Subject;
import com.company.dto.subjectDTO.CreateSubjectDTO;
import com.company.dto.subjectDTO.DeleteSubjectDTO;
import com.company.dto.subjectDTO.UpdateSubjectDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    Subject fromCreateDTO(CreateSubjectDTO dto);
    List<Subject> fromCreateDTO(List<CreateSubjectDTO> dtos);
    Subject deleteSubject(DeleteSubjectDTO dto);
    Subject updateSubject(UpdateSubjectDTO dto);
}

