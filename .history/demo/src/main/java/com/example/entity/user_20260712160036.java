package com.example.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private int age;
    @Column (name = "created_at")
    private LocalDateTime createdAt;

    public User(){

    }

    public User(String name, String email, int age, LocalDateTime createdAt){
        this.age = age;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int newAge){
        this.age = newAge;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime newCreatedAt){
        this.createdAt = newCreatedAt;
    }

    @Override
    public String toString() {
        return (getId() + " | " + getName() + " | " + getAge() + " | " + getEmail() + " | " + getCreatedAt());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        User otherUser = (User) obj;
        return (Objects.equals(this.id, otherUser.getId()) 
        && Objects.equals(this.name, otherUser.getName()) 
        && Objects.equals(this.email, otherUser.getEmail())
        && Objects.equals(this.age, otherUser.getAge())
        && Objects.equals(this.createdAt, otherUser.getCreatedAt()));
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, email, age, createdAt);
    }
}
