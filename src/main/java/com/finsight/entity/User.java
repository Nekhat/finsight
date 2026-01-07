package com.finsight.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
        name ="users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
        }
        )
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false, length = 255)
    private String password;

    @Column(name="preferred_currency", nullable = false)
    private String preferredCurrency = "USD";

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    // Required by JPA
    public User(){}

    // Constructor for required fields
    public User(String name, String email, String password)
    {
        this.name=name;
        this.email=email;
        this.password=password;
    }

    // Getters

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPreferredCurrency() {
        return preferredCurrency;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    // Setters (no setter for id, createdAt)

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPreferredCurrency(String preferred_currency) {
        this.preferredCurrency = preferred_currency;
    }
}