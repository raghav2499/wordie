package com.wordie.alpha.controller;

import com.sun.istack.NotNull;
import com.wordie.alpha.Color;
import com.wordie.alpha.request.GuessTheWordRequest;
import com.wordie.alpha.response.GuessTheWordResponse;
import com.wordie.alpha.service.GuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuessController {

    @Autowired
    private GuessService guessService;

    @PostMapping(value = "/guess", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuessTheWordResponse> guessTheWord(@NotNull @RequestBody GuessTheWordRequest request) {
        return guessService.guessTheWord(request);
    }
}
