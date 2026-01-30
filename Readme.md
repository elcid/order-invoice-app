

This solution provides a complete Quarkus application using **Java 21**, **H2 Database**, **Hibernate ORM with Panache**, and **SmallRye Reactive Messaging** for AMQP integration.

### Project Overview
1.  **Order Entity**: Created via a REST POST request.
2.  **AMQP Broker**: The Order is emitted to an AMQP queue (`orders`).
3.  **Invoice Consumer**: Listens to the queue, generates an `Invoice` linked to the Order, and saves it to the database.

### Prerequisites
You need a local AMQP broker (like RabbitMQ) running, or you can rely on Quarkus Dev Services if you have Docker installed, which will spin up a RabbitMQ container automatically.

### The Generator Script
Save the following code as `generate_app.sh`, give it execution permissions (`chmod +x generate_app.sh`), and run it (`./generate_app.sh`).



### How to Run

1. **Run the application (Dev Mode):**
    ```bash
    cd order-invoice-app
    mvn quarkus:dev
    ```
    *Note: If you don't have a local RabbitMQ instance, Quarkus Dev Services will automatically download and start a RabbitMQ container using Docker.*

### How to Test

1.  **Create an Order** (POST):
    ```bash
    curl -X POST http://localhost:8080/orders \
    -H "Content-Type: application/json" \
    -d '{"item": "Laptop", "price": 999.99}'
    ```
    **Response:** You will see the Order JSON returned with an ID.

2.  **Check the Logs:**
    Look at your console output. You should see:
    ```text
    Received order from AMQP: 1
    Invoice created for Order: 1
    ```

3.  **Verify Data:**
    You can verify the data is in the H2 database by accessing the console (if enabled) or simply checking the logs. Since the consumer is blocking and transactional, the `Invoice` is committed to the database immediately after the message is processed.