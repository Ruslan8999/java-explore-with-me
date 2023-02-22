package ru.practicum.mainserver.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.mainserver.user.dto.UserDto;
import ru.practicum.mainserver.user.mapper.UserMapper;
import ru.practicum.mainserver.user.model.User;
import ru.practicum.mainserver.user.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers(Long[] ids, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        if (ids == null) {
            return userRepository.findAll(pageable).stream()
                    .map(UserMapper::toUserDto)
                    .collect(Collectors.toList());
        } else {
            return userRepository.findUsersByIdIn(List.of(ids), pageable).stream()
                    .map(UserMapper::toUserDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.toUser(new User(), userDto);
        User createdUser = userRepository.save(user);
        log.info("User has been created" + createdUser);
        return UserMapper.toUserDto(createdUser);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteAllById(Collections.singleton(userId));
    }
}
