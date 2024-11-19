# InventoryService
This microservice is built as part of Scalable-Services-Assignment.
It is built using Java Spring Boot & MongoDB.

### Steps to run locally

- Download & Install Java by following steps [here](https://www.geeksforgeeks.org/how-to-download-and-install-java-for-64-bit-machine/)

- Download & Install MongoDB by following steps [here](https://www.geeksforgeeks.org/how-to-install-mongodb-on-windows/)

- Create a Mongo Database - **Inventory**

- Add a Collection to it - **inventory_details**
 
- Add some sample Item documents into this collection. For Example:
```json
{
  "_id": 101,
  "itemId": 1,
  "quantity": 200
}
```
- Download & Install Kafka by following steps [here](https://www.geeksforgeeks.org/how-to-install-and-run-apache-kafka-on-windows/)

- Kafka Server will now be running on `localhost:9092`

- Clone this project

- Run `.\gradlew build` (only for 1st time)

- Run `.\gradlew bootRun`  to run the application

- Application will now be start listening to Kafka Topic `inventory-input-topic`


### Flow of the Service

- The Inventory Service listens to the Kafka Topic `inventory-input-topic` for any new messages.
- If the received Order message says as OrderStatus "PLACED", then Inventory is updated by reducing the quantity of the item.
- In case of all other OrderStatus, the inventory is not updated.
- If the OrderStatus is "PLACED", but the item is not in the inventory, then it throws an Exception

### Logs

**Inventory Updated**

```log
Received Order: Order(orderId=415, userId=1, price=100.0, orderItems=[OrderItem(orderItemId=1, quantity=4, price=25.0)], orderStatus=PLACED)
Order is placed, updating Inventory
Inventory for ItemId 1 updated successfully from 192 to 188
```

**Inventory Update Skipped**

```log
Received Order: Order(orderId=244, userId=1, price=100.0, orderItems=[OrderItem(orderItemId=1, quantity=4, price=25.0)], orderStatus=FAILED_TO_PROCESS)
Order is not placed, skipping inventory update
```

**Item not found in the Inventory**

```log
Received Order: Order(orderId=202, userId=1, price=100.0, orderItems=[OrderItem(orderItemId=2, quantity=4, price=25.0)], orderStatus=PLACED)
Order is placed, updating Inventory
Inventory for ItemId 2 not found
Caused by: java.lang.RuntimeException: Inventory not found
```
