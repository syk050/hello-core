package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 실제 할당하는부분이 구현체를 의존함
    // 추상화(인터페이스)에도 의존하고 구체화에도 의존하게 됨 => DIP 위반
    //private final MemberRepository repository = new MemoryMemberRepository();

    // 추상화에만 의존 => DIP 준수
    // 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다
    private final MemberRepository repository;

    @Autowired
    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public void join(Member member) {
        repository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return repository.findById(id);
    }

    // 테스트 용도
    public MemberRepository getRepository() {
        return repository;
    }
}
