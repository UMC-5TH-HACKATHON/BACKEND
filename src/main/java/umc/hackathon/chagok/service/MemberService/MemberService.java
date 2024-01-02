package umc.hackathon.chagok.service.MemberService;


import java.util.List;
import umc.hackathon.chagok.web.dto.MemberRequest;
import umc.hackathon.chagok.entity.Member;


public interface MemberService {
      public Member findMember(Long memberId);
    public List<Boolean> boxCheck(Long memberId, Integer mm, Integer yy);

}
