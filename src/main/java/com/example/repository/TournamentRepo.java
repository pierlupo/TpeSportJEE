package com.example.repository;

import com.example.entity.Tournament;
import org.hibernate.Session;

import java.util.List;

public class TournamentRepo extends Repository<Tournament>{
    public TournamentRepo(Session session) {
        super(session);
    }

    @Override
    Tournament findById(int id) {
        return (Tournament) _session.get(Tournament.class, id);
    }

    @Override
    List<Tournament> findAll() {
        return _session.createQuery("from Tournament ").list();
    }
}
