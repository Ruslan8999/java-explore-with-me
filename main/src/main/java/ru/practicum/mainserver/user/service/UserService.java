package ru.practicum.mainserver.user.service;

import ru.practicum.mainserver.user.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers(Long[] ids, Integer from, Integer size);

    UserDto createUser(UserDto userDto);

    void deleteUserById(Long userId);
}
