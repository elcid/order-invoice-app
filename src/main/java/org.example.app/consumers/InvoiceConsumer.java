package org.example.app.consumers;

import org.example.app.entities.Invoice;
import org.example.app.entities.Order;
import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.math.BigDecimal;

@ApplicationScoped
public class InvoiceConsumer {

    @Incoming("orders")
    @Blocking
    @Transactional
    public void processOrder(Order order) {
        System.out.println("Received order from AMQP: " + order.id);

        Invoice invoice = new Invoice();
        invoice.orderId = order.id;
        invoice.amount = order.price;
        invoice.status = "PAID";

        invoice.persist();
        System.out.println("Invoice created for Order: " + order.id);
    }
}
