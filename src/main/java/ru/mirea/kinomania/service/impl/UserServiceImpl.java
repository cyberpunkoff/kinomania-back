package ru.mirea.kinomania.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.mirea.kinomania.model.UserWatchedMedia;
import ru.mirea.kinomania.repository.UserWatchedMediaRepository;
import ru.mirea.kinomania.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserWatchedMediaRepository userWatchedMediaRepository;
    
    @Override
    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User not authenticated");
        }

        return authentication.getName();
    }
    
    @Override
    public List<String> getWatchedMediaIds() {
        String userId = getCurrentUserId();
        return userWatchedMediaRepository.findByUserId(userId)
                .stream()
                .map(UserWatchedMedia::getMediaId)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean markMediaAsWatched(String mediaId) {
        String userId = getCurrentUserId();
        
        if (userWatchedMediaRepository.findByUserIdAndMediaId(userId, mediaId).isPresent()) {
            return false;
        }
        
        UserWatchedMedia watchedMedia = UserWatchedMedia.builder()
                .userId(userId)
                .mediaId(mediaId)
                .build();
        
        userWatchedMediaRepository.save(watchedMedia);
        return true;
    }
    
    @Override
    @Transactional
    public boolean unmarkMediaAsWatched(String mediaId) {
        String userId = getCurrentUserId();
        
        if (userWatchedMediaRepository.findByUserIdAndMediaId(userId, mediaId).isEmpty()) {
            return false;
        }
        
        userWatchedMediaRepository.deleteByUserIdAndMediaId(userId, mediaId);
        return true;
    }

}