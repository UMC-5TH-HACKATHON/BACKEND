package umc.hackathon.chagok.service.PostService;

import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.web.dto.PostRequest;

public interface PostService {

    public Post createPost(Long memberId, PostRequest.CreatePostDTO request);
    public Post updatePost(Long postId, PostRequest.UpdatePostDTO request);
}
