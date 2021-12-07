package com.dioclass.rdswithapirest.ApiRest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public //definindo um label para o GetMapping
    //criar método do tipo lista de employees
    List<Employee> listOfEmployeeAll(){
        return repository.findAll();
    }

    //adicionando um employee
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }

    //Get para único employee
    @GetMapping("/employees/{id}")
    Employee consultOneEmployee(@PathVariable long id){
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    //modificação parcial
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, long id){
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
