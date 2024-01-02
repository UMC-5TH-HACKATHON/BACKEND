package umc.hackathon.chagok.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.hackathon.chagok.apiPayload.ApiResponse;
import umc.hackathon.chagok.service.PostService.PostService;
import umc.hackathon.chagok.web.dto.PostRequest;

import javax.validation.constraints.Null;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // TIL 생성하기
    @PostMapping("")
    public ApiResponse<Null> createPost(@RequestHeader(name = "memberId") Long memberId, @RequestBody PostRequest.CreatePostDTO request){

        postService.createPost(memberId, request);

        return ApiResponse.onSuccess(null);
    }

    // TIL 수정하기
    @PutMapping("/{postId}")
    public ApiResponse<Null> updatePost(@PathVariable(name = "postId")Long postId, @RequestBody PostRequest.UpdatePostDTO request){

        postService.updatePost(postId, request);

        return ApiResponse.onSuccess(null);
    }
}
