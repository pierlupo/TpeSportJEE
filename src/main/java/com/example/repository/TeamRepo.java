package com.example.repository;

import com.example.entity.Team;
import org.hibernate.Session;
import java.util.List;

public class TeamRepo extends Repository<Team>{
    public TeamRepo(Session session) {
        super(session);
    }

    @Override
    Team findById(int id) {
        return (Team) _session.get(Team.class, id);
    }

    @Override
    List<Team> findAll() {
        return _session.createQuery("from Team ").list();
    }
}
