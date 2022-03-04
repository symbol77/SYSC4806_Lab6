package com.example.lab5;

import org.springframework.data.repository.CrudRepository;

public interface AddressBookRepo extends CrudRepository<AddressBook, Long> {
    AddressBook findById(long id);
}
