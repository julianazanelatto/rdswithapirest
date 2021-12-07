package com.dioclass.rdswithapirest.Hateoas.Exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class OrderNotFoundExceptionHateoas extends RuntimeException {
    public OrderNotFoundExceptionHateoas(long id){
        super("Could not found the order id: "+id);
    }
}
