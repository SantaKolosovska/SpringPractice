package com.example.SprBootPracticeJanuary2022.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {

    private final UUID id;

    @NotBlank
    private final String name;

    public Person(@JsonProperty("id")  UUID id, // we don't send id in Postman(Json) because it is auto-generated
                  @JsonProperty("name") String name) {  //@JsonProperty for Postman to recognize
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
