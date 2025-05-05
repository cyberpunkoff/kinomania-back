package ru.mirea.kinomania.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.mirea.kinomania.model.Media;
import ru.mirea.kinomania.service.MediaService;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {
    
    private final MediaService mediaService;

    @GetMapping
    public List<Media> getAllMedia(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) List<String> genres,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo,
            @RequestParam(required = false) Double ratingFrom,
            @RequestParam(required = false) Double ratingTo) {
        return mediaService.getAllMedia(search, type, genres, yearFrom, yearTo, ratingFrom, ratingTo);
    }

    @GetMapping("/{id}")
    public Media getMediaById(@PathVariable String id) {
        return mediaService.getMediaById(id);
    }

    @GetMapping("/batch")
    public List<Media> getMediaById(@RequestParam List<String> ids) {
        return mediaService.getMediaByIds(ids);
    }

    @GetMapping("/popular")
    public List<Media> getPopularMedia() {
        return mediaService.getPopularMedia();
    }

    @GetMapping("/new")
    public List<Media> getNewMedia() {
        return mediaService.getNewMedia();
    }

}
