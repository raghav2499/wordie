package com.wordie.alpha.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "words_entry")
@Data
public class WordsEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column(name = "targetWord")
    private String targetWord;

    @Column(name = "date")
    private LocalDate date;

    public WordsEntryEntity() {

    }

    public WordsEntryEntity(String targetWord, LocalDate date) {
        this.targetWord = targetWord;
        this.date = date;
    }
}
