package com.mmsolutions.votifyv2.service;


import com.mmsolutions.votifyv2.dto.UserRequestDTO;
import com.mmsolutions.votifyv2.entity.AppUser;
import com.mmsolutions.votifyv2.mapper.AppUserMapper;
import com.mmsolutions.votifyv2.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreationService {

    AppUserRepository appUserRepository;
    AppUserMapper appUserMapper;
    PasswordEncoder passwordEncoder;

    public boolean checkUserExists(AppUser user){
       return appUserRepository.findByEmail(user.getEmail()).isPresent();
    }

    public String createUser(UserRequestDTO userDto){
        System.out.printf("Thiss is the user"+userDto.toString());
       AppUser appUser= appUserMapper.mapToEntity(userDto);

        if(checkUserExists(appUser)){
            System.out.printf(appUser.getEmail()+"from db");
            return "User already exists!!";
        }
        System.out.printf("Creating user %s\n", userDto.getEmail());
        appUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        appUserRepository.save(appUser);
        return "User created successfully!!!";
    }

}
