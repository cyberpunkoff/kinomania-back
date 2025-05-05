package ru.mirea.kinomania.service;

import java.util.List;

import ru.mirea.kinomania.model.Media;

public interface MediaService {

    List<Media> getAllMedia(
            String search,
            String type, 
            List<String> genres,
            Integer yearFrom,
            Integer yearTo,
            Double ratingFrom,
            Double ratingTo);
    
    Media getMediaById(String id);

    List<Media> getPopularMedia();

    List<Media> getNewMedia();

    List<Media> getMediaByIds(List<String> ids);

}
