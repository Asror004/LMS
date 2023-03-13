package com.company.mappers.faculty;

import com.company.domain.basic.Faculty;
import com.company.dto.facultyDTO.CreateFacultyDTO;
import com.company.dto.facultyDTO.DeleteFacultyDTO;
import com.company.dto.facultyDTO.UpdateFacultyDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacultyMapper {
    Faculty fromCreateDTO(CreateFacultyDTO dto);
    List<Faculty> fromCreateDTO(List<CreateFacultyDTO> dtos);
    Faculty deleteFaculty(DeleteFacultyDTO dto);
    Faculty updateFaculty(UpdateFacultyDTO dto);
}

