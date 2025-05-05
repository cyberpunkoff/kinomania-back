package ru.mirea.kinomania.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.mirea.kinomania.client.KinopoiskApiClient;
import ru.mirea.kinomania.exception.ResourceNotFoundException;
import ru.mirea.kinomania.model.Media;
import ru.mirea.kinomania.service.MediaService;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    
    private final KinopoiskApiClient mediaApiClient;
    
    @Override
    public List<Media> getAllMedia(String search, String type, List<String> genres, Integer yearFrom,
            Integer yearTo, Double ratingFrom, Double ratingTo) {
        return mediaApiClient.getAllMedia(search, type, genres, yearFrom, yearTo, ratingFrom, ratingTo);
    }
    
    @Override
    public Media getMediaById(String id) {
        return mediaApiClient.getMediaById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media", id));
    }
    
    @Override
    public List<Media> getPopularMedia() {
        return mediaApiClient.getPopularMedia();
    }
    
    @Override
    public List<Media> getNewMedia() {
        return mediaApiClient.getNewMedia();
    }

    @Override
    public List<Media> getMediaByIds(List<String> ids) {
        return ids.stream()
                .map(id -> mediaApiClient.getMediaById(id)
                        .orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }

}
 