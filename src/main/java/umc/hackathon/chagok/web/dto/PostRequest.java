package umc.hackathon.chagok.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostRequest {

    @NoArgsConstructor
    @Getter
    public static class CreatePostDTO{
        @Schema(defaultValue = "TIL 테스트 제목")
        private String title;
        @Schema(defaultValue = "TIL 테스트 본문")
        private String content;
        @Schema(defaultValue = "2")
        private Long category;
        @Schema(defaultValue = "#스프링부트#스웨거")
        private String tags;
    }

    @NoArgsConstructor
    @Getter
    public static class UpdatePostDTO{
        @Schema(defaultValue = "TIL 수정 제목")
        private String title;
        @Schema(defaultValue = "TIL 수정 본문")
        private String content;
        @Schema(defaultValue = "4")
        private Long category;
        @Schema(defaultValue = "#수정#에러")
        private String tags;
    }
}
