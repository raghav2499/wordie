package com.wordie.alpha.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    private static Set<String> wordsSet;//todo check if we can initialise it as soon as server restarts

    public Dictionary() throws IOException {
        Path path = Paths.get("alpha/src/main/resources/words_alpha.txt");
        byte[] readBytes = Files.readAllBytes(path);
        String wordListContents = new String(readBytes, "UTF-8");
        String[] words = wordListContents.split("\r\n");
        wordsSet = new HashSet<>();
        Collections.addAll(wordsSet, words);
    }

    public boolean contains(String word) {
        return wordsSet.contains(word);
    }
}