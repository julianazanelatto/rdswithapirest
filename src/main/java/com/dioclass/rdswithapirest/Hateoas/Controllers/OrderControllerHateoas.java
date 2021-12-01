package com.dioclass.rdswithapirest.Hateoas.Controllers;

import com.dioclass.rdswithapirest.Hateoas.Entitys.OrderHateoas;
import com.dioclass.rdswithapirest.Hateoas.Entitys.Status;
import com.dioclass.rdswithapirest.Hateoas.Exceptions.OrderNotFoundExceptionHateoas;
import com.dioclass.rdswithapirest.Hateoas.Repositories.OrderRepositoryHateoas;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class OrderControllerHateoas {

    //definindo o repository
    private final OrderRepositoryHateoas repositoryOrder;

    public OrderControllerHateoas(OrderRepositoryHateoas repositoryHateoas) {
        this.repositoryOrder = repositoryHateoas;
    }

    @GetMapping("/orders")
    ResponseEntity<List<OrderHateoas>> consultOrderAll() {
        List<OrderHateoas> ordersList = repositoryOrder.findAll();
        long idOrder;
        if (ordersList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //tipo var: lista
        for (OrderHateoas orderHateoas : ordersList) {
            idOrder = orderHateoas.getId();
            Link linkUri = linkTo(methodOn(OrderControllerHateoas.class).consultOneOrder(idOrder)).withSelfRel();
            orderHateoas.add(linkUri);
            linkUri = linkTo(methodOn(OrderControllerHateoas.class).consultOrderAll()).withRel("List of orders");
            orderHateoas.add(linkUri); //link retorno
        }
        return new ResponseEntity<List<OrderHateoas>>(ordersList, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    ResponseEntity<OrderHateoas> consultOneOrder(@PathVariable Long id) {
        Optional<OrderHateoas> orderPointer = repositoryOrder.findById(id);
        if (orderPointer.isPresent()) {
            OrderHateoas order = orderPointer.get();
            order.add(linkTo(methodOn(OrderControllerHateoas.class).consultOrderAll()).withRel("All orders"));
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //adicionando um employee
    @PostMapping("/orders")
    OrderHateoas newOrder(@RequestBody OrderHateoas newOrder) {
        return repositoryOrder.save(newOrder);
    }

    //modificação parcial
    @PutMapping("/orders/{id}")
    OrderHateoas replaceOrder(@RequestBody OrderHateoas newOrder, long id) {
        return repositoryOrder.findById(id).map(order -> {
            order.setDescription(newOrder.getDescription());
            order.setStatus(newOrder.getStatus());
            return repositoryOrder.save(newOrder);
        }).orElseGet(() -> {
            newOrder.setId(id);
            return repositoryOrder.save(newOrder);
        });
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable long id) {
        repositoryOrder.deleteById(id);
    }

    //criando métodos para cancelar e completar uma order no banco de dados
    @PutMapping("/orders/{id}/cancel")
    ResponseEntity<?> cancelOrder(@PathVariable Long id) {
        OrderHateoas cancelledOrder = repositoryOrder.findById(id).orElseThrow(() -> new OrderNotFoundExceptionHateoas(id));
        if (cancelledOrder.getStatus() == Status.IN_PROGRESS) {
            cancelledOrder.setStatus(Status.CANCELLED);
            cancelledOrder.add(linkTo(methodOn(OrderControllerHateoas.class).consultOneOrder(id)).withSelfRel());
            cancelledOrder.add(linkTo(methodOn(OrderControllerHateoas.class).consultOrderAll()).withRel("custumer list of orders"));
            repositoryOrder.save(cancelledOrder);
            return ResponseEntity.ok(cancelledOrder);
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body("You can't complete the task, the order has a " +
                        cancelledOrder.getStatus() + " status");
    }

    @PutMapping("/orders/{id}/complete")
    ResponseEntity<?> completeOrder(@PathVariable Long id) {
        OrderHateoas cancelledOrder = repositoryOrder.findById(id).orElseThrow(() -> new OrderNotFoundExceptionHateoas(id));
        if (cancelledOrder.getStatus() == Status.IN_PROGRESS) {
            cancelledOrder.setStatus(Status.COMPLETED);
            cancelledOrder.add(linkTo(methodOn(OrderControllerHateoas.class).consultOneOrder(id)).withSelfRel());
            cancelledOrder.add(linkTo(methodOn(OrderControllerHateoas.class).consultOrderAll()).withRel("Custumer list of orders"));
            repositoryOrder.save(cancelledOrder);
            return ResponseEntity.ok(cancelledOrder);
        }
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body("You can't complete the task, the order has a " +
                        cancelledOrder.getStatus() + " status");
    }

}
