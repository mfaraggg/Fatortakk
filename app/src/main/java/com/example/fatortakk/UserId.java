package com.example.fatortakk;

public class UserId {
    private int id;
    private String phone;

    private String password;
    private String name;
    private String email;

    public UserId(int id, String phone, String name, String email, String password) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) { this.email = password; }
}

