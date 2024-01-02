package umc.hackathon.chagok.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.hackathon.chagok.apiPayload.ApiResponse;
import umc.hackathon.chagok.service.PostService.PostService;
import umc.hackathon.chagok.web.dto.PostRequest;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ApiResponse createPost(@RequestHeader(name = "memberId") Long memberId, @RequestBody PostRequest.CreatePostDTO request){

        postService.createPost(memberId, request);

        return ApiResponse.onSuccess(null);
    }
}
