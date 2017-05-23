package ru.kpfu.itis.dmitry_ivanov.entity;

import javax.persistence.*;

/**
 * Created by Dmitry on 22.05.2017.
 */

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client")
    private String client;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;

    @Column(name = "issue")
    private String issueDate;

    @Column(name = "return")
    private String returnDate;

    public Reservation() {
    }

    public Reservation(String client, String number, Car car, String issueDate, String returnDate) {

        this.client = client;
        this.number = number;
        this.car = car;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String nuber) {
        this.number = nuber;
    }

    public String getCar() {
        return car.getModel();
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
