/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbService;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.*;

/**
 *
 * @author Jordan
 */
public class JPAService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA1PU");
    EntityManager em = emf.createEntityManager();

    public int findGameID(String name) {
        int gameID = 0;
        Query query = em.createQuery("SELECT g.game_Id FROM Game g " + "WHERE g.name=:value", Game.class).setParameter("value", name);
        try {
            gameID = (int) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nothing found " + e.getMessage());
        }
        return gameID;
    }

    public boolean findGame(String name) {
        int id = findGameID(name);
        boolean found = false;
        Game g = em.find(Game.class, id);
        if (g != null) {
            found = true;
        }
        return found;
    }

    public void printAllGames() {
        Query q = em.createNativeQuery("SELECT g.game_Id, g.name, g.age_Rating," + "g.publisher,"
                + "g.platform, g.genre_Id FROM Game g ORDER BY g.genre_Id", Game.class);
        List<Game> results = q.getResultList();

        for (int i = 0; i < results.size(); i++) {
            System.out.println("" + results.get(i).getGen() + "" + results.get(i));
        }
    }

    public void printAllGamesByGenre() {
        Query q = em.createNativeQuery("SELECT g.game_Id, g.name, g.age_Rating," + "g.publisher,"
                + "g.platform, g.genre_Id FROM Game g ORDER BY g.genre_Id", Game.class);
        List<Game> results = q.getResultList();
        int index;

        for (int i = 0; i < results.size() + 10; i++) {
            index = i;
            if (results.get(i).getGen().equals(results.get(index + 1).getGen())) {
                i++;
            }
            System.out.println(results.get(i).getGen());
        }
    }

    public void updateGame(int id, String newName) {
        em.getTransaction().begin();
        Game g = em.find(Game.class, id);
        g.setName(newName);
        em.getTransaction().commit();
    }

    public void removeGame(int id) {
        Game g = em.find(Game.class, id);
        em.getTransaction().begin();
        em.remove(g);
        em.getTransaction().commit();
    }

    public void removeGenre(int id) {
        Genre g = em.find(Genre.class, id);
        em.getTransaction().begin();
        em.remove(g);
        em.getTransaction().commit();
    }

    public void updateAge(int id, String newAge) {
        em.getTransaction().begin();
        Game g = em.find(Game.class, id);
        g.setAge_Rating(newAge);
        em.getTransaction().commit();
    }

    public void updatePublisher(int id, String newPub) {
        em.getTransaction().begin();
        Game g = em.find(Game.class, id);
        g.setPublisher(newPub);
        em.getTransaction().commit();
    }

    public void printAllGenres() {
        Query q = em.createNativeQuery("SELECT g.game_Id, g.name, g.age_Rating," + "g.publisher,"
                + "g.platform, g.genre_Id FROM Game g ORDER BY genre_Id", Game.class);
        List<Game> results = q.getResultList();

        for (Game g : results) {
            System.out.println(g);
        }
    }

    public int findGenreID(String name) {
        int genreID = 0;
        Query query = em.createQuery("SELECT g.genre_Id FROM Genre g " + "WHERE g.genre_Name=:value", Genre.class).setParameter("value", name);
        try {
            genreID = (int) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nothing found " + e.getMessage());
        }
        return genreID;
    }

    public boolean findGenre(String name) {
        int id = findGenreID(name);
        boolean found = false;
        Genre g = em.find(Genre.class, id);
        if (g != null) {
            found = true;
        }
        return found;
    }

    public Game createGame(String genre, Game g) {
        int id = findGenreID(genre);
        Genre gen = em.find(Genre.class, id);
        em.getTransaction().begin();
        gen.addGame(g);
        em.getTransaction().commit();
        return g;
    }

    public void printGenre(String name) {
        Query q = em.createNativeQuery("SELECT g.game_Id, g.name, g.age_Rating, g.publisher,"
                + " g.platform, g.genre_Id FROM Game g WHERE g.genre_Id = " + findGenreID(name), Game.class);
        List<Game> results = q.getResultList();

        System.out.println("-----------------------------------");
        System.out.println(name);
        System.out.println("-----------------------------------");
        for (Game g : results) {
            System.out.println(g);
        }
    }
}
