package edu.miu.cs544.cloudgetway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    private Long id;
    private String userName;
    private String password;
    private Boolean active;
    private List<Roles> roles = new ArrayList<>();

}
