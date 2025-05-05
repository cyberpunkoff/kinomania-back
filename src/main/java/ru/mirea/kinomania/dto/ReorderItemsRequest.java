package ru.mirea.kinomania.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReorderItemsRequest {

    @NotEmpty(message = "Item IDs list cannot be empty")
    private List<String> itemIds;

}
