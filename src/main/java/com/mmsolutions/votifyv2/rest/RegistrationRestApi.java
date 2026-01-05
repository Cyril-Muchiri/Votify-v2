package com.mmsolutions.votifyv2.rest;

import com.mmsolutions.votifyv2.dto.UserRequestDTO;
import com.mmsolutions.votifyv2.entity.AppUser;
import com.mmsolutions.votifyv2.mapper.AppUserMapper;
import com.mmsolutions.votifyv2.service.UserCreationService;
import jakarta.persistence.PersistenceContext;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistrationRestApi {


    @Autowired
    UserCreationService userCreationService;


    @PostMapping("/createuser")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userCreationService.createUser(userRequestDTO));
    }

}
