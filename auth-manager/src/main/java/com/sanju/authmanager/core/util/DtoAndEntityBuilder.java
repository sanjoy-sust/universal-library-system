package com.sanju.authmanager.core.util;

import com.sanju.authmanager.core.entity.PrivilegeEntity;
import com.sanju.authmanager.core.entity.RoleEntity;
import com.sanju.authmanager.core.entity.UserEntity;
import com.sanju.authmanager.dto.PrivilegeDto;
import com.sanju.authmanager.dto.RoleDto;
import com.sanju.authmanager.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DtoAndEntityBuilder {

    public UserEntity userDtoToEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setMobile(userDto.getMobile());
        userEntity.setAddress(userDto.getAddress());
        userEntity.setUserType(userDto.getUserType());
        userEntity.setRoles(userDto.getRoles().stream()
                .map(x -> roleDtoToEntity(x)).collect(Collectors.toList()));

        return userEntity;
    }


    public UserDto userEntityToDto(UserEntity entity) {
        UserDto dto = new UserDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setMobile(entity.getMobile());
        dto.setAddress(entity.getAddress());
        dto.setUserType(entity.getUserType());
        dto.setRoles(entity.getRoles().stream()
                .map(x -> roleEntityToDto(x)).collect(Collectors.toList()));

        return dto;
    }

    public RoleDto roleEntityToDto(RoleEntity entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrivileges(entity.getPrivileges().stream()
                .map(x -> privilegeEntityToDto(x)).collect(Collectors.toList()));
        return dto;
    }


    public RoleEntity roleDtoToEntity(RoleDto dto) {
        RoleEntity entity = new RoleEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrivileges(dto.getPrivileges().stream()
                .map(x -> privilegeDtoEntity(x)).collect(Collectors.toList()));
        return entity;
    }


    public PrivilegeDto privilegeEntityToDto(PrivilegeEntity entity) {
        PrivilegeDto dto = new PrivilegeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }


    public PrivilegeEntity privilegeDtoEntity(PrivilegeDto dto) {
        PrivilegeEntity entity = new PrivilegeEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
