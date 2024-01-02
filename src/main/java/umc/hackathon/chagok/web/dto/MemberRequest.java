package umc.hackathon.chagok.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class MemberRequest {

    @Getter
    public static class LoginRequestDTO{
        @Schema(defaultValue = "test1234@naver.com")
        private String email;
        @Schema(defaultValue = "test1234")
        private String password;
    }
}
