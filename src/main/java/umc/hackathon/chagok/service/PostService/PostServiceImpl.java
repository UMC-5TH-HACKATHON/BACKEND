package umc.hackathon.chagok.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import umc.hackathon.chagok.apiPayload.code.status.ErrorStatus;
import umc.hackathon.chagok.apiPayload.exception.GeneralException;
import umc.hackathon.chagok.entity.Category;
import umc.hackathon.chagok.entity.Member;
import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.entity.Tag;
import umc.hackathon.chagok.repository.PostRepository;
import umc.hackathon.chagok.repository.TagRepository;
import umc.hackathon.chagok.service.MemberService.MemberService;
import umc.hackathon.chagok.service.categoryService.CategoryService;
import umc.hackathon.chagok.web.dto.PostRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import umc.hackathon.chagok.repository.MemberRepository;

import static umc.hackathon.chagok.apiPayload.code.status.ErrorStatus.MEMBER_NOT_FOUND;
import static umc.hackathon.chagok.apiPayload.code.status.ErrorStatus.POST_NOT_FOUND;


@Service
@RequiredArgsConstructor
@Transactional(readOnly= true)
@Slf4j
public class PostServiceImpl implements PostService{

    private final MemberService memberService;
    private final CategoryService categoryService;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final MemberRepository memberRepository;

    // TIL(게시글) 생성하기
    @Transactional
    public Post createPost(Long memberId, PostRequest.CreatePostDTO request) {

        // 멤버 찾기
        Member member = memberService.findMember(memberId);

        // 카테고리 찾기
        Category category = categoryService.findCategory(request.getCategory());

        // 태그 저장하기
        List<Tag> tagList = Arrays.stream(request.getTags().replaceAll(" ", "").split("#"))
                .filter(tag -> !tag.isEmpty())
                .map(
                        tag -> Tag.builder()
                                .tagName(tag)
                                .build()
                ).toList();

        Post newPost = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        newPost.setMember(member);
        newPost.setCategory(category);
        newPost.setTagList(tagList);

        postRepository.save(newPost);

        return newPost;
    }

    @Override
    public List<Post> getMyPostList(Long memberId) {

        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (memberOptional.isEmpty()) {
            throw new GeneralException(MEMBER_NOT_FOUND);
        }

        Member member = memberRepository.findById(memberId).get();
        List<Post> myPostList = postRepository.findAllByMember(member);
        return myPostList;
    }

    @Override
    public List<Post> getMyDatePostList(Long memberId, Integer mm, Integer yy, Integer dd){

        Optional<Member> memberOptional = memberRepository.findById(memberId);

        List<Post> myDatePostList = new ArrayList<>();

        if (memberOptional.isEmpty()) {
            throw new GeneralException(MEMBER_NOT_FOUND);
        }

        Member member = memberRepository.findById(memberId).get();
        List<Post> myTempDatePostList = postRepository.findAllByMember(member);

        LocalDate inputDate = LocalDate.of(yy,mm,dd);

        for(int i = 0; i < myTempDatePostList.size(); i++){
            LocalDateTime temp = myTempDatePostList.get(i).getCreatedAt();
            LocalDate compareLocalDate = temp.toLocalDate();
            if(compareLocalDate.isEqual(inputDate)){
                myDatePostList.add(myTempDatePostList.get(i));
            }
        }

        if(myDatePostList.isEmpty()){
            throw new GeneralException(POST_NOT_FOUND);
        }

        return myDatePostList;
    }

    @Override
    public List<Post> getPostList() {

        List<Post> postList = postRepository.findAll();
        return postList;
    }


    @Override
    public Post getPostContent(Long postId) {

        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isEmpty()) {
            throw new GeneralException(POST_NOT_FOUND);
        }

        Post postContent = postRepository.findById(postId).get();
        return postContent;
    }

    // TIL(게시글) 수정하기
    @Transactional
    public Post updatePost(Long postId, PostRequest.UpdatePostDTO request){

        Post post = findPost(postId);
        Category category = categoryService.findCategory(request.getCategory());
        // 태그 저장하기
        List<Tag> tagList = Arrays.stream(request.getTags().replaceAll(" ", "").split("#"))
                .filter(tag -> !tag.isEmpty())
                .map(
                tag -> Tag.builder()
                        .tagName(tag)
                        .build()
                ).toList();

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(category);

        tagRepository.deleteAll(post.getTagList());
        post.setTagList(tagList);

        log.info("수정된 태그 개수: {}", tagList.size());
        tagList.forEach(tag -> log.info("수정된 태그: {}", tag.getTagName()));

        return post;
    }

    // TIL(게시글) 삭제하기
    @Transactional
    public void deletePost(Long postId){

        Post post = findPost(postId);

        postRepository.delete(post);

    }

    public Post findPost(Long postId){
        return postRepository.findById(postId).orElseThrow(
                () -> {throw new GeneralException(POST_NOT_FOUND);
                }
        );
    }


}
