package com.example.service;

import jakarta.servlet.http.HttpSession;

public class RegisterImpl implements RegisterService{

    private HttpSession _session;

    public RegisterImpl(HttpSession httpSession) {
        _session = httpSession;
    }

    @Override
    public boolean isRegistered() {
        return _session.getAttribute("isRegistered") != null && (boolean) _session.getAttribute("isRegistered");
    }

    @Override
    public boolean register(String user, String password) {
            //je peux utiliser un service Hibernate pour faire la vérification côté base de données.
            if(user.equals("toto") && password.equals("test")) {
                _session.setAttribute("isRegistered", true);
                return true;
            }
            return false;
        }
    }
