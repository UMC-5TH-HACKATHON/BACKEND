package umc.hackathon.chagok.service.MemberService;

import static umc.hackathon.chagok.apiPayload.code.status.ErrorStatus.MEMBER_NOT_FOUND;
import static umc.hackathon.chagok.apiPayload.code.status.ErrorStatus.MEMBER_NOT_FOUND_OR_PASSWORD_ERROR;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.hackathon.chagok.apiPayload.exception.GeneralException;
import umc.hackathon.chagok.entity.Member;
import umc.hackathon.chagok.repository.MemberRepository;
import umc.hackathon.chagok.web.dto.MemberRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly= true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public Long login(MemberRequest.LoginRequestDTO request) {
        String email = request.getEmail();
        String password = request.getPassword();
        Optional<Member> memberOptional = memberRepository.findByEmailAndPassword(email, password);
        if (memberOptional.isEmpty()) {
            throw new GeneralException(MEMBER_NOT_FOUND_OR_PASSWORD_ERROR);
        }
        return memberOptional.get().getId();
    }

}
