package com.enigma.library.specification;

import com.enigma.library.dto.BookSearchDTO;
import com.enigma.library.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookSpecification {
    public static Specification<Book> getSpecification (BookSearchDTO bookSearchDTO) {
        return new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!(bookSearchDTO.getSearchBookId() == null )) {
                    Predicate bookIdPredicate = criteriaBuilder.like(root.get("id"), "%" + bookSearchDTO.getSearchBookId() + "%");
                    predicates.add(bookIdPredicate);
                }

                if (!(bookSearchDTO.getSearchBookTitle() == null )) {
                    Predicate bookTitlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" +
                            bookSearchDTO.getSearchBookTitle().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(bookTitlePredicate);
                }

                if (!(bookSearchDTO.getSearchBookDescription() == null )) {
                    Predicate bookDescriptionPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" +
                            bookSearchDTO.getSearchBookDescription().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(bookDescriptionPredicate);
                }

                if (!(bookSearchDTO.getSearchBookPublisher() == null )) {
                    Predicate bookPublisherPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("publisher")), "%" +
                            bookSearchDTO.getSearchBookPublisher().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(bookPublisherPredicate);
                }

                if (!(bookSearchDTO.getSearchBookAuthor() == null )) {
                    Predicate bookAuthorPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" +
                            bookSearchDTO.getSearchBookAuthor().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(bookAuthorPredicate);
                }

                Predicate[] arrayPredicate = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
