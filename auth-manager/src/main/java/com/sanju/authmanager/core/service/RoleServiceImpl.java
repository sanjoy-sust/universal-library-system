package com.sanju.authmanager.core.service;

import com.sanju.authmanager.core.repository.RoleRepository;
import com.sanju.authmanager.core.repository.UserRepository;
import com.sanju.authmanager.core.util.DtoAndEntityBuilder;
import com.sanju.authmanager.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DtoAndEntityBuilder dtoAndEntityBuilder;

    @Override
    public void add(RoleDto dto) {
        roleRepository.save(dtoAndEntityBuilder.roleDtoToEntity(dto));
    }

    @Override
    public void update(RoleDto dto) {
        roleRepository.save(dtoAndEntityBuilder.roleDtoToEntity(dto));
    }

    @Override
    public RoleDto getOne(long id) {
        return dtoAndEntityBuilder.roleEntityToDto(roleRepository.getOne(id));
    }

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream().map(x->dtoAndEntityBuilder.roleEntityToDto(x)).collect(Collectors.toList());
    }
}
