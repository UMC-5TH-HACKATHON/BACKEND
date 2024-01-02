package umc.hackathon.chagok.web.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    public ApiResponse<KnowledgeResponse.knowledgeViewResponseDTO> knowledgeView(@RequestParam Integer yy,
                                                                                 @RequestParam Integer mm,
                                                                                 @RequestParam Integer dd) {
        knowledgeViewResponseDTO responseDTO = knowledgeService.knowledgeView();
        return ApiResponse.onSuccess(responseDTO);
    }
}
