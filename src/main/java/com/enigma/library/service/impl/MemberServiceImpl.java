package com.enigma.library.service.impl;

import com.enigma.library.constant.ResponMesaage;
import com.enigma.library.dto.MemberSearchDTO;
import com.enigma.library.entity.Member;
import com.enigma.library.exception.DataNotFoundException;
import com.enigma.library.repository.MemberRepository;
import com.enigma.library.service.MemberService;
import com.enigma.library.specification.MemberSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member getMemberById(String memberId) {
        if (!memberRepository.findById(memberId).isPresent()) {
            String message = String.format(ResponMesaage.DATA_NOT_FOUND, "member", memberId);
            throw new DataNotFoundException(message);
        }
        return memberRepository.findById(memberId).get();
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(String idMember) {
        memberRepository.deleteById(idMember);
    }

    @Override
    public Page<Member> getMemberPerPage(Pageable pageable, MemberSearchDTO memberSearchDTO) {
        Specification<Member> memberSpecification = MemberSpecification.getSpecification(memberSearchDTO);
        return memberRepository.findAll(memberSpecification, pageable);
    }
}
