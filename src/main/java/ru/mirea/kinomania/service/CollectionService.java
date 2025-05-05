package ru.mirea.kinomania.service;

import java.util.List;

import ru.mirea.kinomania.model.Collection;

public interface CollectionService {

    List<Collection> getAllCollections();

    Collection getCollectionById(String id);
 
    Collection createCollection(String name);

    Collection updateCollection(String id, String name);

    void deleteCollection(String id);

    boolean addMediaToCollection(String collectionId, String mediaId);

    void removeMediaFromCollection(String collectionId, String mediaId);

    boolean reorderCollectionItems(String collectionId, List<String> itemIds);

}
