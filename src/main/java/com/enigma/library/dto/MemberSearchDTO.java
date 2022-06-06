package com.enigma.library.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberSearchDTO {
    private String searchMemberId;
    private String searchMemberFirstName;
    private String searchMemberLastName;
    private String searchMemberPlaceOfBirth;
    private String searchMemberAddress;
    private String searchMemberTelp;
}