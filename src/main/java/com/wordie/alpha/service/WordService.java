package com.wordie.alpha.service;

import com.wordie.alpha.constants.MessageConstants;
import com.wordie.alpha.entity.WordsEntryEntity;
import com.wordie.alpha.repo.WordsRepo;
import com.wordie.alpha.request.AddWordRequest;
import com.wordie.alpha.utils.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WordService {

    @Autowired
    private Dictionary dictionary;

    @Autowired
    private WordsRepo wordsRepo;

    Logger logger = LoggerFactory.getLogger(WordService.class);

    public ResponseEntity<String> addWord(AddWordRequest request) {
        String word = request.getWord();
        LocalDate date = LocalDate.parse(request.getDate());
        if (dictionary.contains(word)) {
            WordsEntryEntity newWordEntry = new WordsEntryEntity(word, date);
            wordsRepo.save(newWordEntry);
            return ResponseEntity.status(HttpStatus.CREATED).body(MessageConstants.ADD_WORD_SUCCESS_MESSAGE);
        }
        return ResponseEntity.status(HttpStatus.OK).body(MessageConstants.INVALID_WORD_MESSAGE);
    }
}
