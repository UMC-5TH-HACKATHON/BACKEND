package umc.hackathon.chagok.service.MemberService;

import java.util.List;
import umc.hackathon.chagok.web.dto.MemberRequest;

public interface MemberService {
    public List<Boolean> boxCheck(Long memberId, Integer mm, Integer yy);

}
