package umc.hackathon.chagok.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.hackathon.chagok.apiPayload.ApiResponse;
import umc.hackathon.chagok.service.PostService.PostService;
import umc.hackathon.chagok.web.dto.PostRequest;
import umc.hackathon.chagok.converter.PostConverter;
import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.web.dto.PostResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ApiResponse createPost(@RequestHeader(name = "memberId") Long memberId, @RequestBody PostRequest.CreatePostDTO request){

        postService.createPost(memberId, request);
        return ApiResponse.onSuccess(null);
    }
  
    @GetMapping("/")
    public ApiResponse<PostResponse.PostPreviewListDTO> getPostList(){
        List<Post> postList = postService.getPostList();
        return ApiResponse.onSuccess(PostConverter.postPreviewListDTO(postList));
    }

    @GetMapping("/{memberId}")
    public ApiResponse<PostResponse.MyPostPreviewListDTO> getMyPostList(@PathVariable(name = "memberId") Long memberId){
        List<Post> myPostList = postService.getMyPostList(memberId);
        return ApiResponse.onSuccess(PostConverter.myPostPreviewListDTO(myPostList));
    }

    @GetMapping("/contents/{postId}")
    public ApiResponse<PostResponse.PostContentDTO> getPostContent(@PathVariable(name = "postId") Long postId){
        Post postContent = postService.getPostContent(postId);
        return ApiResponse.onSuccess(PostConverter.postContentDTO(postContent));
    }
}
