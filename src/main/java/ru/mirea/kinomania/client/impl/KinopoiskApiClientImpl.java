package ru.mirea.kinomania.client.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.mirea.kinomania.client.KinopoiskApiClient;
import ru.mirea.kinomania.client.KinopoiskFeignClient;
import ru.mirea.kinomania.client.dto.KinopoiskFilm;
import ru.mirea.kinomania.client.dto.KinopoiskFilmsResponse;
import ru.mirea.kinomania.client.dto.KinopoiskFiltersResponse;
import ru.mirea.kinomania.model.Media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@Primary
@RequiredArgsConstructor
public class KinopoiskApiClientImpl implements KinopoiskApiClient {

    private final KinopoiskFeignClient kinopoiskFeignClient;

    @Override
    public List<Media> getAllMedia(String search, String type, List<String> genres, Integer yearFrom, Integer yearTo,
            Double ratingFrom, Double ratingTo) {
        try {
            // Конвертация жанров из строк в ID
            List<Integer> genreIds = Collections.emptyList();
            if (genres != null && !genres.isEmpty()) {
                KinopoiskFiltersResponse filters = kinopoiskFeignClient.getFilmFilters();
                genreIds = genres.stream()
                        .map(genre -> filters.getGenres().stream()
                                .filter(g -> g.getGenre() != null && g.getGenre().equalsIgnoreCase(genre))
                                .map(KinopoiskFiltersResponse.KinopoiskFilterItem::getId)
                                .findFirst()
                                .orElse(null))
                        .filter(id -> id != null)
                        .collect(Collectors.toList());
            }
            
            KinopoiskFilmsResponse response = kinopoiskFeignClient.getFilms(
                    null, // countries
                    genreIds.isEmpty() ? null : genreIds,
                    "RATING", // order
                    type, // type (ALL, FILM, TV_SERIES)
                    ratingFrom,
                    ratingTo,
                    yearFrom,
                    yearTo,
                    search,
                    1); // get only first page
            
            return mapFilmsToMedia(response.getItems());
        } catch (FeignException e) {
            log.error("Error fetching films from Kinopoisk API", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Media> getMediaById(String id) {
        try {
            KinopoiskFilm film = kinopoiskFeignClient.getFilmById(Integer.parseInt(id));
            return Optional.of(mapFilmToMedia(film));
        } catch (FeignException e) {
            log.error("Error fetching film from Kinopoisk API", e);
            return Optional.empty();
        } catch (NumberFormatException e) {
            log.error("Invalid film ID: {}", id, e);
            return Optional.empty();
        }
    }

    @Override
    public List<Media> getPopularMedia() {
        try {
            KinopoiskFilmsResponse response = kinopoiskFeignClient.getFilms(
                    null,
                    null,
                    "NUM_VOTE",
                    "ALL",
                    8.0,
                    null,
                    null,
                    null,
                    null,
                    1);
            
            return mapFilmsToMedia(response.getItems());
        } catch (FeignException e) {
            log.error("Error fetching popular films from Kinopoisk API", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Media> getNewMedia() {
        try {
            int currentYear = java.time.Year.now().getValue();
            
            KinopoiskFilmsResponse response = kinopoiskFeignClient.getFilms(
                    null,
                    null,
                    "YEAR",
                    "ALL",
                    null,
                    null,
                    currentYear - 1,
                    currentYear,
                    null,
                    1); // get only first page
            
            return mapFilmsToMedia(response.getItems());
        } catch (FeignException e) {
            log.error("Error fetching new films from Kinopoisk API", e);
            return new ArrayList<>();
        }
    }
    
    private List<Media> mapFilmsToMedia(List<KinopoiskFilm> films) {
        if (films == null) {
            return new ArrayList<>();
        }
        
        return films.stream()
                .map(this::mapFilmToMedia)
                .collect(Collectors.toList());
    }
    
    private Media mapFilmToMedia(KinopoiskFilm film) {
        String type = "movie";
        if (film.getType() != null) {
            if ("TV_SERIES".equals(film.getType()) || "MINI_SERIES".equals(film.getType()) || "TV_SHOW".equals(film.getType())) {
                type = "series";
            }
        }
        
        List<String> genres = Collections.emptyList();
        if (film.getGenres() != null) {
            genres = film.getGenres().stream()
                    .map(g -> g.getGenre())
                    .collect(Collectors.toList());
        }
        
        return Media.builder()
                .id(String.valueOf(film.getKinopoiskId()))
                .title(film.getNameRu() != null ? film.getNameRu() : 
                      (film.getNameEn() != null ? film.getNameEn() : film.getNameOriginal()))
                .type(type)
                .description(film.getDescription())
                .posterUrl(film.getPosterUrl())
                .posterUrlPreview(film.getPosterUrlPreview())
                .genres(genres)
                .year(film.getYear())
                .rating(film.getRatingKinopoisk() != null ? film.getRatingKinopoisk() : 
                       (film.getRatingImdb() != null ? film.getRatingImdb() : 0.0))
                .build();
    }

}
