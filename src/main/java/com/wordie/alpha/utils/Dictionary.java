package com.wordie.alpha.utils;

import org.apache.commons.io.IOUtils;

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
    private static Set<String> wordsSet;//todo check if we can initialise it as soon as server restarts

    public Dictionary() throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream inputStream = cl.getResourceAsStream("words_alpha.txt");
//        Path path = Paths.get("alpha/src/main/resources/words_alpha.txt");
//        byte[] readBytes = Files.readAllBytes(path);
        String contents = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
//        String wordListContents = new String(contents);
        String[] words = contents.split("\r\n");
        wordsSet = new HashSet<>();
        Collections.addAll(wordsSet, words);
    }

    public boolean contains(String word) {
        return wordsSet.contains(word);
    }
}