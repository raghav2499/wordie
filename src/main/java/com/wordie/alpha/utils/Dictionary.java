package com.wordie.alpha.utils;

import com.wordie.alpha.core.Trie;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class Dictionary {

    Logger logger = LoggerFactory.getLogger(Dictionary.class);

    private static Trie dictionaryTrie;

    @PostConstruct
    public void initialiseDict() throws IOException {
        logger.info("Initialising dictionary on application startup");
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream inputStream = cl.getResourceAsStream("words_alpha.txt");
        String contents = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        String[] words = contents.replace("\r\n", "").split(",");
        dictionaryTrie = new Trie();
        for(String word : words) {
            dictionaryTrie.insert(word);
        }
    }

    public boolean contains(String word) {
        return dictionaryTrie.find(word);
    }
}