package com.dioclass.rdswithapirest.Hateoas.Repositories;

import com.dioclass.rdswithapirest.Hateoas.Entitys.EmployeeHateoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepositoryHateoas extends JpaRepository<EmployeeHateoas, Long> {

}
