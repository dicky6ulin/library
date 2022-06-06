package com.enigma.library.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "trx_return")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReturnBook {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id_return")
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "id_borrow")
    private Borrow borrow;
}