package com.wordie.alpha.utils;

import com.wordie.alpha.service.WordService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

    Logger logger = LoggerFactory.getLogger(Dictionary.class);

    private static Set<String> wordsSet;

    private static Set<String> testWordsSet;

    public Dictionary() throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream inputStream = cl.getResourceAsStream("words_beta.txt");
//        Path path = Paths.get("alpha/src/main/resources/words_alpha.txt");
//        byte[] readBytes = Files.readAllBytes(path);
        String contents = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
//        String wordListContents = new String(contents);
        logger.info("My content is " + contents);
        String[] words = contents.replace("\n", "").split(",");
        wordsSet = new HashSet<>();
        testWordsSet = new HashSet<>();
        logger.info("Words is " + words);
        Collections.addAll(wordsSet, words);
        for(String word : wordsSet) {
            Collections.addAll(testWordsSet, "s" + word + "e");
        }
    }

    public boolean contains(String word) {
        logger.info("My dictionary is " + testWordsSet);
        return wordsSet.contains(word);
    }
}