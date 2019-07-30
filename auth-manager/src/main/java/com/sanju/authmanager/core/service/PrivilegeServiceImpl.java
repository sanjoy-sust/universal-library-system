package com.sanju.authmanager.core.service;

import com.sanju.authmanager.core.repository.PrivilegeRepository;
import com.sanju.authmanager.dto.PrivilegeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @PostMapping
    public void addPrivilege(@RequestBody PrivilegeDto privilegeDto){

    }

    @PutMapping
    public void updatePrivilege(@RequestBody PrivilegeDto privilegeDto){

    }

    @GetMapping("{id}")
    public void getPrivilege(long id){

    }


    @GetMapping
    public List<PrivilegeDto> getPrivileges(){
        return null;
    }
}
