package com.enigma.library.service;

import com.enigma.library.dto.MemberSearchDTO;
import com.enigma.library.entity.Book;
import com.enigma.library.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    public Member getMemberById(String memberId);
    public Member saveMember(Member member);
    public void deleteMember(String idMember);
    Page<Member> getMemberPerPage(Pageable pageable, MemberSearchDTO memberSearchDTO);
}
