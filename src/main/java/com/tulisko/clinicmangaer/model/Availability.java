package com.tulisko.clinicmangaer.model;

import com.tulisko.clinicmangaer.model.enums.AvailabilityStatus;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "availabilities")
public class Availability {

    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id",nullable = false)
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week",nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time",nullable = false)
    private LocalTime startTime;
    @Column(name = "end_time",nullable = false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityStatus status;

    @Column(name = "valid_from",nullable = false)
    private LocalDate validFrom;

    @Column(name = "valid_to")
    private LocalDate validTo;


    protected Availability(){}


    public Availability(Doctor doctor, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, LocalDate validFrom, LocalDate validTo) {
        this.id = UUID.randomUUID();
        this.doctor = doctor;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = AvailabilityStatus.ACTIVE;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public UUID getId() {
        return id;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public AvailabilityStatus getStatus() {
        return status;
    }
    public LocalDate getValidFrom() {
        return validFrom;
    }
    public LocalDate getValidTo() {
        return validTo;
    }

    public boolean appliesTo(LocalDate date) {
        return status == AvailabilityStatus.ACTIVE
                && date.getDayOfWeek() == dayOfWeek
                && !date.isBefore(validFrom)
                && (validTo == null || !date.isAfter(validTo));
    }
    public void setStatus(AvailabilityStatus status) {
        this.status = status;
    }

}
