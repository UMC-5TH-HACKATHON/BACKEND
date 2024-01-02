package umc.hackathon.chagok.service.MemberService;

import umc.hackathon.chagok.web.dto.MemberRequest;

public interface MemberService {

    Long login(MemberRequest.LoginRequestDTO request);

}
