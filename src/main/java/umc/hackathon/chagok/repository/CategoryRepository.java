package umc.hackathon.chagok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.hackathon.chagok.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
