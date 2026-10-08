package com.tulisko.clinicmangaer.controller.doctorServlets;

import com.tulisko.clinicmangaer.mapper.availabilityMappers.AvailabilityMapper;
import com.tulisko.clinicmangaer.service.AvailabilityService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@WebServlet("/doctor/availabilities")
public class DoctorAvailabilityServlet extends HttpServlet {
    private final AvailabilityService availabilityService = new AvailabilityService();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("DO GET EXECUTED");

        UUID doctorId = currentDoctorId(request);
        request.setAttribute("availabilities", AvailabilityMapper.toDtoList(availabilityService.findByDoctor(doctorId)));
        request.getRequestDispatcher("/doctor/availabilities.jsp").forward(request, response);
        request.getSession(false).removeAttribute("success");
        request.getSession(false).removeAttribute("error");
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        UUID doctorId = currentDoctorId(request);
        try {
            switch (param(request,"action")){
                case "add"->{
                    String validTo = param(request,"validTo");
                    availabilityService.create(doctorId,parseDay(param(request,"day"))
                                                       ,parseTime(param(request,"startTime"))
                                                       ,parseTime(param(request,"endTime"))
                                                       ,parseDate(param(request,"validFrom")),validTo.isBlank() ? null
                                                       :parseDate(validTo));
                }
                case "delete"-> availabilityService.delete(doctorId,parseId(param(request,"id")));
                default -> throw new IllegalArgumentException("action invalid !!");
            }
            request.setAttribute("success","crate avec success");
            request.getSession(false).setAttribute("success",1);
            response.sendRedirect(request.getContextPath()+"/doctor/availabilities");
        }catch (IllegalArgumentException e){
            request.getSession(false).setAttribute("error",e.getMessage());
            //request.setAttribute("error",e.getMessage());
            doGet(request,response);
        }catch (PersistenceException e){
            request.getSession(false).setAttribute("error","enregistrement im possible !!");
            //request.setAttribute("error","enregistrement im possible !!");
            doGet(request,response);
        }
    }

    private UUID currentDoctorId(HttpServletRequest request) {
        return (UUID) request.getSession(false).getAttribute("userId");
    }

    private String param(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value == null ? "" : value.trim();
    }

    private DayOfWeek parseDay(String value) {
        try {
            return DayOfWeek.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("jour invalid");
        }
    }

    private LocalTime parseTime(String value) {
        try {
            return LocalTime.parse(value);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Heure invalide.");
        }

    }

    private LocalDate parseDate(String value) {
        try {
            return LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date invalide.");
        }
    }

    private UUID parseId(String value) {
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Horaire introuvable.");
        }
    }

}
