package ru.practicum.mainserver.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.user.dto.UserDto;
import ru.practicum.mainserver.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<UserDto> findAll(@RequestParam(name = "ids") Long[] ids,
                                 @PositiveOrZero
                                 @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                 @Positive
                                 @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("findAll: {},{}", ids, from);
        return userService.getAllUsers(ids, from, size);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        log.info("createUser: {}", userDto);
        return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{userId}")
    public void deleteUser(@Valid @Positive @PathVariable Long userId) {
        log.info("deleteUser: {}", userId);
        userService.deleteUserById(userId);
    }
}
