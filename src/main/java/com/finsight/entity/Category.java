package com.finsight.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false) // Enforces type immutability at DB level
    private CategoryType type;

    @Column(nullable = false)
    private Boolean isGlobal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors


    public Category() {
    }

    public Category(String name, CategoryType type, Boolean isGlobal, User user) {
        this.name = name;
        this.type = type;
        this.isGlobal = isGlobal;
        this.user = user;
    }

    // Getters

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryType getType(){
        return type;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public User getUser() {
        return user;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // NO setter for type (immutability enforced)

}
