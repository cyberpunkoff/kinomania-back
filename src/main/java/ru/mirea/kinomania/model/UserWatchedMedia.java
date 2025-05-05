package ru.mirea.kinomania.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_watched_media", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "mediaId"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWatchedMedia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userId;
    
    private String mediaId;

}
