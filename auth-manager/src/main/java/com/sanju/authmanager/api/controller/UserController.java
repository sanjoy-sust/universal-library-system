package com.sanju.authmanager.api.controller;

import com.sanju.authmanager.api.validator.UserRequestValidator;
import com.sanju.authmanager.core.service.UserService;
import com.sanju.authmanager.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @InitBinder("userRequest")
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new UserRequestValidator());
    }

    @Autowired
    private UserService userService;

    @PostMapping
    public void create(@RequestBody UserDto dto){

        userService.add(dto);
    }

    @PutMapping
    public void update(@RequestBody UserDto dto){
        userService.update(dto);
    }

    @GetMapping("{id}")
    public UserDto getOne(long id){
        return userService.getOne(id);
    }


    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAll();
    }
}
