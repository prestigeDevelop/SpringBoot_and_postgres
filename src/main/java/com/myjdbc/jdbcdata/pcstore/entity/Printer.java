package com.myjdbc.jdbcdata.pcstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Table(name = "printer", schema = "`pc-store`")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Printer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer model;

    @Column(name = "color", nullable = false, length = 10)
    private String color;

    @Column(name = "type", nullable = false, length = 10)
    private String type;

    @Column(name = "price", nullable = false)
    private Integer price;

    // Getters and Setters

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }
}
