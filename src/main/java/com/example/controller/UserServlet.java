package com.example.controller;

import com.example.service.LoginImpl;
import com.example.service.LoginService;
import com.example.service.RegisterImpl;
import com.example.service.RegisterService;
import com.example.util.Definition;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//user?action=login
//user?action=register
@WebServlet(name="user", value="/user")
public class UserServlet extends HttpServlet {
        private LoginService loginService;
        private RegisterService registerService;

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if (request.getParameter("action") != null && request.getParameter("action").equals("login")) {
                request.getRequestDispatcher(Definition.VIEW_PATH + "/registerorlogin.jsp").forward(request, response);
            }

            if (request.getParameter("action") != null && request.getParameter("action").equals("register")) {
                request.getRequestDispatcher(Definition.VIEW_PATH + "/registerorlogin.jsp").forward(request, response);
            }
        }


        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            loginService = new LoginImpl(request.getSession());
            registerService = new RegisterImpl(request.getSession());
            if (request.getParameter("action") != null && request.getParameter("action").equals("login") && !request.getParameter("login").equals("") && !request.getParameter("password").equals("")) {
                if (loginService.login(request.getParameter("registerorlogin"), request.getParameter("password"))) {
                    response.sendRedirect(Definition.BASE_URL);
                } else {
                    request.setAttribute("messageError", "login error");
                    request.getRequestDispatcher(Definition.VIEW_PATH + "/registerorlogin.jsp").forward(request, response);
                }

            }
            if (request.getParameter("action") != null && request.getParameter("action").equals("register") && !request.getParameter("register").equals("") && !request.getParameter("password").equals("")) {
                if (registerService.register(request.getParameter("registerorlogin"), request.getParameter("password"))) {
                    response.sendRedirect(Definition.BASE_URL);
                } else {
                    request.setAttribute("messageError", "login error");
                    request.getRequestDispatcher(Definition.VIEW_PATH + "/registerorlogin.jsp").forward(request, response);
                }
            }
        }
    }
