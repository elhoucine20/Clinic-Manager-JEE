package com.tulisko.clinicmangaer.mapper.depertmentMappers;

import com.tulisko.clinicmangaer.dto.departmentDTOs.DepartmentDTO;
import com.tulisko.clinicmangaer.model.Department;

import java.util.List;

public class DepartmentMapper {

    private DepartmentMapper() {
    }

    public static DepartmentDTO toDTO(Department department) {
        return new DepartmentDTO(department.getId(), department.getName());
    }

    public static List<DepartmentDTO> toDTOList(List<Department> departments) {
        return departments.stream().map(DepartmentMapper::toDTO).toList();
    }
}