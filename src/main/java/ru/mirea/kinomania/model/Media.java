package ru.mirea.kinomania.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    private String id;

    private String title;

    private String description;

    private String type;

    private List<String> genres;

    private Integer year;

    private Double rating;

    private String posterUrl;

    private String posterUrlPreview;

}
