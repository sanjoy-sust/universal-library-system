package com.sanju.authmanager.core.service;

import com.sanju.authmanager.dto.PrivilegeDto;

import java.util.List;

public interface PrivilegeService {
    void add(PrivilegeDto privilegeDto);
    void update( PrivilegeDto privilegeDto);
    PrivilegeDto getPrivilege(long id);
    List<PrivilegeDto> getPrivileges();
}
