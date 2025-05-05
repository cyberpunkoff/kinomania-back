package ru.mirea.kinomania.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.mirea.kinomania.exception.ResourceNotFoundException;
import ru.mirea.kinomania.model.Collection;
import ru.mirea.kinomania.repository.CollectionRepository;
import ru.mirea.kinomania.service.CollectionService;
import ru.mirea.kinomania.service.UserService;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final static List<String > DEMO_COLLECTION_MOVIES = List.of("301", "1091", "403");

    private final CollectionRepository collectionRepository;
    private final UserService userService;
    
    @Override
    @Transactional
    public List<Collection> getAllCollections() {
        String userId = userService.getCurrentUserId();
        var collections = collectionRepository.findByUserId(userId);

        if (collections.isEmpty()) {
            return List.of(createDemoCollection(userId));
        }

        return collections;
    }
    
    @Override
    public Collection getCollectionById(String id) {
        String userId = userService.getCurrentUserId();
        return collectionRepository.findById(id)
                .filter(collection -> collection.getUserId().equals(userId))
                .orElseThrow(() -> new ResourceNotFoundException("Collection", id));
    }
    
    @Override
    @Transactional
    public Collection createCollection(String name) {
        String userId = userService.getCurrentUserId();
        
        Collection collection = Collection.builder()
                .name(name)
                .userId(userId)
                .items(new ArrayList<>())
                .build();
        
        return collectionRepository.save(collection);
    }
    
    @Override
    @Transactional
    public Collection updateCollection(String id, String name) {
        Collection collection = getCollectionById(id);
        collection.setName(name);
        return collectionRepository.save(collection);
    }
    
    @Override
    @Transactional
    public void deleteCollection(String id) {
        Collection collection = getCollectionById(id);
        collectionRepository.delete(collection);
    }
    
    @Override
    @Transactional
    public boolean addMediaToCollection(String collectionId, String mediaId) {
        Collection collection = getCollectionById(collectionId);
        
        if (collection.getItems().contains(mediaId)) {
            return false;
        }
        
        collection.getItems().add(mediaId);
        collectionRepository.save(collection);
        return true;
    }
    
    @Override
    @Transactional
    public void removeMediaFromCollection(String collectionId, String mediaId) {
        Collection collection = getCollectionById(collectionId);
        collection.getItems().remove(mediaId);
        collectionRepository.save(collection);
    }
    
    @Override
    @Transactional
    public boolean reorderCollectionItems(String collectionId, List<String> itemIds) {
        Collection collection = getCollectionById(collectionId);
        
        // Check if the new list contains exactly the same items
        if (collection.getItems().size() != itemIds.size() || 
                !collection.getItems().containsAll(itemIds) || 
                !itemIds.containsAll(collection.getItems())) {
            return false;
        }
        
        collection.setItems(new ArrayList<>(itemIds));
        collectionRepository.save(collection);
        return true;
    }

    private Collection createDemoCollection(String userId) {
        Collection collection = Collection.builder()
                .name("Демо-коллекция")
                .userId(userId)
                .items(DEMO_COLLECTION_MOVIES)
                .build();

        return collectionRepository.save(collection);
    }

}
