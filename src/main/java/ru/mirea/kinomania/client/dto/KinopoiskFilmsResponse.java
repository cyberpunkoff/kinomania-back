package ru.mirea.kinomania.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KinopoiskFilmsResponse {

    @JsonProperty("items")
    private List<KinopoiskFilm> items;
    
    @JsonProperty("total")
    private Integer total;
    
    @JsonProperty("totalPages")
    private Integer totalPages;

}
