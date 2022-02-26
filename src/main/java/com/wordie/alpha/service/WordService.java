package com.wordie.alpha.service;

import com.wordie.alpha.constants.MessageConstants;
import com.wordie.alpha.entity.WordsEntryEntity;
import com.wordie.alpha.repo.WordsRepo;
import com.wordie.alpha.request.AddWordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDate;

@Service
public class WordService {

    @Autowired
    private WordsRepo wordsRepo;

    public ResponseEntity<String> addWord(AddWordRequest request) {
        String word = request.getWord();
        LocalDate date = LocalDate.parse(request.getDate());
        WordsEntryEntity newWordEntry = new WordsEntryEntity(word, date);
        wordsRepo.save(newWordEntry);
        return ResponseEntity.status(HttpStatus.OK).body(MessageConstants.ADD_WORD_SUCCESS_MESSAGE);
    }
}
