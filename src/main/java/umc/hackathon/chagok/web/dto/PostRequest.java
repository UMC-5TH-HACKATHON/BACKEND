package umc.hackathon.chagok.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostRequest {

    @NoArgsConstructor
    @Getter
    public static class CreatePostDTO{
        private String title;
        private String content;
        private Long category;
        private String tags;
    }
}
