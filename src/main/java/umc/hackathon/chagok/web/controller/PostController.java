package umc.hackathon.chagok.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "TIL 생성하기 API", description = "post를 생성합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "멤버 id 입니다. request header 입니다.")
    })
    @PostMapping("")
    public ApiResponse<Null> createPost(@RequestHeader(name = "memberId") Long memberId, @RequestBody PostRequest.CreatePostDTO request){

        postService.createPost(memberId, request);
        return ApiResponse.onSuccess(null);
    }

    @Operation(summary = "카테고리 조회 API", description = "post의 카테고리를 조회합니다.")
    @RequestMapping(value="/categories" , method= RequestMethod.GET)
    public ApiResponse <List<CategoryDTO>> getCategories(){

        List<CategoryResponse.CategoryDTO> resCategories = categoryService.getCategories();

        return ApiResponse.onSuccess(resCategories);
    }

    @Operation(summary = "카테고리 생성 API", description = "post의 카테고리를 생성합니다.")
    @PostMapping("/categories")
    public ApiResponse createCategory(@RequestBody CategoryRequest.CreateCategoryDto request){

        categoryService.createCategory(request);

        return ApiResponse.onSuccess(null);
    }

    // TIL 수정하기
    @Operation(summary = "TIL 수정하기 API", description = "post의 내용을 수정합니다.")
    @Parameters({
            @Parameter(name = "postId", description = "TIL id 입니다. path variable로 받습니다.")
    })
    @PutMapping("/{postId}")
    public ApiResponse<Null> updatePost(@PathVariable(name = "postId")Long postId, @RequestBody PostRequest.UpdatePostDTO request){

        postService.updatePost(postId, request);

        return ApiResponse.onSuccess(null);
    }

    // TIL 삭제하기
    @Operation(summary = "TIL 삭제하기 API", description = "post를 삭제합니다.")
    @Parameters({
            @Parameter(name = "postId", description = "TIL id 입니다. path variable로 받습니다.")
    })
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

    @Operation(summary = "특정 Tag를 포함하는 TIL 조회 API", description = "특정 Tag를 포함하는 TIL을 조회하는 API 입니다. query string으로 tag name을 입력해주세요.")
    @Parameters({
            @Parameter(name = "tagName", description = "tag의 이름입니다. query string 입니다.")
    })
    @GetMapping("/search")
    public ApiResponse<PostResponse.PostSearchTagListDTO> getSearchTag(@RequestParam String tagName){

        List<Post> postList = postService.getSearchTagPostList(tagName);
        return ApiResponse.onSuccess(PostConverter.postSearchTagListDTO(postList));
    }
}
