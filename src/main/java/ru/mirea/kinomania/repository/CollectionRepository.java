package ru.mirea.kinomania.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.mirea.kinomania.model.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, String> {

    List<Collection> findByUserId(String userId);

}
 