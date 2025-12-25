package com.amit.alumniManagement.dto;




import com.amit.alumniManagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
