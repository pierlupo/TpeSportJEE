package com.example.controller;

import com.example.entity.Player;
import com.example.entity.Team;
import com.example.service.LoginImpl;
import com.example.service.LoginService;
import com.example.service.PlayerService;
import com.example.util.Definition;
import com.example.util.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.example.util.Definition.VIEW_PATH;

@WebServlet(name = "player", value = "")
public class PlayerServlet extends HttpServlet {
    private PlayerService playerService;
    private List<Player> players;
    private LoginService loginService;

    public void init() {playerService = new PlayerService(HibernateSession.getSessionFactory());}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String messageError = null;
        if(request.getParameter("action")!= null && request.getParameter("action").equals("search")) {
            players = playerService.getPlayers(request.getParameter("search"));
        }
        else {
            players = playerService.getPlayers(null);
            if(request.getParameter("username") != null
                    && request.getParameter("username") != null
                    && request.getParameter("favgame") != null
                    && !request.getParameter("username").equals("")
                    && !request.getParameter("favgame").equals("")){
                    String username = request.getParameter("username");
                    String favgame = request.getParameter("favgame");
                if (playerService.createPlayer(username, favgame)) {
                    response.sendRedirect("/home.jsp");
                } else {
                    messageError = "Error while adding a player";
                }
            } else {
                messageError = "fill up all fields please";
            }
        }

        if(messageError != null || request.getParameter("action").equals("search")) {
            request.setAttribute("playerList", players);
            request.setAttribute("messageError", messageError);
            request.getRequestDispatcher(VIEW_PATH + "/home.jsp").forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginService = new LoginImpl(request.getSession());
        request.setAttribute("isLogged", loginService.isLogged());
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            Player player = playerService.getByIdPlayer(id);
            request.setAttribute("player", player);
            request.getRequestDispatcher(VIEW_PATH+"playerDetail.jsp").forward(request, response);
        } else {
            players = playerService.getPlayers(null);
            request.setAttribute("playerList", players);
            request.getRequestDispatcher(VIEW_PATH + "/home.jsp").forward(request, response);
        }
    }
}
