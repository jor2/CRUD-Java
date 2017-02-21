/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dbService.JPAService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.*;

/**
 *
 * @author Jordan
 */
public class TestGame {

    //Creation of static variables
    static JPAService jpa;
    static EntityManagerFactory emf;
    static EntityManager em;
    static ArrayList<Genre> g;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("CA1PU");
        em = emf.createEntityManager();

        g = new ArrayList<>();

        //Adding genre's
        g.add(new Genre("Sports"));
        g.add(new Genre("Strategy"));
        g.add(new Genre("Survival"));
        g.add(new Genre("Action"));

        //Begin persistence and adding games objects, plus assigning genre
        em.getTransaction().begin();

        for (int i = 0; i < g.size(); i++) {
            em.persist(g.get(i));
        }

        //Creation of games object(s)...
        Calendar release1 = Calendar.getInstance();
        release1.set(2016, 0, 13, 14, 30, 0);
        Console game1 = new Console(release1, 39.99, 34, "PG13", "Civilization V", "EA");
        em.persist(game1);
        g.get(1).addGame(game1);

        Calendar release2 = Calendar.getInstance();
        release2.set(2016, 3, 15, 0, 01, 0);
        Console game2 = new Console(release2, 19.99, 105, "12+", "Burnout Paradise", "EA");
        em.persist(game2);
        g.get(3).addGame(game2);

        Calendar release3 = Calendar.getInstance();
        release3.set(2017, 2, 05, 23, 59, 0);
        Console game3 = new Console(release3, 34.99, 97, "PG", "Rocket League", "Psyonix");
        em.persist(game3);
        g.get(0).addGame(game3);

        Calendar release4 = Calendar.getInstance();
        release4.set(2018, 11, 29, 13, 30, 0);
        PC p1 = new PC(release4, 9.99, 305, "Steam", "11+", "DayZ", "Bohemia Interactive", "DLC1");
        em.persist(p1);
        g.get(2).addGame(p1);

        Calendar release5 = Calendar.getInstance();
        release5.set(2017, 0, 01, 23, 59, 0);
        PC p2 = new PC(release5, 69.99, 141, "Windows", "3+", "Chess", "Chess Studios", "Limited Edition Board");
        em.persist(p2);
        g.get(1).addGame(p2);

        em.getTransaction().commit();

