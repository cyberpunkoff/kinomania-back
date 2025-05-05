package ru.mirea.kinomania.service;

import java.util.List;

public interface UserService {

    String getCurrentUserId();
    
    List<String> getWatchedMediaIds();

    boolean markMediaAsWatched(String mediaId);

    boolean unmarkMediaAsWatched(String mediaId);

}
