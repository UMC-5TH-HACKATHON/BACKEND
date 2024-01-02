package umc.hackathon.chagok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.hackathon.chagok.entity.Knowledge;
public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {
}
