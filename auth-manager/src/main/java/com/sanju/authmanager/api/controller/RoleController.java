package com.sanju.authmanager.api.controller;

import com.sanju.authmanager.core.service.RoleService;
import com.sanju.authmanager.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public void create(@RequestBody RoleDto roleDto){

        roleService.add(roleDto);
    }

    @PutMapping
    public void update(@RequestBody RoleDto roleDto){
        roleService.update(roleDto);
    }

    @GetMapping("{id}")
    public RoleDto getOne(long id){
        return roleService.getOne(id);
    }


    @GetMapping
    public List<RoleDto> getAll(){
        return roleService.getAll();
    }
}
