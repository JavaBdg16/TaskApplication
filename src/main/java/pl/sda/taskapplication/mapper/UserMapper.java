package pl.sda.taskapplication.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.taskapplication.dto.UserDto;
import pl.sda.taskapplication.entity.User;

public class UserMapper {

    public static User map(UserDto userDto, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCity(userDto.getCity());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }

    public static UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setCity(user.getCity());
        userDto.setStreet(user.getStreet());
        userDto.setZip(user.getZip());
        userDto.setPhoneNumber(user.getPhoneNumber());

        return userDto;
    }
}
