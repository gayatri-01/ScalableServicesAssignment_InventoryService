package com.scalable.shoppify.model;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItem {
    private int orderItemId;
    private int quantity;
    private double price;

}
