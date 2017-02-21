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
@DiscriminatorValue( value="PS4" )
@PrimaryKeyJoinColumn(referencedColumnName="game_Id")
@SuppressWarnings("SerializableClass")
public class Console extends Game {
    @Temporal(TemporalType.DATE)
    private Calendar release_Date;
    private double price;
    private int quantity;

    public Console() {
    }

    public Console(Calendar release_Date, double price, int quantity, String age_Rating, String name, String publisher) {
        super(age_Rating, name, publisher);
        this.release_Date = release_Date;
        this.price = price;
        this.quantity = quantity;
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

    @Override
    public String toString(){
        String d = String.format("%1$s%2$tB %2$td, %2$tY   -   Time: %2$tH:%2$tM", 
            "\nRelease Date\t:" ,release_Date);
        return "\nPS4" +super.toString() + d + "\nPrice\t\t:€" +price + "\nQuantity\t:" +quantity +"\n";
    }
}
