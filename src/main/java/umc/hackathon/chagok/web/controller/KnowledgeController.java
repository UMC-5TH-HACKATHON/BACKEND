package umc.hackathon.chagok.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.hackathon.chagok.apiPayload.ApiResponse;
import umc.hackathon.chagok.service.KnowledgeService.KnowledgeService;
import umc.hackathon.chagok.web.dto.KnowledgeResponse;
import umc.hackathon.chagok.web.dto.KnowledgeResponse.knowledgeViewResponseDTO;

@RestController
@RequestMapping("/knowledge")
@RequiredArgsConstructor
public class KnowledgeController {

    private final KnowledgeService knowledgeService;

    @Operation(summary = "오늘의 상식 보기 API", description = "오늘의 상식을 보여줍니다. Query Parameter로 년도, 월, 일을 넘겨주세요")
    @GetMapping("")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),

    })
    @Parameters({
            @Parameter(name = "mm", description = "선택 달 입니다!", example = "01"),
            @Parameter(name = "yy", description = "선택 년도 입니다!", example = "2023"),
            @Parameter(name = "dd", description = "선택 일 입니다!", example = "03")
    })
    public ApiResponse<KnowledgeResponse.knowledgeViewResponseDTO> knowledgeView(@RequestParam Integer yy,
                                                                                 @RequestParam Integer mm,
                                                                                 @RequestParam Integer dd) {
        knowledgeViewResponseDTO responseDTO = knowledgeService.knowledgeView();
        return ApiResponse.onSuccess(responseDTO);
    }
}
