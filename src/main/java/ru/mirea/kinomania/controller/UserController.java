package ru.mirea.kinomania.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.mirea.kinomania.dto.SuccessResponse;
import ru.mirea.kinomania.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @GetMapping("/watched")
    public List<String> getWatchedMediaIds() {
        return userService.getWatchedMediaIds();
    }

    @PostMapping("/watched/{mediaId}")
    public SuccessResponse markMediaAsWatched(@PathVariable String mediaId) {
        boolean marked = userService.markMediaAsWatched(mediaId);
        return SuccessResponse.of(marked);
    }

    @DeleteMapping("/watched/{mediaId}")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse unmarkMediaAsWatched(@PathVariable String mediaId) {
        boolean unmarked = userService.unmarkMediaAsWatched(mediaId);
        return SuccessResponse.of(unmarked);
    }

}
