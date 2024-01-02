package umc.hackathon.chagok.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class CategoryRequest {
    @Getter
    public static class CreateCategoryDto{
        String name;
    }
}
