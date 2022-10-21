package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 실제 할당하는부분이 구현체를 의존함
    // 추상화(인터페이스)에도 의존하고 구체화에도 의존하게 됨 => DIP 위반
    private final MemberRepository repository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        repository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return repository.findById(id);
    }
}
