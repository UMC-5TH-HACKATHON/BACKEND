package umc.hackathon.chagok.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.hackathon.chagok.entity.Knowledge;
public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {

    Optional<Knowledge> findById(Long id);
}
