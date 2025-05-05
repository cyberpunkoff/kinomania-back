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
public class MediaIdRequest {
    
    @NotBlank(message = "Media ID cannot be empty")
    private String mediaId;

}
