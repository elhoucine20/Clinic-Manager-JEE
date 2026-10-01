package com.tulisko.clinicmangaer.controller;

import java.io.*;

import com.tulisko.clinicmangaer.exception.InvalidCredentialsException;
import com.tulisko.clinicmangaer.model.User;
import com.tulisko.clinicmangaer.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class AuthServlet extends HttpServlet {
    private String message;

    private UserService userService = new UserService();
    public void init() {
        message = "Votre inscrire ici !";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("/login".equals(request.getServletPath())){
            HttpSession session = request.getSession(false);
            if (session != null){
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath()+"/login");
            return;
        }
        request.getRequestDispatcher("/auth/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userService.login(email,password);
            HttpSession oldSesion = request.getSession(false);
            if (oldSesion != null){
                oldSesion.invalidate();
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getId());
            session.setAttribute("fullName", user.getFullName());
            session.setAttribute("role", user.getRole());

            String base = request.getContextPath();

            switch (user.getRole()){
                case ADMIN -> response.sendRedirect(base + "/admin/dashboard.jsp");
                case DOCTOR -> response.sendRedirect(base + "/doctor/dashboard.jsp");
                case PATIENT -> response.sendRedirect(base + "/patient/dashboard.jsp");
                case STAFF -> response.sendRedirect(base + "/staff/dashboard.jsp");
            }
        }catch (InvalidCredentialsException e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/auth/login.jsp").forward(request,response);
        }
    }
}
