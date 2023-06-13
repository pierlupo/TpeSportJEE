package com.example.service;

import com.example.entity.Player;
import com.example.entity.Team;
import com.example.repository.PlayerRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PlayerService {

    private SessionFactory _sessionFactory;
    private PlayerRepo playerRepo;
    private Session session;

    public PlayerService (SessionFactory sessionFactory){
        _sessionFactory = sessionFactory;
    }

    public boolean createPlayer (String username, String favgame){
        boolean result = false;
        session = _sessionFactory.openSession();
        session.beginTransaction();
        playerRepo = new PlayerRepo(session);
        Player player = Player.builder().username(username).favgame(favgame).build();
        try{
            playerRepo.create(player);
            session.getTransaction().commit();
            result = true;
        }catch (Exception e){
            try{
                session.getTransaction().rollback();
            }catch (Exception except){
                System.out.println(except.getMessage());
            }
        }finally {
            session.close();
        }
        return result;
    }

    public Player getByIdPlayer (int id){
        Player player = null ;
        session = _sessionFactory.openSession();
        playerRepo = new PlayerRepo(session);
        try{
            player = playerRepo.findById(id);
        }catch (Exception e){

        }finally {
            session.close();
        }
        return player;
    }

    public List<Player> getPlayers(String name){
        List<Player> players = null;
        session = _sessionFactory.openSession();
        playerRepo = new PlayerRepo(session);
        try{
            if(name == null){
                players = playerRepo.findAll();
            }else{
                players = playerRepo.findAllByName(name);
            }
        }catch (Exception e){

        }finally {
            session.close();
        }
        return players;
    }

}
