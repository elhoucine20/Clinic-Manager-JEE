package com.tulisko.clinicmangaer.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/staff")
public class StaffController extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {

        HttpSession session = request.getSession();
        session.setAttribute("slm","lahcen");
        //request.changeSessionId();

        PrintWriter hellowolrd = response.getWriter();
        hellowolrd.println("hello world");
    }
}
