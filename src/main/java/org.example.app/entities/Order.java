package org.example.app.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order extends PanacheEntity {
    public String item;
    public BigDecimal price;
    public String status = "PENDING";
}
