package umc.hackathon.chagok.web.dto;

import lombok.Builder;
import lombok.Getter;

public class KnowledgeResponse {

    @Builder
    @Getter
    public static class knowledgeViewResponseDTO {
        private String title;

        private String content;

        private String imgUrl;

    }
}
