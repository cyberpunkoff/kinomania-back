package ru.mirea.kinomania.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ru.mirea.kinomania.client.dto.KinopoiskFilm;
import ru.mirea.kinomania.client.dto.KinopoiskFilmsResponse;
import ru.mirea.kinomania.client.dto.KinopoiskFiltersResponse;
import ru.mirea.kinomania.client.dto.KinopoiskPremiereResponse;
import ru.mirea.kinomania.client.dto.KinopoiskSeasonsResponse;
import ru.mirea.kinomania.config.FeignConfig;

@FeignClient(name = "kinopoisk-api", url = "${kinopoisk.api.url}", configuration = FeignConfig.class)
public interface KinopoiskFeignClient {

    @GetMapping("/api/v2.2/films/{id}")
    KinopoiskFilm getFilmById(@PathVariable("id") Integer id);
    
    @GetMapping("/api/v2.2/films/{id}/seasons")
    KinopoiskSeasonsResponse getFilmSeasons(@PathVariable("id") Integer id);
    
    @GetMapping("/api/v2.2/films")
    KinopoiskFilmsResponse getFilms(
            @RequestParam(value = "countries", required = false) List<Integer> countries,
            @RequestParam(value = "genres", required = false) List<Integer> genres,
            @RequestParam(value = "order", required = false, defaultValue = "RATING") String order,
            @RequestParam(value = "type", required = false, defaultValue = "ALL") String type,
            @RequestParam(value = "ratingFrom", required = false, defaultValue = "0") Double ratingFrom,
            @RequestParam(value = "ratingTo", required = false, defaultValue = "10") Double ratingTo,
            @RequestParam(value = "yearFrom", required = false, defaultValue = "1000") Integer yearFrom,
            @RequestParam(value = "yearTo", required = false, defaultValue = "3000") Integer yearTo,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page);
    
    @GetMapping("/api/v2.2/films/premieres")
    KinopoiskPremiereResponse getFilmPremieres(
            @RequestParam("year") Integer year,
            @RequestParam("month") String month);
    
    @GetMapping("/api/v2.2/films/filters")
    KinopoiskFiltersResponse getFilmFilters();
}
