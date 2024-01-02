package umc.hackathon.chagok.web.controller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.hackathon.chagok.apiPayload.ApiResponse;
import umc.hackathon.chagok.service.PostService.PostService;
import umc.hackathon.chagok.service.categoryService.CategoryService;
import umc.hackathon.chagok.web.dto.CategoryRequest;
import umc.hackathon.chagok.web.dto.CategoryResponse;
import umc.hackathon.chagok.web.dto.CategoryResponse.CategoryDTO;
import umc.hackathon.chagok.web.dto.PostRequest;
import umc.hackathon.chagok.converter.PostConverter;
import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.web.dto.PostResponse;

import java.util.List;
import umc.hackathon.chagok.web.dto.PostResponse;


import javax.validation.constraints.Null;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;

    // TIL 생성하기
    @PostMapping("")
    public ApiResponse<Null> createPost(@RequestHeader(name = "memberId") Long memberId, @RequestBody PostRequest.CreatePostDTO request){

        postService.createPost(memberId, request);

        return ApiResponse.onSuccess(null);
    }

    @RequestMapping(value="/categories" , method= RequestMethod.GET)
    public ApiResponse <List<CategoryDTO>> getCategories(){

        List<CategoryResponse.CategoryDTO> resCategories = categoryService.getCategories();

        return ApiResponse.onSuccess(resCategories);
    }

    @PostMapping("/categories")
    public ApiResponse createCategory(@RequestHeader(name = "memberId") Long memberId, @RequestBody CategoryRequest.CreateCategoryDto request){

        categoryService.createCategory(memberId, request);

        return ApiResponse.onSuccess(null);
    }

    // TIL 수정하기
    @PutMapping("/{postId}")
    public ApiResponse<Null> updatePost(@PathVariable(name = "postId")Long postId, @RequestBody PostRequest.UpdatePostDTO request){

        postService.updatePost(postId, request);

        return ApiResponse.onSuccess(null);
    }

    // TIL 삭제하기
    @DeleteMapping("/{postId}")
    public ApiResponse<Null> deletePost(@PathVariable(name = "postId")Long postId){

        postService.deletePost(postId);

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
}
