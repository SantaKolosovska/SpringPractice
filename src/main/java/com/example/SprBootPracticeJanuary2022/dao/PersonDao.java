package com.example.SprBootPracticeJanuary2022.dao;

import com.example.SprBootPracticeJanuary2022.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    // int 1 or 0 to make sure person is inserted?
    int insertPerson(UUID id, Person person);   // A UUID is a class that represents an immutable Universally Unique
                                                // Identifier (UUID). A UUID represents a 128-bit long value that is
                                                // unique to all practical purpose. It is used to identify information
                                                // in the computer system. The UUID class belongs to java. util package.

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    // this is an interface therefore the methods below are without bodies. Something about the default one above though.
    List<Person> selectAllPeople();

    // Optional is a container object which may or may not contain a non-null value. You must import java. util package
    // to use this class. If a value is present, isPresent() will return true and get() will return the value.
    // When getting an Optional return type, we're likely to check if the value is missing, leading to fewer
    // NullPointerExceptions in the applications.
    Optional<Person> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);
}
