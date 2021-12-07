package com.dioclass.rdswithapirest.Hateoas.Controllers;

import com.dioclass.rdswithapirest.Hateoas.Repositories.EmployeeRepositoryHateoas;
import com.dioclass.rdswithapirest.Hateoas.Entitys.EmployeeHateoas;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeControllerHateoas {
    //definindo o repository
    private final EmployeeRepositoryHateoas repository;
    public EmployeeControllerHateoas(EmployeeRepositoryHateoas repository) {
        this.repository = repository;
    }

    @GetMapping("/employees") //definindo um label para o GetMapping
    //criar método do tipo lista de employees
    ResponseEntity<List<EmployeeHateoas>>listOfEmployeeAll(){
        List<EmployeeHateoas> employeeHateoasList = repository.findAll();
        long id;
        Link linkUri;
        // inserindo retorno com reponse http
        if (employeeHateoasList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        for (EmployeeHateoas employeeHateoas: employeeHateoasList) {
            id = employeeHateoas.getId();
            linkUri = linkTo(methodOn(EmployeeControllerHateoas.class).consultOneEmployee(id)).withSelfRel();
            employeeHateoas.add(linkUri);
        }
        return new ResponseEntity<List<EmployeeHateoas>>(employeeHateoasList,HttpStatus.OK);
    }

    //adicionando um employee
    @PostMapping("/employees")
    EmployeeHateoas newEmployee(@RequestBody EmployeeHateoas newEmployee){
        return repository.save(newEmployee);
    }

    //Get para único employee
    @GetMapping("/employees/{id}")
    ResponseEntity<EmployeeHateoas> consultOneEmployee(@PathVariable Long id){
        // Para enviar como resposta HTTP devemos setar o retorno para ResponseEntity<>
        Link linkUri;
        Optional<EmployeeHateoas> employeesHateoasOptional = repository.findById(id);
        if (employeesHateoasOptional.isPresent()) {
            EmployeeHateoas employeesHateoas = employeesHateoasOptional.get();
            employeesHateoas.add(linkTo(methodOn(EmployeeControllerHateoas.class).listOfEmployeeAll()).withRel("Employees List"));
            employeesHateoas.add(linkTo(methodOn(EmployeeControllerHateoas.class).consultOneEmployee(id)).withSelfRel());
            return new ResponseEntity<>(employeesHateoas, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //modificação parcial
    @PutMapping("/employees/{id}")
    EmployeeHateoas replaceEmployee(@RequestBody EmployeeHateoas newEmployee, long id){
        return repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setAddress(newEmployee.getAddress());
            employee.setRole(newEmployee.getRole());
            return repository.save(newEmployee);
        }).orElseGet(() -> {
            newEmployee.setId(id);
            return repository.save(newEmployee);
        });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable long id){
        repository.deleteById(id);
    }
}
