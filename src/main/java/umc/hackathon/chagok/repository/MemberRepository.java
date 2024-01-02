package umc.hackathon.chagok.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.hackathon.chagok.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndPassword(String email, String password);
}
