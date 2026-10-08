package com.tulisko.clinicmangaer.dto.availabilityDTOs;

import java.util.UUID;

public class AvailabilityDTO {

    private final UUID id;
    private final String dayLabel;
    private final String startTime;
    private final String endTime;
    private final String validFrom;
    private final String validTo;
    private final boolean active;

    public AvailabilityDTO(UUID id, String dayLabel, String startTime, String endTime, String validFrom, String validTo, boolean active) {
        this.id = id;
        this.dayLabel = dayLabel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }
    public String getDayLabel() {
        return dayLabel;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public String getValidFrom() {
        return validFrom;
    }
    public String getValidTo() {
        return validTo;
    }
    public boolean isActive() {
        return active;
    }

}
