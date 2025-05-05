package ru.mirea.kinomania.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KinopoiskSeasonsResponse {

    @JsonProperty("total")
    private Integer total;
    
    @JsonProperty("items")
    private List<KinopoiskSeason> items;
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KinopoiskSeason {
        
        @JsonProperty("number")
        private Integer number;
        
        @JsonProperty("episodes")
        private List<KinopoiskEpisode> episodes;
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KinopoiskEpisode {
        
        @JsonProperty("seasonNumber")
        private Integer seasonNumber;
        
        @JsonProperty("episodeNumber")
        private Integer episodeNumber;
        
        @JsonProperty("nameRu")
        private String nameRu;
        
        @JsonProperty("nameEn")
        private String nameEn;
        
        @JsonProperty("synopsis")
        private String synopsis;
        
        @JsonProperty("releaseDate")
        private String releaseDate;
    }

}