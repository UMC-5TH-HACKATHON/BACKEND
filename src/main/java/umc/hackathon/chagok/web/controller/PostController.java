package umc.hackathon.chagok.web.controller;


import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
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

    @Operation(summary = "전체 TIL 조회 API", description = "전체 TIL을 조회하는 API 입니다.")
    @GetMapping("/")
    public ApiResponse<PostResponse.PostPreviewListDTO> getPostList(){
        List<Post> postList = postService.getPostList();
        return ApiResponse.onSuccess(PostConverter.postPreviewListDTO(postList));
    }

    @Operation(summary = "나의 TIL 조회 API", description = "나의 TIL을 조회하는 API 입니다. path variable 로 member id를 입력해주세요.")
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다.")
    })
    @GetMapping("/{memberId}")
    public ApiResponse<PostResponse.MyPostPreviewListDTO> getMyPostList(@PathVariable(name = "memberId") Long memberId){
        List<Post> myPostList = postService.getMyPostList(memberId);
        return ApiResponse.onSuccess(PostConverter.myPostPreviewListDTO(myPostList));
    }

    @Operation(summary = "특정 TIL 상세 조회 API", description = "특정 TIL을 상세 조회하는 API 입니다. path variable 로 post id를 입력해주세요.")
    @Parameters({
            @Parameter(name = "postId", description = "TIL의 아이디, path variable 입니다.")
    })
    @GetMapping("/contents/{postId}")
    public ApiResponse<PostResponse.PostContentDTO> getPostContent(@PathVariable(name = "postId") Long postId){
        Post postContent = postService.getPostContent(postId);
        return ApiResponse.onSuccess(PostConverter.postContentDTO(postContent));
    }

    @Operation(summary = "특정 기간 TIL 상세 조회 API", description = "특정 기간의 TIL을 상세 조회하는 API 입니다. path variable 로 member id와 query string으로 year, month, day를 입력해주세요.")
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다."),
            @Parameter(name = "yy", description = "해당 날짜의 연도"),
            @Parameter(name = "mm", description = "해당 날짜의 달"),
            @Parameter(name = "dd", description = "해당 날짜의 일")
    })
    @GetMapping("/date/{memberId}/")
    public ApiResponse<PostResponse.PostTimeContentListDTO> getTimeContent(@PathVariable(name = "memberId") Long memberId,
                                                                       @RequestParam Integer yy, @RequestParam Integer mm,
                                                                       @RequestParam Integer dd){

        List<Post> postList = postService.getMyDatePostList(memberId,mm,yy,dd);
        return ApiResponse.onSuccess(PostConverter.postTimeContentListDTO(postList));
    }
}
