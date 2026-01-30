package org.example.app.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Invoice extends PanacheEntity {
    public Long orderId;
    public BigDecimal amount;
    public String status = "CREATED";
}
