package com.example.lab5;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class AddressBookController {
    public final AddressBookRepo repo;

    AddressBookController(AddressBookRepo repository) {
        this.repo = repository;
    }

    @GetMapping("/books")
    List<AddressBook> all() {
        return (List<AddressBook>) repo.findAll();
    }

    @PostMapping("/books/newBook")
    AddressBook newAddressBook() {
        return repo.save(new AddressBook());
    }

    @PostMapping("/books/{id}/addBuddy")
    BuddyInfo addBuddy(@PathVariable Long id, @RequestBody BuddyInfo buddy) {
        AddressBook book = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        book.addBuddy(buddy);
        repo.save(book);
        return buddy;
    }

    @GetMapping("/books/getBook/{id}")
    AddressBook one(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @DeleteMapping("/books/deleteBook/{id}")
    void deleteBook(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @DeleteMapping("/books/removeBuddy/{id}")
    String removeBuddy(@PathVariable Long id, @RequestParam(value = "buddyID") Long buddyId) {
        AddressBook book = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        if (book.removeBuddy(buddyId)) {
            repo.save(book);
            return "Removed buddy";
        }
        return "Failed to remove buddy";
    }
}
