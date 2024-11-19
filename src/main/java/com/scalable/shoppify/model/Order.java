package com.scalable.shoppify.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private Integer orderId;
    private Integer userId;
    private Double price;
    private List<OrderItem> orderItems;
    private String orderStatus;

}


