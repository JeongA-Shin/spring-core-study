package group.core.member;

import java.util.HashMap;
import java.util.Map;

//구현(배우, 차 모델기종 , 대체 가능) == 구현체
public class MemoryMemberRepository implements MemberRepository{
    
    private static Map<Long,Member> store = new HashMap<>();
    
    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
    }
    
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
