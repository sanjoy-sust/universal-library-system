package com.sanju.authmanager.api.controller;

import com.sanju.authmanager.core.service.PrivilegeService;
import com.sanju.authmanager.dto.PrivilegeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("privilege")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @PostMapping
    public void addPrivilege(@RequestBody PrivilegeDto privilegeDto){
        privilegeService.add(privilegeDto);
    }

    @PutMapping
    public void updatePrivilege(@RequestBody PrivilegeDto privilegeDto){
        privilegeService.update(privilegeDto);
    }

    @GetMapping("{id}")
    public PrivilegeDto getPrivilege(long id){
        return privilegeService.getPrivilege(id);
    }


    @GetMapping
    public List<PrivilegeDto> getPrivileges(){
        return null;
    }
}
