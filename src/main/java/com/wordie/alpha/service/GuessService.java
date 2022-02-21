package com.wordie.alpha.service;

import com.wordie.alpha.Color;
import com.wordie.alpha.constants.MessageConstants;
import com.wordie.alpha.entity.WordsEntryEntity;
import com.wordie.alpha.repo.WordsRepo;
import com.wordie.alpha.request.GuessTheWordRequest;
import com.wordie.alpha.response.GuessTheWordResponse;
import com.wordie.alpha.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class GuessService {

    @Autowired
    private WordsRepo wordsRepo;

    public GuessTheWordResponse guessTheWord(GuessTheWordRequest request, String sessionId) {
        WordsEntryEntity wordEntry = wordsRepo.findOneBySessionId(sessionId);
        Integer wordLength = request.getWordLength();
        String guessWord = request.getGuessWord();
        if (wordEntry == null) {
            String word = generateNewWord(wordLength);
            wordEntry = new WordsEntryEntity(sessionId, word);
            wordsRepo.save(wordEntry);
        }
        if (isValidWord(guessWord, wordLength)) {
            List<Color> colorCoding = getColorCoding(guessWord, wordEntry.getTargetWord());
            return new GuessTheWordResponse(colorCoding, MessageConstants.GUESS_THE_WORD_SUCCESS_RESPONSE);
        }
        return new GuessTheWordResponse(MessageConstants.GUESS_THE_WORD_INVALID_RESPONSE);
    }

    private String generateNewWord(Integer length) {
        return "TACIT";
    }

    private boolean isValidWord(String word, Integer length) {
        return word.length() == length;//todo : add additional check that word is valid
    }

    private List<Color> getColorCoding(String guessWord, String targetWord) {
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
            if (resultList.get(iter) == Color.GREY
                    && frequencyMap.containsKey(guessWord.charAt(iter))
                    && frequencyMap.get(guessWord.charAt(iter)) > 0) {
                resultList.set(iter, Color.YELLOW);
                frequencyMap.put(guessWord.charAt(iter), guessWord.charAt(iter) - 1);
            }
        }

        return resultList;
    }
}
