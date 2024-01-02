package umc.hackathon.chagok.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.hackathon.chagok.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Override
    Optional<Member> findById(Long aLong);

    Optional<Member> findByEmailAndPassword(String email, String password);
}
