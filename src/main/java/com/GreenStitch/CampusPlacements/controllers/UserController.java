package com.GreenStitch.CampusPlacements.controllers;

import com.GreenStitch.CampusPlacements.payloads.UserDto;
import com.GreenStitch.CampusPlacements.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> registerNewStudentHandler(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.registerNewUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/signIn")
    public ResponseEntity<String> signInUserHandler(Authentication auth) {
        return new ResponseEntity<>(userService.getUserDetails(auth.getName()).getUsername()
                + " logged in successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloUserHandler() {
        return new ResponseEntity<>("Hello from GreenStitch", HttpStatus.OK);
    }
}
