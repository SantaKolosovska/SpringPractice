package com.example.SprBootPracticeJanuary2022.dao;

import com.example.SprBootPracticeJanuary2022.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")     // instantiates this class as a bean to inject into other classes. You can also use @Component, but @Repository makes sure this class is served as repository
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;   // to ensure insertion works
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

//    @Override
//    public int deletePersonById(UUID id) {
//        return  selectPersonById(id)
//                .map(personFound -> {
//                    int indexOfPersonToDelete = DB.indexOf(personFound);
//                    if (indexOfPersonToDelete >= 0) {
//                        DB.remove(indexOfPersonToDelete);
//                        return 1;
//                    }
//                    return 0;
//                })
//                .orElse(0);
//    }

    @Override
    public int updatePersonById(UUID id, Person newPerson) {

        return  selectPersonById(id)
                .map(personFound -> {
                    int indexOfPersonToUpdate = DB.indexOf(personFound);
                    if (indexOfPersonToUpdate >= 0) {
                        DB.set(indexOfPersonToUpdate, new Person(id, newPerson.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

//    @Override
//    public int updatePersonById(UUID id, Person person) {
//
//        return  selectPersonById(id)
//                .map(p -> {
//                    int indexOfPersonToUpdate = DB.indexOf(person);
//                    if (indexOfPersonToUpdate >= 0) {
//                        DB.set(indexOfPersonToUpdate, person);
//                        return 1;
//                    }
//                    return 0;
//                })
//                .orElse(0);
//    }


}
