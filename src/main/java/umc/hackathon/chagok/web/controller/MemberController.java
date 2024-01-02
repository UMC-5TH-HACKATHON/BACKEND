package umc.hackathon.chagok.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Operation(summary = "로그인 API", description = "로그인이 성공하면 memberId를 넘겨줍니다. RequestBody로 email, password를 넘겨주세요")
    @PostMapping("/login")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4003", description = "없는 사용자이거나 비밀번호가 틀렸습니다."),

    })
    public ApiResponse<LoginResponseDTO> login(@RequestBody MemberRequest.LoginRequestDTO request) {
        Long id = memberService.login(request);
        return ApiResponse.onSuccess(MemberResponse.LoginResponseDTO.builder().memberId(id).
                build());
    }

    @Operation(summary = "특정 회원의 박스 조회 API", description = "특정 회원의 월별 박스 목록을 조회합니다. Query parameter로 달, 년도를 넘겨주세요. Header로 memberId를 넘겨주세요")
    @GetMapping("")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다."),

    })
    @Parameters({
            @Parameter(name = "mm", description = "선택 달 입니다!", example = "01"),
            @Parameter(name = "yy", description = "선택 년도 입니다!", example = "2024"),
            @Parameter(name = "memberId", description = "유저 식별자", example = "1")
    })
    public ApiResponse<List<Boolean>> viewBox(@RequestParam Integer mm, @RequestParam Integer yy,
                                              @RequestHeader Long memberId) {

        List<Boolean> boxList = memberService.boxCheck(memberId, mm, yy);
        return ApiResponse.onSuccess(boxList);
    }


}
