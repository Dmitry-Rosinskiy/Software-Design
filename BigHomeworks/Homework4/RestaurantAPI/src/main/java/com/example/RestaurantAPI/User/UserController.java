package com.example.RestaurantAPI.User;

import com.example.RestaurantAPI.User.models.*;
import com.example.RestaurantAPI.User.services.JwtService;
import com.example.RestaurantAPI.User.services.SessionService;
import com.example.RestaurantAPI.User.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
@Tag(name = "Пользователи", description = "Работа с пользователями")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final SessionService sessionService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService, SessionService sessionService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.sessionService = sessionService;
    }

    @PostMapping(path = "signup")
    @Operation(summary = "Регистрация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Указаны неверные данные")
    })
    public ResponseEntity<String> registerUser(@Parameter(description = "Имя") @RequestParam String name,
                                               @Parameter(description = "Электронная почта") @RequestParam String email,
                                               @Parameter(description = "Пароль") @RequestParam String password) {
        userService.addNewUser(name, email, password, Role.CUSTOMER);
        return new ResponseEntity<>("You are registered successfully!", HttpStatus.CREATED);
    }

    @PostMapping(path = "login")
    @Operation(summary = "Вход пользователя в систему")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно авторизован"),
            @ApiResponse(responseCode = "400", description = "Указаны неверные данные")
    })
    public ResponseEntity<String> authenticateUser(@Parameter(description = "Электронная почта") @RequestParam String email,
                                                   @Parameter(description = "Пароль") @RequestParam String password) {
        if (userService.checkEmailPassword(email, password)) {
            RestaurantUser user = userService.getUserByEmail(email);
            if (sessionService.hasSession(user.getID())) {
                return new ResponseEntity<>("You are already logged in", HttpStatus.BAD_REQUEST);
            }
            String token = jwtService.generateToken(user);
            sessionService.addNewSession(user.getID(), token);
            return new ResponseEntity<>("You logged in successfully!\nYour JWT: " + token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email or password is incorrect", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(path = "change-password")
    @Operation(summary = "Изменение пароля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пароль успешно изменён"),
            @ApiResponse(responseCode = "400", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    public ResponseEntity<String> changeUserPassword(@RequestBody RequestChangeUserPassword request) {
        if (!sessionService.isActiveSession(request.getToken())) {
            return new ResponseEntity<>("To change password, you need to be logged in", HttpStatus.UNAUTHORIZED);
        }
        Optional<Session> session = sessionService.getSessionByToken(request.getToken());
        Optional<RestaurantUser> user = userService.getUserByID(session.get().getUserID());
        if (!userService.checkWithExistingPassword(user.get().getID(), request.getOldPassword())) {
            return new ResponseEntity<>("Current password is written incorrectly", HttpStatus.BAD_REQUEST);
        }
        userService.changeUserPassword(user.get().getID(), request.getNewPassword());
        return new ResponseEntity<>("Your password was changed successfully!", HttpStatus.OK);
    }

    @PatchMapping(path = "change-role")
    @Operation(summary = "Изменение роли пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль указанного пользователя успешна изменена"),
            @ApiResponse(responseCode = "400", description = "Указанный пользователь не существует"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Пользователь не обладает необходимыми правами")
    })
    public ResponseEntity<String> changeUserRole(@RequestBody RequestChangeUserRole request) {
        if (!sessionService.isActiveSession(request.getToken())) {
            return new ResponseEntity<>("To change user role, you need to be logged in and have a " + Role.MANAGER + " role", HttpStatus.UNAUTHORIZED);
        }
        Optional<Session> session = sessionService.getSessionByToken(request.getToken());
        Optional<RestaurantUser> user = userService.getUserByID(session.get().getUserID());
        if (user.isPresent() && user.get().getRole() == Role.MANAGER) {
            if (!userService.getUserByID(request.getUserID()).isPresent()) {
                return new ResponseEntity<>("User with ID " + request.getUserID() + " does not exist", HttpStatus.BAD_REQUEST);
            }
            userService.changeUserRole(request.getUserID(), request.getRole());
            return new ResponseEntity<>("User role was changed successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("To change user role, you need to have a role " + Role.MANAGER, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "info")
    @Operation(summary = "Получение информации о пользователе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о пользователе предоставлена"),
            @ApiResponse(responseCode = "400", description = "Указанный пользователь не существует"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
    })
    public ResponseEntity<RestaurantUser> getUserInfo(@RequestBody RequestUserInfo request) {
        if (!sessionService.isActiveSession(request.getToken())) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Optional<Session> session = sessionService.getSessionByToken(request.getToken());
        Optional<RestaurantUser> userInfo = userService.getUserByID(request.getUserID());
        if (userInfo.isPresent()) {
            userInfo.get().setPasswordHash(null);
            Optional<RestaurantUser> user = userService.getUserByID(session.get().getUserID());
            if (userInfo.get().getID() == user.get().getID()) {
                return new ResponseEntity<>(userInfo.get(), HttpStatus.OK);
            }
            if (user.get().getRole() == Role.CHEF) {
                userInfo.get().setCreatedAt(null);
                userInfo.get().setUpdatedAt(null);
            }
            if (user.get().getRole() == Role.CUSTOMER) {
                userInfo.get().setRole(null);
                userInfo.get().setCreatedAt(null);
                userInfo.get().setUpdatedAt(null);
            }
            return new ResponseEntity<>(userInfo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
