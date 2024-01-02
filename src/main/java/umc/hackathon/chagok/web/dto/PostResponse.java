package umc.hackathon.chagok.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.entity.Tag;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PostResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPostPreviewListDTO{
        List<MyPostPreviewDTO> myPostPreviewList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPostPreviewDTO{
        String title;
        String content;
        Long categoryId;
        List<Tag> tagList;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostPreviewListDTO{
        List<PostPreviewDTO> postPreviewList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostPreviewDTO{
        String ownerNickName;
        String title;
        String content;
        Long categoryId;
        List<Tag> tagList;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostContentDTO{
        String title;
        String content;
        Long categoryId;
        List<Tag> tagList;
        LocalDateTime createdAt;
    }
}
