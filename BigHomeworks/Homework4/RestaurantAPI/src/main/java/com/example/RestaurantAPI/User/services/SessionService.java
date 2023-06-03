package com.example.RestaurantAPI.User.services;

import com.example.RestaurantAPI.User.repositories.SessionRepository;
import com.example.RestaurantAPI.User.models.Session;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final JwtService jwtService;

    @Autowired
    public SessionService(SessionRepository sessionRepository, JwtService jwtService) {
        this.sessionRepository = sessionRepository;
        this.jwtService = jwtService;
    }

    public void addNewSession(int userID, String sessionToken) {
        Claims claims = jwtService.extractAllClaims(sessionToken);
        Session session = new Session(userID, sessionToken, claims.getExpiration());
        sessionRepository.save(session);
    }

    public boolean hasSession(int userID) {
        Optional<Session> result = sessionRepository.findActiveSessionByUserID(userID);
        return result.isPresent();
    }

    public Optional<Session> getSessionByToken(String token) {
        return sessionRepository.findSessionByToken(token);
    }

    public boolean isActiveSession(String token) {
        Optional<Session> session = getSessionByToken(token);
        return session.isPresent() && session.get().getExpiresAt().compareTo(new Date(System.currentTimeMillis())) > 0;
    }
}
