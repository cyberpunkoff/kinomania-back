package ru.mirea.kinomania.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionRequest {
    
    @NotBlank(message = "Collection name cannot be empty")
    private String name;

}