package com.sanju.authmanager.core.service;

import com.sanju.authmanager.dto.RoleDto;
import com.sanju.authmanager.dto.UserDto;

import java.util.List;

public interface UserService {
    void add(UserDto dto);
    void update(UserDto dto);
    UserDto getOne(long id);
    List<UserDto> getAll();
}
