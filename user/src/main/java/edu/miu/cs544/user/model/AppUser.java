package edu.miu.cs544.user.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;
    private Boolean active;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Roles> roles = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String userName, String password, Boolean active, List<Roles> roles) {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public void addRoles(Roles roles){
        this.roles.add(roles);
    }


}
