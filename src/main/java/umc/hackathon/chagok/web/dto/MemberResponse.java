package umc.hackathon.chagok.web.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberResponse {

    @Builder
    @Getter
    public static class LoginResponseDTO{
        private Long memberId;
    }
}
