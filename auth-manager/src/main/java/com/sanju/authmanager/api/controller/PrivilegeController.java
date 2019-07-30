package com.sanju.authmanager.api.controller;

import com.sanju.authmanager.core.service.PrivilegeService;
import com.sanju.authmanager.dto.PrivilegeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @PostMapping
    public void addPrivilege(@RequestBody PrivilegeDto privilegeDto){

    }

    @PutMapping
    public void updatePrivilege(@RequestBody PrivilegeDto privilegeDto){

    }

    @GetMapping("{id}")
    public PrivilegeDto getPrivilege(long id){
        return null;
    }


    @GetMapping
    public List<PrivilegeDto> getPrivileges(){
        return null;
    }
}
