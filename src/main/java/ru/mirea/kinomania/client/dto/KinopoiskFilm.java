package ru.mirea.kinomania.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KinopoiskFilm {

    @JsonProperty("kinopoiskId")
    private Integer kinopoiskId;

    @JsonProperty("nameRu")
    private String nameRu;

    @JsonProperty("nameEn")
    private String nameEn;
    
    @JsonProperty("nameOriginal")
    private String nameOriginal;
    
    @JsonProperty("posterUrl")
    private String posterUrl;
    
    @JsonProperty("posterUrlPreview")
    private String posterUrlPreview;
    
    @JsonProperty("ratingKinopoisk")
    private Double ratingKinopoisk;
    
    @JsonProperty("ratingImdb")
    private Double ratingImdb;
    
    @JsonProperty("webUrl")
    private String webUrl;
    
    @JsonProperty("year")
    private Integer year;
    
    @JsonProperty("filmLength")
    private Integer filmLength;
    
    @JsonProperty("slogan")
    private String slogan;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("shortDescription")
    private String shortDescription;
    
    @JsonProperty("type")
    private String type;
    
    @JsonProperty("countries")
    private List<KinopoiskCountry> countries;
    
    @JsonProperty("genres")
    private List<KinopoiskGenre> genres;

}