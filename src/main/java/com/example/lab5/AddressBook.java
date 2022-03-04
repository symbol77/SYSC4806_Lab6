package com.example.lab5;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * AddressBook class that stores a list of Buddy's information
 *
 * Author: William Tran - 101115465
 */

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BuddyInfo> contacts;


    public AddressBook() {
        this.contacts = new ArrayList<BuddyInfo>();

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addBuddy(BuddyInfo buddy) {
        this.contacts.add(buddy);
    }

    public boolean removeBuddy(Long id) {
        for (BuddyInfo buddy : this.contacts) {
            if (buddy.getId() == id) {
                this.contacts.remove(buddy);
                return true;
            }
        }

        return false;
    }

    public void printContacts() {
        for (BuddyInfo buddy : this.contacts) {
            System.out.println("Name | " + buddy.getName() + ", Phone | " + buddy.getNumber());
        }
    }

    public List<BuddyInfo> getAddressBook() {
        return this.contacts;
    }

    @Override
    public String toString() {
        return String.format("AddressBook %d", id);
    }

}

