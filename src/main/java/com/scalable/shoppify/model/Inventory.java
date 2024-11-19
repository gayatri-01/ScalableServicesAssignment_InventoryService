package com.scalable.shoppify.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory_details")
@Getter
@Setter
public class Inventory {
    @Id
    private int inventoryId;
    private int itemId;
    private int quantity;
}
