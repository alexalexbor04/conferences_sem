package com.example.conferences_sem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Barbershop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String service;
    private String master;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) { this.service = service; }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }


    @Override
    public String toString() {
        return "theatre [id=" + id + ", name=" + name + ", date=" + date +
                ", service=" + service + ", master=" + master + "]";
    }
}