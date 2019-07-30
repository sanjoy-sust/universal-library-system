package com.sanju.authmanager.core.service;

import com.sanju.authmanager.core.entity.RoleEntity;
import com.sanju.authmanager.dto.RoleDto;

import java.util.List;

public interface RoleService {
    void add(RoleDto dto);
    void update(RoleDto dto);
    RoleDto getOne(long id);
    List<RoleDto> getAll();
}
