package com.tulisko.clinicmangaer.dto.absenceDTOs;

import java.util.UUID;

public class AbsenceDTO {

    private final UUID id;
    private final String startDate;
    private final String endDate;
    private final String reason;
    private final String status;


    public AbsenceDTO(UUID id, String startDate, String endDate, String reason, String status) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }
}
