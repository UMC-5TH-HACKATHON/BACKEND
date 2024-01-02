package umc.hackathon.chagok.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CategoryRequest {
    @Getter
    public static class CreateCategoryDto{
        @Schema(defaultValue = "테스트 카테고리")
        String name;
    }
}
