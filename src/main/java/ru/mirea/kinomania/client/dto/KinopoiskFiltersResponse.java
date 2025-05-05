package ru.mirea.kinomania.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KinopoiskFiltersResponse {

    @JsonProperty("genres")
    private List<KinopoiskFilterItem> genres;
    
    @JsonProperty("countries")
    private List<KinopoiskFilterItem> countries;
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KinopoiskFilterItem {
        
        @JsonProperty("id")
        private Integer id;
        
        @JsonProperty("genre")
        private String genre;
        
        @JsonProperty("country")
        private String country;
    }

}