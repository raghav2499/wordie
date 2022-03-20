package com.wordie.alpha.response;

import com.wordie.alpha.Color;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GuessTheWordResponse {

    List<String> result;

    String message;

    public GuessTheWordResponse(String message) {
        this.message = message;
    }

    public GuessTheWordResponse(List<Color> result, String message) {
        List<String> resultList = new ArrayList<>(result.size());
        for(Color color : result) {
            resultList.add(color.toString());
        }
        this.result = resultList;
        this.message = message;
    }
}
