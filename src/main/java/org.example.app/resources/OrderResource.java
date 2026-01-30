package org.example.app.resources;

import org.example.app.entities.Order;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    @Channel("orders")
    Emitter<Order> orderEmitter;

    @GET
    public List<Order> getAllOrders() {
        // Hibernate Panache provides this helper method
        return Order.listAll();
    }

    @GET
    @Path("/{id}")
    public Order getOrder(Long id) {
        return Order.findById(id);
    }

    @POST
    @Transactional
    public Uni<Response> createOrder(Order order) {
        order.persist();
        orderEmitter.send(order).toCompletableFuture().join();
        return Uni.createFrom().item(Response.ok(order).status(Response.Status.CREATED).build());
    }
}