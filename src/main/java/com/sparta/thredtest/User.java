package com.sparta.thredtest;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private String email;

    private String first;
    private String last;
    private String city;
    private String county;
    private int age;

    public User() {
    }

    public User(String email, String first, String last, String city, String county, int age) {
        this.email = email;
        this.first = first;
        this.last = last;
        this.city = city;
        this.county = county;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}