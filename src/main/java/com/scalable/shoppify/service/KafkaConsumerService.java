package com.scalable.shoppify.service;

import com.scalable.shoppify.model.Order;
import com.scalable.shoppify.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @KafkaListener(topics = "${topic.name}", groupId = "inventory-group")
    public void listen(Order order) {
        System.out.println("Received Order: " + order);
        if ("PLACED".equals(order.getOrderStatus())) {
            System.out.println("Order is placed, updating Inventory");
            order.getOrderItems().forEach(orderItem -> inventoryRepository.findByItemId(orderItem.getOrderItemId())
                    .ifPresentOrElse(inventory -> {
                        int oldQuantity = inventory.getQuantity();
                        int newQuantity = oldQuantity - orderItem.getQuantity();
                        inventory.setQuantity(newQuantity);
                        inventoryRepository.save(inventory);
                        System.out.println("Inventory for ItemId " + orderItem.getOrderItemId() + " updated successfully from " + oldQuantity + " to " + newQuantity);
                    }, () -> {
                        System.out.println("Inventory for ItemId " + orderItem.getOrderItemId() + " not found");
                        throw new RuntimeException("Inventory not found");
                    }));
        } else {
            System.out.println("Order is not placed, skipping inventory update");
        }
    }
}

