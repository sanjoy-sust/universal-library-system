package com.sanju.authmanager.core.service;

import com.sanju.authmanager.core.repository.PrivilegeRepository;
import com.sanju.authmanager.core.util.DtoAndEntityBuilder;
import com.sanju.authmanager.dto.PrivilegeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private DtoAndEntityBuilder dtoAndEntityBuilder;

    public void add(PrivilegeDto privilegeDto){
        privilegeRepository.save(dtoAndEntityBuilder.privilegeDtoEntity(privilegeDto));
    }

    public void update( PrivilegeDto privilegeDto){
        privilegeRepository.save(dtoAndEntityBuilder.privilegeDtoEntity(privilegeDto));
    }


    public PrivilegeDto getPrivilege(long id){

        return dtoAndEntityBuilder.privilegeEntityToDto(privilegeRepository.getOne(id));
    }

    public List<PrivilegeDto> getPrivileges(){
        return privilegeRepository.findAll().stream().map(x->dtoAndEntityBuilder.privilegeEntityToDto(x)).collect(Collectors.toList());
    }
}
