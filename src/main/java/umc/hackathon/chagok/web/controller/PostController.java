package umc.hackathon.chagok.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.hackathon.chagok.apiPayload.ApiResponse;
import umc.hackathon.chagok.converter.PostConverter;
import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.service.PostService.PostService;
import umc.hackathon.chagok.web.dto.PostResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    @GetMapping("")
    public ApiResponse<PostResponse.PostPreviewListDTO> getPostList(){
        List<Post> postList = postService.getPostList();
        return ApiResponse.onSuccess(PostConverter.postPreviewListDTO(postList));
    }
}
