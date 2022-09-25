package edu.miu.cs544.user.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Roles {
    @Id
    @GeneratedValue
    private Long id;
    private String role;

    public Roles() {
    }

    public Roles(String role) {
        this.role = role;
    }
}
