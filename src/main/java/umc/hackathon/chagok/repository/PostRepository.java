package umc.hackathon.chagok.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.hackathon.chagok.entity.Member;
import umc.hackathon.chagok.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByMemberAndCreatedAtBetween(Member member, LocalDateTime startTime, LocalDateTime endTime);
}
