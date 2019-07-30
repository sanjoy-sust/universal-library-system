package com.sanju.authmanager.core.service;

import com.sanju.authmanager.core.repository.UserRepository;
import com.sanju.authmanager.core.util.DtoAndEntityBuilder;
import com.sanju.authmanager.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DtoAndEntityBuilder dtoAndEntityBuilder;

    @Override
    public void add(UserDto dto) {
            userRepository.save(dtoAndEntityBuilder.userDtoToEntity(dto));
    }

    @Override
    public void update(UserDto dto) {
        userRepository.save(dtoAndEntityBuilder.userDtoToEntity(dto));
    }

    @Override
    public UserDto getOne(long id) {
        return dtoAndEntityBuilder.userEntityToDto(userRepository.getOne(id));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(x-> dtoAndEntityBuilder.userEntityToDto(x)).collect(Collectors.toList());
    }
}
