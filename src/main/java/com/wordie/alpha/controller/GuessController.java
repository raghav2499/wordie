package com.wordie.alpha.controller;

import com.sun.istack.NotNull;
import com.wordie.alpha.request.GuessTheWordRequest;
import com.wordie.alpha.response.GuessTheWordResponse;
import com.wordie.alpha.service.GuessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GuessController {

    Logger logger = LoggerFactory.getLogger(GuessController.class);

    @Autowired
    private GuessService guessService;

    @PostMapping(value = "/guess", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuessTheWordResponse> guessTheWord(@NotNull @RequestBody GuessTheWordRequest request) {
        long startTime = System.currentTimeMillis();
        ResponseEntity response = guessService.guessTheWord(request);
        logger.info("Time taken in guessing " + String.valueOf(System.currentTimeMillis() - startTime));
        return response;
    }
}
