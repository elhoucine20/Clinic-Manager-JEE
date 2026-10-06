package com.tulisko.clinicmangaer.controller;

import com.tulisko.clinicmangaer.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.tulisko.clinicmangaer.mapper.doctorMappers.DoctorMapper;

import java.io.IOException;

@WebServlet("/admin/doctors")
public class AdminDoctorsServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("doctors", DoctorMapper.toDTOList(userService.findAllDoctors()));
        request.getRequestDispatcher("/admin/doctors.jsp").forward(request, response);
    }
}