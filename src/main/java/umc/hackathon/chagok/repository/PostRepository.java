package umc.hackathon.chagok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.hackathon.chagok.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}