package com.example.RestaurantAPI.User.repositories;

import com.example.RestaurantAPI.User.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    @Query("SELECT s FROM Session s WHERE s.userID = ?1 AND s.expiresAt > CURRENT_TIMESTAMP")
    Optional<Session> findActiveSessionByUserID(int userID);

    @Query("SELECT s FROM Session s WHERE s.sessionToken = ?1")
    Optional<Session> findSessionByToken(String token);
}
