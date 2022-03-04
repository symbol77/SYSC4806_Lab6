package com.example.lab5;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * BuddyInfo class containing contact information about a buddy
 *
 * Author: William Tran - 101115465
 */

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String number;

    private String address;
    private String testField; // Solely for testing CircleCI and Heroku purposes

    public BuddyInfo() {

    }

    public BuddyInfo(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("BuddyInfo %d | %s", id, name);
    }
}

