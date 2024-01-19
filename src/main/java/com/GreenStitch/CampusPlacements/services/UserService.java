package com.GreenStitch.CampusPlacements.services;

import com.GreenStitch.CampusPlacements.payloads.UserDto;

public interface UserService {
    public UserDto registerNewUser(UserDto userDto);
    public UserDto getUserDetails(String email);
}
