package umc.hackathon.chagok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.hackathon.chagok.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
