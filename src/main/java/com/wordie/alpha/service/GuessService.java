package com.wordie.alpha.service;

import com.wordie.alpha.Color;
import com.wordie.alpha.constants.MessageConstants;
import com.wordie.alpha.entity.WordsEntryEntity;
import com.wordie.alpha.repo.WordsRepo;
import com.wordie.alpha.request.GuessTheWordRequest;
import com.wordie.alpha.response.GuessTheWordResponse;
import com.wordie.alpha.utils.CommonUtils;
import com.wordie.alpha.utils.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class GuessService {

    @Autowired
    private WordsRepo wordsRepo;

    @Autowired
    private Dictionary dictionary;

    Logger logger = LoggerFactory.getLogger(GuessService.class);

    public ResponseEntity<GuessTheWordResponse> guessTheWord(GuessTheWordRequest request) {
        WordsEntryEntity wordEntry = wordsRepo.findOneByDate(LocalDate.now());//todo check if we could store it into some variable that refreshes after 24 hours
        Integer wordLength = request.getWordLength();
        String guessWord = request.getGuessWord();
        logger.info("Guess word is " + guessWord + " and word length is " + wordLength);
        if (isValidWord(guessWord, wordLength)) {
            List<Color> colorCoding = getColorCoding(guessWord, wordEntry.getTargetWord());
            GuessTheWordResponse successResponseBody = new GuessTheWordResponse(colorCoding, MessageConstants.GUESS_THE_WORD_SUCCESS_RESPONSE);
            return ResponseEntity.status(HttpStatus.OK).body(successResponseBody);
        }
        GuessTheWordResponse noContentResponseBody = new GuessTheWordResponse(MessageConstants.GUESS_THE_WORD_SUCCESS_RESPONSE);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(noContentResponseBody);
    }

    private boolean isValidWord(String word, Integer length) {
        return word.length() == length && dictionary.contains(word);
    }

    public List<Color> getColorCoding(String guessWord, String targetWord) {
        Integer wordLength = guessWord.length();
        List<Color> resultList = new ArrayList<Color>(Collections.nCopies(wordLength, Color.GREY));
        Map<Character, Integer> frequencyMap = CommonUtils.getFrequencyMap(targetWord);

        for (int iter = 0; iter < wordLength; iter++) {
            Character targetCharacter = targetWord.charAt(iter);
            if (Character.compare(guessWord.charAt(iter), targetCharacter) == 0) {
                resultList.set(iter, Color.GREEN);
                frequencyMap.put(targetCharacter, frequencyMap.get(targetCharacter) - 1);
            }
        }

        for (int iter = 0; iter < wordLength; iter++) {
            Character targetCharacter = targetWord.charAt(iter);
            if (resultList.get(iter) == Color.GREY
                    && frequencyMap.containsKey(targetCharacter)
                    && frequencyMap.get(targetCharacter) > 0) {
                resultList.set(iter, Color.YELLOW);
                frequencyMap.put(guessWord.charAt(iter), frequencyMap.get(targetCharacter) - 1);
            }
        }

        return resultList;
    }
}
