/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jordan
 */
@Entity
@DiscriminatorValue( value="PC" )
@PrimaryKeyJoinColumn(referencedColumnName="game_Id")
@SuppressWarnings("SerializableClass")
public class PC extends Game {
    @Temporal(TemporalType.DATE)
    private Calendar release_Date;
    private double price;
    private int quantity;
    private String platform;
    private String bonus_Content;

    public PC() {
    }

    public PC(Calendar release_Date, double price, int quantity, String platform, String age_Rating, String name, String publisher, String bonus_Content) {
        super(age_Rating, name, publisher);
        this.release_Date = release_Date;
        this.price = price;
        this.quantity = quantity;
        this.platform = platform;
        this.bonus_Content = bonus_Content;
    }

    public Calendar getRelease_Date() {
        return release_Date;
    }

    public void setRelease_Date(Calendar release_Date) {
        this.release_Date = release_Date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBonus_Content() {
        return bonus_Content;
    }

    public void setBonus_Content(String bonus_Content) {
        this.bonus_Content = bonus_Content;
    }
    
    @Override
    public String toString(){
        String d = String.format("%1$s%2$tB %2$td, %2$tY   -   Time: %2$tH:%2$tM", 
            "\nRelease Date\t:" ,release_Date);
        return "\nPC" +super.toString() +d + "\nPrice\t\t:€" +price
                + "\nQuantity\t:" +quantity + "\nPlatform\t:" +platform + "\nBonus Content\t:" +bonus_Content +"\n";
    }
}
