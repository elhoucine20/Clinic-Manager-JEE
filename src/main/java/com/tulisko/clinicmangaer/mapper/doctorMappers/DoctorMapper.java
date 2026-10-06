package com.tulisko.clinicmangaer.mapper.doctorMappers;

import com.tulisko.clinicmangaer.dto.doctorDTOs.DoctorDTO;
import com.tulisko.clinicmangaer.model.Doctor;
import com.tulisko.clinicmangaer.model.Specialty;

import java.util.List;

public class DoctorMapper {

    private DoctorMapper() {
    }

    public static DoctorDTO toDTO(Doctor doctor) {
        Specialty specialty = doctor.getSpecialty();
        String specialtyName = specialty == null ? "—" : specialty.getName();
        String departmentName = specialty == null ? "—" : specialty.getDepartment().getName();

        return new DoctorDTO(doctor.getId(), doctor.getFullName(), doctor.getEmail(), doctor.getTelephone(),
                doctor.getMatricule(), doctor.getTitre(), specialtyName, departmentName, doctor.isActive());}

    public static List<DoctorDTO> toDTOList(List<Doctor> doctors) {
        return doctors.stream().map(DoctorMapper::toDTO).toList();
    }
}