package umc.hackathon.chagok.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.hackathon.chagok.apiPayload.code.BaseErrorCode;
import umc.hackathon.chagok.apiPayload.code.status.ErrorStatus;
import umc.hackathon.chagok.apiPayload.exception.GeneralException;
import umc.hackathon.chagok.entity.Member;
import umc.hackathon.chagok.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly= true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public Member findMember(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(
                () -> {
                    throw new GeneralException(ErrorStatus.MEMBER_NOT_FOUND);
                }
        );
    }
}
