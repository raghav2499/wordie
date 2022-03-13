package com.wordie.alpha.service;

import com.wordie.alpha.constants.MessageConstants;
import com.wordie.alpha.entity.WordsEntryEntity;
import com.wordie.alpha.repo.WordsRepo;
import com.wordie.alpha.request.AddWordRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(WordService.class);

    public ResponseEntity<String> addWord(AddWordRequest request) {
        String word = request.getWord();
        LocalDate date = LocalDate.parse(request.getDate());
        WordsEntryEntity newWordEntry = new WordsEntryEntity(word, date);
        WordsEntryEntity savedWord = wordsRepo.save(newWordEntry);
        logger.info("New word " + newWordEntry.getTargetWord() + " new word date " + newWordEntry.getDate()
                + " and saved word " + savedWord.getTargetWord() + " saved word date " + savedWord.getDate());
        return ResponseEntity.status(HttpStatus.OK).body(MessageConstants.ADD_WORD_SUCCESS_MESSAGE);
    }
}
