package com.dioclass.rdswithapirest.ApiRest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Employee {
    //entidade jpa para persistencia de dados
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String role;
    private String address;

    //empty constructor for jpa
    public Employee(){
    }

    public Employee(String name, String role, String address){
        this.name = name;
        this.role = role;
        this.address = address;
    }

    //set the getters and setters
    public void setId(long id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getAddress() {
        return address;
    }

    //to string method for debug
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id,this.name,this.role,this.address);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id)
                && Objects.equals(this.name,employee.name)
                && Objects.equals(this.role,employee.role)
                && Objects.equals(this.address,employee.address);
    }
}
