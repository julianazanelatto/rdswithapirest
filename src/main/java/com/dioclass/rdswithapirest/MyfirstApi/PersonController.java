package com.dioclass.rdswithapirest.MyfirstApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    // ficam os atributos e métodos
    private final PersonRepository repositoryPerson;

    public PersonController(PersonRepository repository) {
        this.repositoryPerson = repository;
    }

    @GetMapping("/")
    public String helloWorld(){
        return "Hello you are using spring boot! This is your first controller method.";
    }

    @GetMapping("/persons")
    public List<Person> personFindAll(){
        return repositoryPerson.findAll();
    }

    @GetMapping("/persons/{id}")
    public Optional personFindById(@PathVariable Long id){
            return repositoryPerson.findById(id);
    }

}
