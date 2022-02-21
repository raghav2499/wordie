package com.wordie.alpha.utils;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    public static Map<Character, Integer> getFrequencyMap(String str) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (Character ch : str.toCharArray()) {
            if (frequencyMap.containsKey(ch)) {
                frequencyMap.put(ch, frequencyMap.get(ch) + 1);
            } else {
                frequencyMap.put(ch, 1);
            }
        }
        return frequencyMap;
    }
}