        //Calls user menu after changes committed to the database
        displayMenu1();
    }

    public static void displayMenu1() {
        Scanner in = new Scanner(System.in);
        jpa = new JPAService();
        boolean exists;
        //While always true, only exit via sytem.exit which user chooses via option 9.
        while (true) {
            //Initial menu
            System.out.println("Please press 1 to view games");
            System.out.println("Please press 2 to view games by genre");
            System.out.println("Please press 3 if you want to update the name of a game");
            System.out.println("Please press 4 to update the age rating of a game");
            System.out.println("Please press 5 to update the publisher of a game");
            System.out.println("Please press 6 to delete a game");
            System.out.println("Please press 7 to add a new game");
            System.out.println("Please press 8 to add a new genre");
            System.out.println("Press 9 to view all genre's");
            System.out.println("Press 10 to quit");

            int choice = in.nextInt();
            in.nextLine();

            //Switch with variable choice assigned the value of the option you choose, e.g. if choice = 1 then it goes to case 1...
            switch (choice) {
                case 1:
                    //Prints all games by genre.
                    jpa.printAllGames();
                    break;
                case 2:
                    //View individual genre's
                    System.out.println("Please enter the name of the genre you wish to view");
                    String genre = in.nextLine();
                    jpa.printGenre(genre);
                    break;
                case 3:
                    //Update a game's name
                    System.out.println("Please enter the name of the game you wish to update");
                    String name = in.nextLine();
                    if (!jpa.findGame(name)) {
                        System.out.println("Entity not found");
                    } else {
                        System.out.println("Please enter the new name for " + name);
                        String newName = in.nextLine();
                        jpa.updateGame(jpa.findGameID(name), newName);
                        System.out.println("Name has been updated for the game " + name);
                    }
                    break;
                case 4:
                    //Update a game's age rating
                    System.out.println("Please enter the name of the game you wish to update");
                    String name1 = in.nextLine();
                    if (!jpa.findGame(name1)) {
                        System.out.println("Entity not found");
                    } else {
                        System.out.println("Please enter the new age rating for " + name1);
                        String age = in.nextLine();
                        jpa.updateAge(jpa.findGameID(name1), age);
                        System.out.println("The age rating has been updated for the game " + name1);
                    }
                    break;
                case 5:
                    //Update a game's publisher name
                    System.out.println("Please enter the name of the game you wish to update");
                    String name2 = in.nextLine();
                    if (!jpa.findGame(name2)) {
                        System.out.println("Entity not found");
                    } else {
                        System.out.println("Please enter the new publisher for " + name2);
                        String pub = in.nextLine();
                        jpa.updatePublisher(jpa.findGameID(name2), pub);
                        System.out.println("The publisher has been updated for the game " + name2);
                    }
                    break;
                case 6:
                    //Delete a game from the system
                    System.out.println("Please enter the name of the game you wish to delete");
                    String updateName = in.nextLine();
                    if (!jpa.findGame(updateName)) {
                        System.out.println("Entity not found");
                    } else {
                        jpa.removeGame(jpa.findGameID(updateName));
                        System.out.println("The Entity " + updateName + " has been removed");
                    }
                    break;
                case 7:
                    //Create a pc or console game object
                    System.out.println("Please enter the Game System(Console or PC)");
                    String system = in.nextLine();
                    while (!system.equals("PC") && !system.equals("Console")) {
                        System.out.println("Invalid game system. Please re-enter");
                        system = in.nextLine();
                    }
                    Calendar release = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm", Locale.ENGLISH);
                    System.out.println("Please enter the genre of the game you wish to add");
                    String genreAdd = in.nextLine();
                    exists = false;
                    for (int i = 0; i < g.size(); i++) {
                        if (genreAdd.equals(g.get(i).getGenre_Name())) {
                            exists = true;
                        }
                    }
                    while (exists == false) {
                        System.out.println("Invalid genre. Please re-enter");
                        genreAdd = in.nextLine();
                        for (int i = 0; i < g.size(); i++) {
                            if (genreAdd.equals(g.get(i).getGenre_Name())) {
                                exists = true;
                            }
                        }
                    }
                    System.out.println("Please enter the name of the game you wish to add");
                    String nameAdd = in.nextLine();
                    System.out.println("Please enter the release date of the game you wish to add in the format(January 05 2017 13:44)");
                    String release_DateAdd = in.nextLine();
                    //Converting String to Calender so it can persist as DATE
                    try {
                        release.setTime(sdf.parse(release_DateAdd));
                    } catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                    }
                    System.out.println("Please enter the price of the game you wish to add");
                    double priceAdd = in.nextDouble();
                    in.nextLine();
                    System.out.println("Please enter the age rating of the game you wish to add");
                    String age_RatingAdd = in.nextLine();
                    System.out.println("Please enter the publisher of the game you wish to add");
                    String publisherAdd = in.nextLine();
                    System.out.println("Please enter the quantity of the game you wish to add");
                    int quantityAdd = in.nextInt();
                    Game game;
                    //Create PC Object
                    if (system.equals("PC")) {
                        in.nextLine();
                        System.out.println("Please enter the Platform e.g. Steam etc.");
                        String platform = in.nextLine();
                        System.out.println("Please enter the bonus content");
                        String bonus = in.nextLine();
                        em.getTransaction().begin();
                        game = new PC(release, priceAdd, quantityAdd, platform, age_RatingAdd, nameAdd, publisherAdd, bonus);
                        em.persist(game);
                        for (int i = 0; i < g.size(); i++) {
                            if (g.get(i).getGenre_Name().equals(genreAdd)) {
                                g.get(i).addGame(game);
                            }
                        }
                        em.getTransaction().commit();
                        System.out.println("PC Object added successfully");
                        //Create PS4 Object
                    } else if (system.equals("Console")) {
                        em.getTransaction().begin();
                        game = new Console(release, priceAdd, quantityAdd, age_RatingAdd, nameAdd, publisherAdd);
                        em.persist(game);
                        for (int i = 0; i < g.size(); i++) {
                            if (g.get(i).getGenre_Name().equals(genreAdd)) {
                                g.get(i).addGame(game);
                            }
                        }
                        em.getTransaction().commit();
                        System.out.println("Console Object added successfully");
                    } else {
                        System.out.println("Object type does not exist.");
                    }
                    break;
                case 8:
                   //Create a new genre
                    System.out.println("Please enter the name of the genre you wish to add");
                    String addGenre = in.nextLine();
                    g.add(new Genre(addGenre));
                    //Persists the new genre to the database
                    em.getTransaction().begin();
                    em.persist(g.get(g.size() - 1));
                    em.getTransaction().commit();
                    break;
                case 9:
                    System.out.println("*****************************************");
                    TypedQuery<Genre> query2
                            = em.createNamedQuery("Genre.findAll", Genre.class);

                    List<Genre> results1 = query2.getResultList();
                    for (Genre g : results1) {
                        System.out.println(g);
                    }
                    System.out.println();
                    break;
                case 10:
                    //Exits the program
                    System.out.println("\nThe program has now ended.");
                    System.exit(0);
                    break;
                case 11:
                    //Delete a genre from the system
                    System.out.println("Please enter the name of the genre you wish to delete");
                    String delGen = in.nextLine();
                    if (!jpa.findGenre(delGen)) {
                        System.out.println("Entity not found");
                    } else {
                        jpa.removeGenre(jpa.findGenreID(delGen));
                        System.out.println("The Entity " + delGen + " has been removed");
                    }
                    break;
                default:
                    //If invalid option is chosen by user, e.g. choice = 112
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
    }
}
