package com.GreenStitch.CampusPlacements.services;

import com.GreenStitch.CampusPlacements.entities.User;
import com.GreenStitch.CampusPlacements.exceptions.UserException;
import com.GreenStitch.CampusPlacements.payloads.UserDto;
import com.GreenStitch.CampusPlacements.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = mapUserDtoToUser(userDto);
        user = userRepository.save(user);
        return mapUserToUserDto(user);
    }
    @Override
    public UserDto getUserDetails(String email) {
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new UserException("User not found with email: " + email));
        return mapUserToUserDto(user);
    }


    private User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

    private UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
