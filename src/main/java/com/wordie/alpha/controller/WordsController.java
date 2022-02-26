package com.wordie.alpha.controller;

import com.sun.istack.NotNull;
import com.wordie.alpha.request.AddWordRequest;
import com.wordie.alpha.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordsController {

    @Autowired
    private WordService wordService;

    @PostMapping(value = "/word", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addWord(@NotNull @RequestBody AddWordRequest request) {
        return wordService.addWord(request);
    }
}
