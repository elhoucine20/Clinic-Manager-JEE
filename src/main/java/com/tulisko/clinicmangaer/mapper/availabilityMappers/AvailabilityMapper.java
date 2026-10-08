package com.tulisko.clinicmangaer.mapper.availabilityMappers;

import com.tulisko.clinicmangaer.dto.availabilityDTOs.AvailabilityDTO;
import com.tulisko.clinicmangaer.model.Availability;
import com.tulisko.clinicmangaer.model.enums.AvailabilityStatus;

import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class AvailabilityMapper {

    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private AvailabilityMapper(){}

    public static AvailabilityDTO toDto(Availability a){
        String day = a.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRANCE);
        String dayLabel = day.substring(0,1).toUpperCase()+day.substring(1);
        return new AvailabilityDTO(a.getId(),dayLabel,a.getStartTime().format(TIME),a.getEndTime().format(TIME),a.getValidFrom().format(DATE)
                ,a.getValidTo() == null ? "___" : a.getValidTo().format(DATE),a.getStatus() == AvailabilityStatus.ACTIVE);
    }

    public static List<AvailabilityDTO> toDtoList(List<Availability> availabilities){
        return availabilities.stream().map(AvailabilityMapper::toDto).toList();
    }

}
