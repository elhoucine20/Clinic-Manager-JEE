package com.tulisko.clinicmangaer.controller;

import java.io.*;

import com.tulisko.clinicmangaer.exception.InvalidCredentialsException;
import com.tulisko.clinicmangaer.model.User;
import com.tulisko.clinicmangaer.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class AuthServlet extends HttpServlet{
    private String message;

    private UserService userService = new UserService();
    public void init() { // qaund le servlet est initialiser execute automatique
        message = "inscrire ici !";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if("/logout".equals(request.getServletPath())){ // pour verifie laquelle des deux routes a ete demander
            HttpSession session = request.getSession(false); // recuperer session if exist if not -> false => ne cree pas
            if (session != null){
                session.invalidate();  // (fini session) remove tous les donnees que stocker en session
            }
            response.sendRedirect(request.getContextPath()+"/login"); // redirect vers login (new request)
            return;
        }
        request.getRequestDispatcher("/auth/login.jsp").forward(request,response);  // la meme request vres login
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userService.login(email,password);
            HttpSession oldSesion = request.getSession(false);
            if (oldSesion != null){   // if exist deja une session
                oldSesion.invalidate(); // remove
            }
            HttpSession session = request.getSession(); // create nouvelle sesssion
            session.setAttribute("userId", user.getId()); // remplier session
            session.setAttribute("fullName", user.getFullName());
            session.setAttribute("role", user.getRole());

            String laBase = request.getContextPath();

            switch (user.getRole()){  // rediger vers une direction selon role
                case ADMIN -> response.sendRedirect(laBase + "/admin/dashboard");
                case DOCTOR -> response.sendRedirect(laBase + "/doctor/dashboard.jsp");
                case PATIENT -> response.sendRedirect(laBase + "/patient/dashboard.jsp");
                case STAFF -> response.sendRedirect(laBase + "/staff/dashboard.jsp");
            }
        }catch (InvalidCredentialsException e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/auth/login.jsp").forward(request,response);
        }
    }
}
