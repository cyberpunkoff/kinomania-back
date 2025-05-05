package ru.mirea.kinomania.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KinopoiskPremiereResponse {

    @JsonProperty("total")
    private Integer total;
    
    @JsonProperty("items")
    private List<KinopoiskPremiere> items;
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KinopoiskPremiere {
        
        @JsonProperty("kinopoiskId")
        private Integer kinopoiskId;
        
        @JsonProperty("nameRu")
        private String nameRu;
        
        @JsonProperty("nameEn")
        private String nameEn;
        
        @JsonProperty("year")
        private Integer year;
        
        @JsonProperty("posterUrl")
        private String posterUrl;
        
        @JsonProperty("posterUrlPreview")
        private String posterUrlPreview;
        
        @JsonProperty("countries")
        private List<KinopoiskCountry> countries;
        
        @JsonProperty("genres")
        private List<KinopoiskGenre> genres;
        
        @JsonProperty("duration")
        private Integer duration;
        
        @JsonProperty("premiereRu")
        private String premiereRu;
    }

}