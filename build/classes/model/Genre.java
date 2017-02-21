/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Jordan
 */
@Entity
@Table(name = "Genre")
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g ORDER BY g.genre_Id"),
    @NamedQuery(name = "Genre.findById", query = "SELECT g FROM Genre g WHERE g.genre_Id<=:id ")})
@SequenceGenerator(name = "genId_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genId_seq")
    private int genre_Id;
    private String genre_Name;
    
    @OneToMany(mappedBy = "gen", cascade = CascadeType.PERSIST)
    private List<Game> gameList = new ArrayList<>();

    public Genre() {
    }

    public Genre(String genre_Name) {
        this.genre_Name = genre_Name;
    }

    public int getGenre_Id() {
        return genre_Id;
    }

    public void setGenre_Id(int genre_Id) {
        this.genre_Id = genre_Id;
    }

    public String getGenre_Name() {
        return genre_Name;
    }

    public void setGenre_Name(String genre_Name) {
        this.genre_Name = genre_Name;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public void addGame(Game g)
    {
        gameList.add(g);
        g.setGen(this);
    }
    
    @Override
    public String toString(){
        return "Genre ID\t:" +genre_Id + "\nGenre Name\t:" +genre_Name;
    }   
}