package com.example.lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class AddressBookViewController {

    @Autowired
    private AddressBookRepo repo;

    @GetMapping("/newAddressBook")
    public String greeting() {
        return "newAddressBook";
    }

    @PostMapping("/addressbook/newBook")
    public String  newAddressBook(Model model) {
        AddressBook newBook = new AddressBook();
        repo.save(newBook);
        model.addAttribute("book", newBook);
        return "newBook";
    }

    @PostMapping("/addressbook/addBuddy")
    public String addBuddy(@RequestParam(value = "bookID") Long bookID, @RequestParam(value = "name") String name,
                           @RequestParam(value = "number") String number, @RequestParam(value = "address") String address,
                           Model model) {
        AddressBook book = repo.findById(bookID)
                .orElseThrow(() -> new EntityNotFoundException());

        BuddyInfo newBuddy = new BuddyInfo(name, number, address);
        book.addBuddy(newBuddy);
        repo.save(book);

        model.addAttribute("book", book);
        model.addAttribute("buddies", book.getAddressBook());
        return "newBook";
    }
}
