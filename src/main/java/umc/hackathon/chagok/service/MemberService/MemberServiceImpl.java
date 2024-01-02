package umc.hackathon.chagok.service.MemberService;

import static umc.hackathon.chagok.apiPayload.code.status.ErrorStatus.MEMBER_NOT_FOUND;
import static umc.hackathon.chagok.apiPayload.code.status.ErrorStatus.MEMBER_NOT_FOUND_OR_PASSWORD_ERROR;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.hackathon.chagok.apiPayload.code.BaseErrorCode;
import umc.hackathon.chagok.apiPayload.code.status.ErrorStatus;
import umc.hackathon.chagok.apiPayload.exception.GeneralException;
import umc.hackathon.chagok.entity.Member;
import umc.hackathon.chagok.entity.Post;
import umc.hackathon.chagok.repository.MemberRepository;
import umc.hackathon.chagok.repository.PostRepository;
import umc.hackathon.chagok.web.dto.MemberRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly= true)

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Member findMember(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(
                () -> {
                    throw new GeneralException(ErrorStatus.MEMBER_NOT_FOUND);
                }
        );
    }
    public List<Boolean> boxCheck(Long memberId, Integer mm, Integer yy) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (memberOptional.isEmpty()) {
            throw new GeneralException(MEMBER_NOT_FOUND);
        }
        Member member = memberOptional.get();
        LocalDateTime startDateTime = LocalDateTime.of(yy, mm, 1, 0, 0, 0);
        LocalDateTime endDateTime = startDateTime.plusMonths(1).minusSeconds(1);
        List<Post> monthBoxList = postRepository.findAllByMemberAndCreatedAtBetween(member,
                startDateTime, endDateTime);
        List<Boolean> boxCheckList = new ArrayList<>();
        for (int day = 1; day <= startDateTime.toLocalDate().lengthOfMonth(); day++) {
            LocalDateTime currentDateTime = LocalDateTime.of(yy, mm, day, 0, 0, 0);

            // 해당 날짜에 글이 있는지 확인
            boolean postExists = monthBoxList.stream()
                    .anyMatch(post -> post.getCreatedAt().toLocalDate().isEqual(currentDateTime.toLocalDate()));

            boxCheckList.add(postExists);
        }
        return boxCheckList;
        }
        
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
