package com.example.service;

import jakarta.servlet.http.HttpSession;

public class LoginImpl implements LoginService{

    private HttpSession _session;

    public LoginImpl(HttpSession httpSession) {
        _session = httpSession;
    }
    @Override
    public boolean isLogged() {
        return _session.getAttribute("isLogged") != null && (boolean) _session.getAttribute("isLogged");
    }

    @Override
    public boolean login(String user, String password) {
        //je peux utiliser un service Hibernate pour faire la vérification côté base de données.
        if(user.equals("toto") && password.equals("test")) {
            _session.setAttribute("isLogged", true);
            return true;
        }
        return false;
    }

}
