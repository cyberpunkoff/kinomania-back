package ru.mirea.kinomania.client;

import java.util.List;
import java.util.Optional;

import ru.mirea.kinomania.model.Media;

public interface KinopoiskApiClient {

    List<Media> getAllMedia(
            String search,
            String type, 
            List<String> genres,
            Integer yearFrom,
            Integer yearTo,
            Double ratingFrom,
            Double ratingTo);

    Optional<Media> getMediaById(String id);

    List<Media> getPopularMedia();

    List<Media> getNewMedia();

}
