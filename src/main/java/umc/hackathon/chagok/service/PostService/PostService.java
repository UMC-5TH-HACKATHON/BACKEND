package umc.hackathon.chagok.service.PostService;

import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.web.dto.PostRequest;

public interface PostService {

    public Post createPost(Long memberId, PostRequest.CreatePostDTO request);
}
