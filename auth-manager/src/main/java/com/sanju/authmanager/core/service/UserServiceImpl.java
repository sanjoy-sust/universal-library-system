package com.sanju.authmanager.core.service;

import com.sanju.authmanager.core.entity.UserEntity;
import com.sanju.authmanager.core.enums.Status;
import com.sanju.authmanager.core.repository.UserRepository;
import com.sanju.authmanager.core.util.DtoAndEntityBuilder;
import com.sanju.authmanager.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DtoAndEntityBuilder dtoAndEntityBuilder;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void add(UserDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserEntity userEntity = dtoAndEntityBuilder.userDtoToEntity(dto);
        userEntity.setStatus(Status.ACTIVE);
        userRepository.save(userEntity);
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
    @Transactional
    public List<UserDto> getAll() {
        List<UserDto> collect = userRepository.findAll().stream().map(x -> dtoAndEntityBuilder.userEntityToDto(x)).collect(Collectors.toList());

        return collect;
    }
}
