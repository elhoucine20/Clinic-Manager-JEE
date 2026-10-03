package com.tulisko.clinicmangaer.controller;

import com.tulisko.clinicmangaer.exception.DuplicateEmailException;
import com.tulisko.clinicmangaer.service.UserService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/users")
public class AdminUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException {
        res.sendRedirect(req.getContextPath()+"/admin/dashboard.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");

        String role = request.getParameter( "role");
        String fullname = request.getParameter( "fullname");
        String email = request.getParameter( "email");
        String telephone = request.getParameter( "telephone");
        String password = request.getParameter( "password");
        String matricule = request.getParameter( "matricule");
        String titre = request.getParameter( "titre");

        try {
            if (fullname.isBlank() || email.isBlank() || telephone.isBlank()){
                throw new IllegalArgumentException("tous les champ est oblegatoires");
            }
            switch (role){
                case "DOCTOR"->{
                    if (matricule.isBlank() || titre.isBlank()){throw new IllegalArgumentException("le matricule et le titre est oblegatoires");}
                    userService.createDoctor(fullname,email,telephone,password,matricule,titre);
                }
                case "STAFF"->userService.createStaff(fullname,email,telephone,password);
                default -> throw new IllegalArgumentException("role invalid");
            }
            response.sendRedirect(request.getContextPath()+"/admin/dashboard.jsp?success=1");

        }catch (DuplicateEmailException e){
            showError(request,response,"cet email est deja utiliser");
        }catch (IllegalArgumentException e){
            showError(request,response,e.getMessage());
        }catch (PersistenceException e){
            showError(request,response,"enregistrement impossible : le matricule peut-etre exist deja ");
        }
    }

    private void showError(HttpServletRequest request,HttpServletResponse response,String message)throws ServletException , IOException{
        request.setAttribute("error",message);
        request.getRequestDispatcher("/admin/dashboard.jsp").forward(request,response);
    }
}
