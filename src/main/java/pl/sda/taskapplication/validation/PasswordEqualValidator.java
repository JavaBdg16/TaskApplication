package pl.sda.taskapplication.validation;

import pl.sda.taskapplication.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordEqualValidator
        implements ConstraintValidator<PasswordEqualConstraint, UserDto> {
    @Override
    public void initialize(PasswordEqualConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getRetypePassword().equals(userDto.getPassword());
    }
}
