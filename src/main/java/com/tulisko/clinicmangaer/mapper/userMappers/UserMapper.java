package com.tulisko.clinicmangaer.mapper.userMappers;

import com.tulisko.clinicmangaer.dto.userDto.UserDTO;
import com.tulisko.clinicmangaer.model.User;

import java.util.List;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getTelephone(),
                user.getRole().name(),
                user.isActive());
    }

    public static List<UserDTO> toDTOList(List<? extends User> users) {
        return users.stream().map(UserMapper::toDTO).toList();
    }
}