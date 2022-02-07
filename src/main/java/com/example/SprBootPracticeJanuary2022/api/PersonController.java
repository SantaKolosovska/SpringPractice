package com.example.SprBootPracticeJanuary2022.api;

import com.example.SprBootPracticeJanuary2022.model.Person;
import com.example.SprBootPracticeJanuary2022.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping ("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired  // injecting the service
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping    // telling that this is a post method
    public void addPerson(@Valid @NonNull @RequestBody Person person) {    // @RequestBody - telling that we are taking the request body from Postman(Json)
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")  // allows to pass id in path in browser or Postman after slash (/id). Grabs the id and
    // turns it into UUID
    public Person getPersonById(@PathVariable("id") UUID id){     // @PathVariable for Postman
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping(path="{id}")
    public void updatePerson(@PathVariable ("id") UUID id, @Valid @NonNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }
}
