package com.tulisko.clinicmangaer.controller;

import com.tulisko.clinicmangaer.mapper.userMappers.UserMapper;
import com.tulisko.clinicmangaer.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/staff")
public class AdminStaffServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("staffList", UserMapper.toDTOList(userService.findAllStaff()));
        request.getRequestDispatcher("/admin/staff.jsp").forward(request, response);
    }
}