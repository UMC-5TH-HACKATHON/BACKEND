package umc.hackathon.chagok.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.hackathon.chagok.apiPayload.ApiResponse;
import umc.hackathon.chagok.service.MemberService.MemberService;
import umc.hackathon.chagok.web.dto.MemberRequest;
import umc.hackathon.chagok.web.dto.MemberResponse;
import umc.hackathon.chagok.web.dto.MemberResponse.LoginResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ApiResponse<LoginResponseDTO> login(@RequestBody MemberRequest.LoginRequestDTO request) {
        Long id = memberService.login(request);
        return ApiResponse.onSuccess(MemberResponse.LoginResponseDTO.builder().memberId(id).
                build());
    }
}
