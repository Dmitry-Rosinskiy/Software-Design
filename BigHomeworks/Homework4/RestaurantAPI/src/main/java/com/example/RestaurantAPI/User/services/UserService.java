package com.example.RestaurantAPI.User.services;

import com.example.RestaurantAPI.User.repositories.UserRepository;
import com.example.RestaurantAPI.User.exceptions.*;
import com.example.RestaurantAPI.User.models.RestaurantUser;
import com.example.RestaurantAPI.User.models.Role;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final String EMAIL_REGEX = "^[\\w+-]+@([\\w+-]+\\.)+\\w{2,}$";
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<RestaurantUser> getUserByID(int userID) {
        return userRepository.findUserByID(userID);
    }

    public void checkName(String name) {
        Optional<RestaurantUser> result = userRepository.findUserByName(name);
        if (result.isPresent()) {
            throw new UserNameExistsException();
        }
        if (name.length() > 50) {
            throw new UserNameLengthException();
        }
    }

    public void checkEmail(String email) {
        Optional<RestaurantUser> result = userRepository.findUserByEmail(email);
        if (result.isPresent()) {
            throw new UserEmailExistsException();
        }
        if (email.length() > 100) {
            throw new UserEmailLengthException();
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new UserEmailValidationException();
        }
    }

    public void addNewUser(String name, String email, String password, Role role) {
        checkName(name);
        checkEmail(email);
        RestaurantUser user = new RestaurantUser(name, email, getHash(password), role);
        userRepository.save(user);
    }

    public RestaurantUser getUserByEmail(String email) {
        Optional<RestaurantUser> result = userRepository.findUserByEmail(email);
        return result.orElse(null);
    }

    public boolean checkEmailPassword(String email, String password) {
        Optional<RestaurantUser> result = userRepository.findUserByEmail(email);
        if (result.isEmpty()) {
            return false;
        }

        return result.get().getPasswordHash().equals(getHash(password));
    }

    private String getHash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Can't make string hash");
            return "";
        }
    }

    public void changeUserRole(int id, Role role) {
        Optional<RestaurantUser> user = userRepository.findUserByID(id);
        if (user.isPresent()) {
            user.get().setRole(role);
            userRepository.save(user.get());
        }
    }

    public boolean checkWithExistingPassword(int id, String oldPassword) {
        Optional<RestaurantUser> user = userRepository.findUserByID(id);
        if (user.isPresent()) {
            return user.get().getPasswordHash().equals(getHash(oldPassword));
        }
        return false;
    }

    public void changeUserPassword(int id, String newPassword) {
        Optional<RestaurantUser> user = userRepository.findUserByID(id);
        if (user.isPresent()) {
            user.get().setPasswordHash(getHash(newPassword));
            userRepository.save(user.get());
        }
    }
}
