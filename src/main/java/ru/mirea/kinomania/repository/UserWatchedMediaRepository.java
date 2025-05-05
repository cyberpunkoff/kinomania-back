package ru.mirea.kinomania.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.mirea.kinomania.model.UserWatchedMedia;

@Repository
public interface UserWatchedMediaRepository extends JpaRepository<UserWatchedMedia, Long> {

    List<UserWatchedMedia> findByUserId(String userId);

    Optional<UserWatchedMedia> findByUserIdAndMediaId(String userId, String mediaId);
    
    long deleteByUserIdAndMediaId(String userId, String mediaId);

}
