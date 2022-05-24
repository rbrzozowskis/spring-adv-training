package pl.training.orders.adapters.rest;

import lombok.Data;

import java.util.Map;

@Data
public class OrderDto {

    Long id;
    private Map<Long, Integer> entries;

}
