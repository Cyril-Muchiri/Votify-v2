package com.mmsolutions.votifyv2.mapper;

import com.mmsolutions.votifyv2.dto.UserRequestDTO;
import com.mmsolutions.votifyv2.entity.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper{
    public UserRequestDTO mapToDto(AppUser appUser) {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername(appUser.getUsername());
        userRequestDTO.setPassword(appUser.getPassword());
        userRequestDTO.setEmail(appUser.getEmail());
        userRequestDTO.setFirstName(appUser.getFirstName());
        userRequestDTO.setLastName(appUser.getLastName());
        return userRequestDTO;
    }


    public AppUser mapToEntity(UserRequestDTO userRequestDTO) {
        AppUser appUser = new AppUser();
        appUser.setUsername(userRequestDTO.getUsername());
        appUser.setPassword(userRequestDTO.getPassword());
        appUser.setEmail(userRequestDTO.getEmail());
        appUser.setFirstName(userRequestDTO.getFirstName());
        appUser.setLastName(userRequestDTO.getLastName());
        return appUser;
    }
}
