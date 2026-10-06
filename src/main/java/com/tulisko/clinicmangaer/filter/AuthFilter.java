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
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        RoleUser role = (session == null) ? null : (RoleUser) session.getAttribute("role");
        if (role == null){
            response.sendRedirect(request.getContextPath()+"/login");
            return;
        }
        String path = request.getServletPath() ;
        String expected = "/"+role.name().toLowerCase()+"/";

        if (path.startsWith(expected)) {
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate"); // c'est le principale
              //  no-store-> sans copier page, no-cache-> if une copier chaque fois verifier filter , must-revalidate->if copier vieux doit verifier avant sorite de copier
            response.setHeader("Pragma", "no-cache");  // la ,meme chose avec une autre manier (langage vieux)
            response.setDateHeader("Expires", 0);  // la page est exprimer donc doit recuperer une nouveux copier
            chain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}
