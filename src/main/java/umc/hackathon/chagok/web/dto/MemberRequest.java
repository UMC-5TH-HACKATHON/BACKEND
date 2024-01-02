package umc.hackathon.chagok.web.dto;

import lombok.Getter;

public class MemberRequest {

    @Getter
    public static class LoginRequestDTO{
        private String email;
        private String password;
    }
}
