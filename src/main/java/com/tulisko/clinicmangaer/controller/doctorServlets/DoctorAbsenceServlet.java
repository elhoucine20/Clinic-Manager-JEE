package com.tulisko.clinicmangaer.controller.doctorServlets;

import com.tulisko.clinicmangaer.mapper.absenceMappers.AbsenceMapper;
import com.tulisko.clinicmangaer.service.AbsenceService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@WebServlet("/doctor/absences")
public class DoctorAbsenceServlet extends HttpServlet {

    private final AbsenceService absenceService = new AbsenceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        UUID doctorId = currentDoctor(request);
        request.setAttribute("absences", AbsenceMapper.toDtiList(absenceService.findByDoctor(doctorId)));
        request.getRequestDispatcher("/doctor/absences.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        UUID doctorId = currentDoctor(request);

        try {
            switch (param(request, "action")) {
                case "add" -> absenceService.create(
                        doctorId,
                        parseDate(param(request, "startDate")),
                        parseDate(param(request, "endDate")),
                        param(request, "reason"));
                case "delete" -> absenceService.delete(doctorId, parseId(param(request, "id")));
                default -> throw new IllegalArgumentException("Action invalide.");
            }
            response.sendRedirect(request.getContextPath() + "/doctor/absences?success=1");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
        } catch (PersistenceException e) {
            request.setAttribute("error", "Enregistrement impossible !!");
            doGet(request, response);
        }
    }

    private String param(HttpServletRequest request,String name){
        String value = request.getParameter(name);
        return value == null ? "" : value.trim();
    }

    private UUID currentDoctor(HttpServletRequest request){
        return (UUID) request.getSession(false).getAttribute("userId");
    }

    private LocalDate parseDate(String date){
        try {
            return LocalDate.parse(date);
        }catch (DateTimeParseException e){
            throw new IllegalArgumentException("Date invalide !!");
        }
    }

    private UUID parseId(String value){
        try {
            return UUID.fromString(value);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Absence intouvable !!");
        }
    }
}
