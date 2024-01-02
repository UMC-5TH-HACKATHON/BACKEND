package umc.hackathon.chagok.service.KnowledgeService;


import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.hackathon.chagok.entity.Knowledge;
import umc.hackathon.chagok.repository.KnowledgeRepository;
import umc.hackathon.chagok.web.dto.KnowledgeResponse;
import umc.hackathon.chagok.web.dto.KnowledgeResponse.knowledgeViewResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly= true)
public class KnowledgeServiceImpl implements KnowledgeService{

    private final KnowledgeRepository knowledgeRepository;

    public KnowledgeResponse.knowledgeViewResponseDTO knowledgeView() {
        Long randomNumber = (long) (Math.random() * 2 + 1);
        Knowledge knowledge = knowledgeRepository.findById(randomNumber).get();
        return knowledgeViewResponseDTO.builder().title(knowledge.getTitle())
                .content(knowledge.getContent())
                .imgUrl(knowledge.getImgUrl())
                .build();
    }




}
