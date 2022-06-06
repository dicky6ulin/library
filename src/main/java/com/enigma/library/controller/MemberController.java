package com.enigma.library.controller;

import com.enigma.library.constant.ApiURLConstant;
import com.enigma.library.constant.ResponMesaage;
import com.enigma.library.dto.MemberSearchDTO;
import com.enigma.library.entity.Book;
import com.enigma.library.entity.Member;
import com.enigma.library.service.MemberService;
import com.enigma.library.utils.PageResponseWrapper;
import com.enigma.library.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURLConstant.MEMBER)
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<Response<Member>> addMember(@RequestBody Member member) {
        Response<Member> response = new Response<>();
        String message = String.format(ResponMesaage.DATA_INSERT, "member");
        response.setMessage(message);
        response.setData(memberService.saveMember(member));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id) {
        return memberService.getMemberById(id);
    }

    @GetMapping
    public PageResponseWrapper<Member> getMemberPerPage(@RequestBody MemberSearchDTO memberSearchDTO,
                                                        @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(name = "size", defaultValue = "3") Integer sizePerPage,
                                                        @RequestParam(name = "sortBy", defaultValue = "firstName") String sortBy,
                                                        @RequestParam(name = "direction", defaultValue = "asc") String direction) {
        Sort sorting = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page,sizePerPage,sorting);
        Page<Member> customerPage = memberService.getMemberPerPage(pageable, memberSearchDTO);
        return new PageResponseWrapper<>(customerPage);
    }

    @PutMapping
    public ResponseEntity<Response<Member>> updateMember(@RequestBody Member member) {
        Response<Member> response = new Response<>();
        String message = String.format(ResponMesaage.DATA_UPDATE, "member");
        response.setMessage(message);
        response.setData(memberService.saveMember(member));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping
    public void deleteMember(@RequestParam String id) {
        memberService.deleteMember(id);
    }
}
