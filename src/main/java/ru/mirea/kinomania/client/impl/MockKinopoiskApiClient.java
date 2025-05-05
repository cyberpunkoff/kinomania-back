package ru.mirea.kinomania.client.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.mirea.kinomania.client.KinopoiskApiClient;
import ru.mirea.kinomania.model.Media;

@Slf4j
@Component
@RequiredArgsConstructor
public class MockKinopoiskApiClient implements KinopoiskApiClient {

    private final RestTemplate restTemplate;
    
    @Override
    public List<Media> getAllMedia(String search, String type, List<String> genres, Integer yearFrom,
            Integer yearTo, Double ratingFrom, Double ratingTo) {
        // TODO: Implement actual API call
        log.info("Getting all media with filters: search={}, type={}, genres={}, yearFrom={}, yearTo={}, ratingFrom={}, ratingTo={}",
                search, type, genres, yearFrom, yearTo, ratingFrom, ratingTo);
        return List.of(); // Return empty list for now
    }
    
    @Override
    public Optional<Media> getMediaById(String id) {
        // TODO: Implement actual API call
        log.info("Getting media by id: {}", id);
        return Optional.empty(); // Return empty for now
    }
    
    @Override
    public List<Media> getPopularMedia() {
        // TODO: Implement actual API call
        log.info("Getting popular media");
        return List.of(); // Return empty list for now
    }
    
    @Override
    public List<Media> getNewMedia() {
        // TODO: Implement actual API call
        log.info("Getting new media");
        return List.of(); // Return empty list for now
    }

}
 