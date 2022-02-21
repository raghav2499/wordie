package com.wordie.alpha.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "words_entry")
@Data
public class WordsEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "targetWord")
    private String targetWord;

    public WordsEntryEntity() {

    }

    public WordsEntryEntity(String sessionId, String targetWord) {
        this.sessionId = sessionId;
        this.targetWord = targetWord;
    }
}
