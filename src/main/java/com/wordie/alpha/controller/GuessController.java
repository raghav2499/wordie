package com.wordie.alpha.controller;

import com.sun.istack.NotNull;
import com.wordie.alpha.request.GuessTheWordRequest;
import com.wordie.alpha.response.GuessTheWordResponse;
import com.wordie.alpha.service.GuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuessController {

    @Autowired
    private GuessService guessService;

    @PostMapping(value = "/guess", produces = MediaType.APPLICATION_JSON_VALUE)
    public GuessTheWordResponse guessTheWord(@NotNull @RequestBody GuessTheWordRequest request,
                                             @RequestParam(required = true, value = "session_id") String sessionId) {
        return guessService.guessTheWord(request, sessionId);
    }
}
