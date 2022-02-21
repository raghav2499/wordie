package com.wordie.alpha.response;

import com.wordie.alpha.Color;
import lombok.Data;

import java.util.List;

@Data
public class GuessTheWordResponse {

    List<Color> result;

    String message;

    public GuessTheWordResponse(String message) {
        this.message = message;
    }

    public GuessTheWordResponse(List<Color> result, String message) {
        this.result = result;
        this.message = message;
    }
}
