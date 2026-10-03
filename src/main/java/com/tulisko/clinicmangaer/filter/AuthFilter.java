package com.tulisko.clinicmangaer.filter;

import com.tulisko.clinicmangaer.model.enums.RoleUser;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/doctor/*", "/patient/*", "/staff/*"})
public class AuthFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        RoleUser role = (session == null) ? null : (RoleUser) session.getAttribute("role");
        if (role == null){
            response.sendRedirect(request.getContextPath()+"/login");
            return;
        }
        RoleUser required = requiredRole(request.getServletPath());
        if (required == null || role != required){
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        chain.doFilter(request,response);
    }

    private RoleUser requiredRole(String path){
        if (path.startsWith("/admin/")) return RoleUser.ADMIN;
        if (path.startsWith("/doctor/")) return RoleUser.DOCTOR;
        if (path.startsWith("/patient/")) return RoleUser.PATIENT;
        if (path.startsWith("/staff/")) return RoleUser.STAFF;
        return null;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
