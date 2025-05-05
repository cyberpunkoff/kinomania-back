package ru.mirea.kinomania.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse {
    private boolean success;
    
    public static SuccessResponse of(boolean success) {
        return SuccessResponse.builder()
                .success(success)
                .build();
    }

}
