/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Jordan
 */

@Entity
@Table(name="Game")
@Inheritance( strategy = InheritanceType.JOINED )
@DiscriminatorColumn( name = "platform" )
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g"),
    @NamedQuery(name = "Game.findById", query = "SELECT g FROM Game g WHERE g.game_Id<=:id ")})
@SequenceGenerator(name = "gameId_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameId_seq")
    private int game_Id;
    private String age_Rating;
    private String name;
    private String publisher;

    @ManyToOne()
    @JoinColumn(name = "genre_Id")
    private Genre gen;
    
    public Game() {
    }

    public Game(String age_Rating, String name, String publisher) {
        this.age_Rating = age_Rating;
        this.name = name;
        this.publisher = publisher;
    }

    public int getGame_Id() {
        return game_Id;
    }

    public void setGame_Id(int game_Id) {
        this.game_Id = game_Id;
    }

    public String getAge_Rating() {
        return age_Rating;
    }

    public void setAge_Rating(String age_Rating) {
        this.age_Rating = age_Rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Genre getGen() {
        return gen;
    }

    public void setGen(Genre gen) {
        this.gen = gen;
    }
    
    @Override
    public String toString(){
        return "\nGame ID\t\t:" +game_Id +"\nName\t\t:" +name + "\nPublisher\t:" +publisher + "\nAge Rating\t:" +age_Rating;
    }
}