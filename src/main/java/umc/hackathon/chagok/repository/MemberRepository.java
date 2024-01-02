package umc.hackathon.chagok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.hackathon.chagok.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
