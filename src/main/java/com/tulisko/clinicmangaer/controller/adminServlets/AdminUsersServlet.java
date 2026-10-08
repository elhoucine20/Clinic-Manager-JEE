package com.tulisko.clinicmangaer.controller.adminServlets;

import com.tulisko.clinicmangaer.mapper.userMappers.UserMapper;
import com.tulisko.clinicmangaer.service.UserService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/admin/users")
public class AdminUsersServlet extends HttpServlet{
    private final UserService userService = new UserService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        HttpSession session = request.getSession(false);
        request.setAttribute("currentUserId",session.getAttribute("userId"));
        request.setAttribute("users", UserMapper.toDTOList(userService.findAllUsers()));
        request.getRequestDispatcher("/admin/users.jsp").forward(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        UUID currentUserId = (session == null) ? null : (UUID) session.getAttribute("userId");
        try {
            UUID targetId = parseId(request.getParameter("userId"));
            Boolean active = Boolean.parseBoolean(request.getParameter("active"));
            userService.setActive(targetId,active,currentUserId);
            response.sendRedirect(request.getContextPath()+"/admin/users?success=1");
        }catch (IllegalArgumentException e){
            request.setAttribute("error",e.getMessage());
            doGet(request,response);
        }catch (PersistenceException e){
            request.setAttribute("error","modification impossible !");
            doGet(request,response);
        }
    }
    private UUID parseId(String value){
        try {
            return UUID.fromString(value);
        }catch (IllegalArgumentException | NullPointerException e){
            throw new IllegalArgumentException("user invalide !");
        }
    }

}
