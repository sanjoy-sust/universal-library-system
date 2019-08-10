package com.sanju.authmanager.api.validator;

import com.sanju.authmanager.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

import static com.sanju.authmanager.util.Constants.VALID_EMAIL_ADDRESS_REGEX;

public class UserRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserDto userDto = (UserDto) target;
        if (userDto.getName() == null || userDto.getName().isEmpty()) {
            errors.reject("user.name", RequestValidationMessage.NAME_EMPTY);
        }
        if (userDto.getMobile() == null || userDto.getMobile().isEmpty()) {
            errors.reject("author.email", RequestValidationMessage.USER_MOBILE_EMPTY);
        }
        if (!(VALID_EMAIL_ADDRESS_REGEX.matcher(userDto.getEmail()).find())) {
            errors.reject("author.email", RequestValidationMessage.USER_EMAIL_INCORRECT);

        }
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            errors.reject("author.mobile", RequestValidationMessage.USER_EMAIL_EMPTY);
        }
    }
}