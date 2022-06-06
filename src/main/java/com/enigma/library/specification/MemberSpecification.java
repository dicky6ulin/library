package com.enigma.library.specification;

import com.enigma.library.dto.MemberSearchDTO;
import com.enigma.library.entity.Member;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MemberSpecification {
    public static Specification<Member> getSpecification (MemberSearchDTO memberSearchDTO) {
        return new Specification<Member>() {
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!(memberSearchDTO.getSearchMemberId() == null )) {
                    Predicate memberIdPredicate = criteriaBuilder.like(root.get("id"), "%" + memberSearchDTO.getSearchMemberId() + "%");
                    predicates.add(memberIdPredicate);
                }

                if (!(memberSearchDTO.getSearchMemberFirstName() == null )) {
                    Predicate memberFirstNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" +
                            memberSearchDTO.getSearchMemberFirstName().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(memberFirstNamePredicate);
                }

                if (!(memberSearchDTO.getSearchMemberLastName() == null )) {
                    Predicate memberLastNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" +
                            memberSearchDTO.getSearchMemberLastName().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(memberLastNamePredicate);
                }

                if (!(memberSearchDTO.getSearchMemberPlaceOfBirth() == null )) {
                    Predicate memberPlaceOfBirthPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("placeOfBirth")), "%" +
                            memberSearchDTO.getSearchMemberPlaceOfBirth().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(memberPlaceOfBirthPredicate);
                }

                if (!(memberSearchDTO.getSearchMemberAddress() == null )) {
                    Predicate memberAddressPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" +
                            memberSearchDTO.getSearchMemberAddress().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(memberAddressPredicate);
                }

                if (!(memberSearchDTO.getSearchMemberTelp() == null )) {
                    Predicate memberTelpPredicate = criteriaBuilder.like(root.get("telp"), "%" + memberSearchDTO.getSearchMemberTelp() + "%");
                    predicates.add(memberTelpPredicate);
                }

                Predicate[] arrayPredicate = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
