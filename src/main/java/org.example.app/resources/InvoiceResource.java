package org.example.app.resources;

import org.example.app.entities.Invoice;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/invoices")
@Produces(MediaType.APPLICATION_JSON)
public class InvoiceResource {

    @GET
    public List<Invoice> getAllInvoices() {
        return Invoice.listAll();
    }

    @GET
    @Path("/{id}")
    public Invoice getInvoice(Long id) {
        return Invoice.findById(id);
    }
}