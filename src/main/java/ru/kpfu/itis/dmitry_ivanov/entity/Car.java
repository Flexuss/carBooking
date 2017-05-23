package ru.kpfu.itis.dmitry_ivanov.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Dmitry on 22.05.2017.
 */

@Entity
@Table(name = "cars")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "power")
    private int power;

    @Column(name = "cost")
    private int cost;

    public Car(String model, int year, int mileage, int power, int cost) {
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.power = power;
        this.cost = cost;
    }

    public Car() {
    }

    @OneToMany(mappedBy = "car")
    private Set<Reservation> reservations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
