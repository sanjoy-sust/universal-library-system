package com.sanju.authmanager.dto;

import com.sanju.authmanager.core.enums.UserType;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String mobile;
    private String address;
    private UserType userType;
    private String username;
    private String password;
    private List<RoleDto> roles;
}
