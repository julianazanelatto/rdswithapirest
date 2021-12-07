package com.dioclass.rdswithapirest.Hateoas.Entitys;

import org.springframework.hateoas.RepresentationModel;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name="CUSTUMER_ORDER")
//para habilitar o uso de links nos recursos precisamos utilizar a classe RepresentationMondel do Hateoas
public class OrderHateoas extends RepresentationModel<OrderHateoas> {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;
    private String description;

    public OrderHateoas() {
    }

    public OrderHateoas(Status status, String description) {
        this.status = status;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderHateoas order = (OrderHateoas) o;
        return Objects.equals(id, order.id) && status == order.status && Objects.equals(description, order.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, description);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
