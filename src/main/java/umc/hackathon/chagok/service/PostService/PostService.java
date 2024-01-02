package umc.hackathon.chagok.service.PostService;

import java.util.List;

import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.web.dto.PostRequest;

public interface PostService {

    public Post createPost(Long memberId, PostRequest.CreatePostDTO request);

    List<Post> getMyPostList(Long memberId);
    List<Post> getPostList();

    Post getPostContent(Long postId);
}
