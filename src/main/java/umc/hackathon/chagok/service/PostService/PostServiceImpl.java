package umc.hackathon.chagok.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly= true)
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public List<Post> getPostList() {

        List<Post> postList = postRepository.findAll();
        return postList;
    }
}
