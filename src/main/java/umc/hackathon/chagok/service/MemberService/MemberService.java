package umc.hackathon.chagok.service.MemberService;


import java.util.List;
import umc.hackathon.chagok.web.dto.MemberRequest;
import umc.hackathon.chagok.entity.Member;


public interface MemberService {
    Member findMember(Long memberId);

    List<Boolean> boxCheck(Long memberId, Integer mm, Integer yy);

    Long login(MemberRequest.LoginRequestDTO request);

}
