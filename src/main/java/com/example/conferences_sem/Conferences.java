package com.example.conferences_sem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Conferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String conf_name;
    private String date;
    private String moder_name;
    private String speaker_name;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getConf_name() { return conf_name; }

    public void setConf_name(String conf_name) { this.conf_name = conf_name; }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getModer_name() { return moder_name; }

    public void setModer_name(String moder_name) { this.moder_name = moder_name; }

    public String getSpeaker_name() { return speaker_name; }

    public void setSpeaker_name(String speaker_name) { this.speaker_name = speaker_name; }


    @Override
    public String toString() {
        return "theatre [id=" + id + ", conf_name=" + conf_name + ", date=" + date +
                ", moder_name=" + moder_name + ", speaker_name=" + speaker_name + "]";
    }
}