package ru.mirea.kinomania.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.mirea.kinomania.dto.CollectionRequest;
import ru.mirea.kinomania.dto.MediaIdRequest;
import ru.mirea.kinomania.dto.ReorderItemsRequest;
import ru.mirea.kinomania.dto.SuccessResponse;
import ru.mirea.kinomania.model.Collection;
import ru.mirea.kinomania.service.CollectionService;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionController {
    
    private final CollectionService collectionService;

    @GetMapping("/hello")
    public String hello(Principal principal) {
        return "Hello, " + principal.getName() + "!";
    }

    @GetMapping
    public List<Collection> getAllCollections() {
        return collectionService.getAllCollections();
    }

    @GetMapping("/{id}")
    public Collection getCollectionById(@PathVariable String id) {
        return collectionService.getCollectionById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection createCollection(@Valid @RequestBody CollectionRequest request) {
        return collectionService.createCollection(request.getName());
    }

    @PutMapping("/{id}")
    public Collection updateCollection(
            @PathVariable String id, 
            @Valid @RequestBody CollectionRequest request) {
        return collectionService.updateCollection(id, request.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCollection(@PathVariable String id) {
        collectionService.deleteCollection(id);
    }

    @PostMapping("/{id}/items")
    public SuccessResponse addMediaToCollection(
            @PathVariable String id, 
            @Valid @RequestBody MediaIdRequest request) {
        boolean added = collectionService.addMediaToCollection(id, request.getMediaId());
        return SuccessResponse.of(added);
    }

    @DeleteMapping("/{id}/items/{mediaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMediaFromCollection(
            @PathVariable String id, 
            @PathVariable String mediaId) {
        collectionService.removeMediaFromCollection(id, mediaId);
    }

    @PutMapping("/{id}/items/reorder")
    public SuccessResponse reorderCollectionItems(
            @PathVariable String id, 
            @Valid @RequestBody ReorderItemsRequest request) {
        boolean reordered = collectionService.reorderCollectionItems(id, request.getItemIds());
        return SuccessResponse.of(reordered);
    }

}
