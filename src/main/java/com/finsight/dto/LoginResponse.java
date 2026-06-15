package com.finsight.dto;

public class LoginResponse {
    private Integer id;
    private String name;
    private String email;

    // Constructors

    public LoginResponse(Integer id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
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
}
