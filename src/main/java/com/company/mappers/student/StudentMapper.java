package com.company.mappers.faculty;

import com.company.domain.basic.Faculty;
import com.company.dto.facultyDTO.DeleteFacultyDTO;
import com.company.dto.facultyDTO.UpdateFacultyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Faculty deleteFaculty(DeleteFacultyDTO dto);
    Faculty updateFaculty(UpdateFacultyDTO dto);
}

