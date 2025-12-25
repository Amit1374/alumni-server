package com.amit.alumniManagement.dto;



import com.amit.alumniManagement.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}

