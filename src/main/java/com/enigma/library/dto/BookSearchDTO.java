package com.enigma.library.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookSearchDTO {
    private String searchBookId;
    private String searchBookTitle;
    private String searchBookDescription;
    private String searchBookPublisher;
    private Integer searchBookPublisherYear;
    private String searchBookAuthor;
}