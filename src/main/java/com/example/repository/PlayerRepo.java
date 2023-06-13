package com.example.repository;

import com.example.entity.Player;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class PlayerRepo extends Repository<Player>{

    public PlayerRepo(Session session) {
        super(session);
    }

    @Override
    public Player findById(int id) {
        return (Player) _session.get(Player.class, id);
    }

    @Override
    public List<Player> findAll() {
        return _session.createQuery("from Player ").list();
    }

    public List<Player> findAllByName(String search) {
        Query<Player> query = _session.createQuery("from Player where username like :search");
        query.setParameter("search", search + "%");
        return query.list();
    }
}
