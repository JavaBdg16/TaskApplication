package pl.sda.taskapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.taskapplication.dto.UserDto;
import pl.sda.taskapplication.entity.User;
import pl.sda.taskapplication.mapper.UserMapper;
import pl.sda.taskapplication.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(UserDto userDto) {
        User user = UserMapper.map(userDto, passwordEncoder);
        user = userRepository.save(user);

        return user.getId() != 0;
    }
}
